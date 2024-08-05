import { Form, Formik } from "formik";
import * as Yup from "yup";

import { Input } from "@/components/ui/input";
import { Textarea } from "@/components/ui/textarea";
import { Button } from "../ui/button";

interface Props {}

// Form values types
interface InitialFormValues {
  title: string;
  description: string;
  content: string;
}

//  Yup form validation schema
const EditPostSchema = Yup.object().shape({
  title: Yup.string()
    .min(2, "Too Short!")
    .max(100, "Too Long!")
    .required("Required"),
  description: Yup.string()
    .min(6, "Must be at least 6 characters long")
    .required("Required"),
  content: Yup.string().required("Required"),
});

export const EditPostForm: React.FC<Props> = () => {
  return (
    <Formik<InitialFormValues>
      initialValues={{
        content: "",
        description: "",
        title: "",
      }}
      validationSchema={EditPostSchema}
      onSubmit={async (values, { setSubmitting }) => {
        console.log(values);
        setSubmitting(false);
      }}
    >
      {({ handleChange, handleSubmit, values, errors, isSubmitting }) => (
        <Form
          onSubmit={handleSubmit}
          className="flex flex-col gap-3 w-full pb-3"
        >
          <div className="flex flex-col gap-4">
            <div className="flex flex-col gap-3 w-full">
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
            Edit post
          </Button>
        </Form>
      )}
    </Formik>
  );
};
