import { Form, Formik } from "formik";
import { useState } from "react";
import * as Yup from "yup";

import { Input } from "@/components/ui/input";
import { Textarea } from "@/components/ui/textarea";
import { Button } from "../ui/button";

interface Props {}

// Form values types
interface InitialFormValues {
  title: string;
  coverImage: File | undefined;
  description: string;
  content: string;
}

//  Yup form validation schema
const CreatePostSchema = Yup.object().shape({
  title: Yup.string()
    .min(2, "Too Short!")
    .max(100, "Too Long!")
    .required("Required"),
  description: Yup.string()
    .min(6, "Must be at least 6 characters long")
    .required("Required"),
  content: Yup.string().required("Required"),
});

export const CreatePostForm: React.FC<Props> = () => {
  const [coverImageUrl, setCoverImageUrl] = useState("");

  return (
    <Formik<InitialFormValues>
      initialValues={{
        content: "",
        description: "",
        title: "",
        coverImage: undefined,
      }}
      validationSchema={CreatePostSchema}
      onSubmit={async (values, { setSubmitting, setFieldError }) => {
        if (values.coverImage) {
          // First upload the cover image to cloudinary
          const data = new FormData();
          data.append("file", values.coverImage);
          data.append(
            "upload_preset",
            import.meta.env.VITE_CLOUDINARY_UPLOAD_PRESET
          );
          data.append("cloud_name", import.meta.env.VITE_CLOUDINARY_CLOUD_NAME);
          data.append("folder", "Blog-Cover-Images");

          try {
            const response = await fetch(
              `https://api.cloudinary.com/v1_1/${
                import.meta.env.VITE_CLOUDINARY_CLOUD_NAME
              }/image/upload`,
              {
                method: "POST",
                body: data,
              }
            );
            const res = await response.json();
            setCoverImageUrl(res.secure_url);

            console.log(coverImageUrl);
          } catch (error) {
            // Log the error into a Logging service (eg: Sentry)
            console.error(error);
          }

          // Submit formData and cloudary image url to backend
        } else {
          setFieldError("coverImage", "You must upload a cover image");
        }

        setSubmitting(false);
      }}
    >
      {({
        handleChange,
        handleSubmit,
        values,
        errors,
        isSubmitting,
        setFieldValue,
      }) => (
        <Form
          onSubmit={handleSubmit}
          className="flex flex-col gap-3 w-full pb-3"
        >
          <div className="flex flex-col gap-4">
            <div className="flex sm:flex-row flex-col gap-3 w-full">
              <div className="flex-1 w-full">
                <label htmlFor="">Title</label>
                <Input
                  value={values.title}
                  name="title"
                  onChange={handleChange}
                  placeholder="What's the title for your post?"
                />
                {errors.title && (
                  <span className="text-red-600 text-sm">{errors.title}</span>
                )}
              </div>
              <div>
                <label htmlFor="">Cover image</label>
                <Input
                  type="file"
                  name="coverImage"
                  onChange={(e) => {
                    // Object is possibly null error w/o check
                    if (e.currentTarget.files) {
                      setFieldValue("coverImage", e.currentTarget.files[0]);
                    }
                  }}
                  placeholder="What's the title for your post?"
                />
                {errors.coverImage && (
                  <span className="text-red-600 text-sm">
                    {errors.coverImage}
                  </span>
                )}
              </div>
            </div>
            <label htmlFor="">Description</label>
            <Textarea
              placeholder="Tell your audience a short summary."
              rows={4}
              name="description"
              onChange={handleChange}
              value={values.description}
            />
            {errors.description && (
              <span className="text-red-600 text-sm">{errors.description}</span>
            )}
          </div>
          <label>Content (Markdown support)</label>
          <Textarea
            rows={20}
            name="content"
            value={values.content}
            onChange={handleChange}
          />
          {errors.content && (
            <span className="text-red-600 text-sm">{errors.content}</span>
          )}
          <Button type="submit" disabled={isSubmitting}>
            Create post
          </Button>
        </Form>
      )}
    </Formik>
  );
};
