import { Form, Formik } from "formik";
import { useState } from "react";
import * as Yup from "yup";

import { Input } from "@/components/ui/input";
import { Textarea } from "@/components/ui/textarea";
import { Button } from "../ui/button";
import { authApi } from "@/utils/api/constants";
import toast from "react-hot-toast";
import { useNavigate } from "react-router-dom";
import { getErrorFromResponse } from "@/utils/functions/function";

interface Props {
  post: any;
}

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

export const CreatePostForm: React.FC<Props> = ({ post }) => {
  const [coverImageUrl, setCoverImageUrl] = useState("");
  const navigate = useNavigate();
  const buttonContent = post == null ? "Create post" : "Update post";

  return (
    <Formik<InitialFormValues>
      initialValues={{
        content: post == null ? "" : post.content,
        description: "",
        title: post == null ? "" : post.title,
        coverImage: post == null ? "" : post.imageUrl,
      }}
      validationSchema={CreatePostSchema}
      onSubmit={async (values, { setSubmitting, setFieldError }) => {
        if (!values.coverImage && post == null) {
          setFieldError("coverImage", "You must upload a cover image");
          setSubmitting(false);
        }
        // First upload the cover image to cloudinary
        const data = new FormData();
        data.append("file", values.coverImage!);
        data.append(
          "upload_preset",
          import.meta.env.VITE_CLOUDINARY_UPLOAD_PRESET
        );
        data.append("cloud_name", import.meta.env.VITE_CLOUDINARY_CLOUD_NAME);
        data.append("folder", "Blog-Cover-Images");

        try {
          // Uploading the image to cloudinary to prevent unnecessary memory consumption on the server
          if (post == null) {
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
          }
          let createPostResponse;
          const body = {
            title: values.title,
            content: values.content,
            imageUrl: coverImageUrl,
          };
          if (post == null) {
            createPostResponse = await authApi.post(`/posts/create`, body);
          } else {
            createPostResponse = await authApi.put(
              `/posts/update/${post.id}`,
              body
            );
          }

          console.log(createPostResponse);
          // Moving bacl to the previous page
          navigate(-1);
          let message: string;
          // Providing the feedback to the user based on the action ( created or updated )
          if (post == null) {
            message = "The post was created successfully";
          } else {
            message = "The post was update successfully";
          }
          toast.success(message);
        } catch (error) {
          toast.error(getErrorFromResponse(error));
        } finally {
          setSubmitting(false);
          //  Removing the post from the local storage is available
          if (post != null) {
            localStorage.removeItem("post");
          }
        }
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
        <div className="md:mb-0 mb-5">
          <Form
            onSubmit={handleSubmit}
            className="flex relative flex-col gap-3 w-full  pb-3"
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
                <span className="text-red-600 text-sm">
                  {errors.description}
                </span>
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
            <div className="w-full flex justify-end items-end">
              <Button
                className="md:w-[20%] w-full"
                type="submit"
                disabled={isSubmitting}
              >
                {buttonContent}
              </Button>
            </div>
          </Form>
        </div>
      )}
    </Formik>
  );
};
