import { Form, Formik } from "formik";
import { Link, useNavigate } from "react-router-dom";
import * as Yup from "yup";

import { Input } from "../ui/input";
import { Button } from "../ui/button";
import toast from "react-hot-toast";
import { getErrorFromResponseData } from "@/utils/functions/function";
import { api } from "@/utils/api/constants";

interface Props {}

// Form values types
interface InitialFormValues {
  username: string;
  email: string;
  password: string;
}

//  Yup form validation schema
const RegisterSchema = Yup.object().shape({
  username: Yup.string()
    .min(2, "Too Short!")
    .max(50, "Too Long!")
    .required("Required"),
  password: Yup.string()
    .min(6, "Must be at least 6 characters long")
    .required("Required"),
  email: Yup.string().email("Invalid email").required("Required"),
});

export const RegisterForm: React.FC<Props> = () => {
  const navigate = useNavigate();
  return (
    <Formik<InitialFormValues>
      initialValues={{ email: "", password: "", username: "" }}
      validationSchema={RegisterSchema}
      onSubmit={async (values, { setSubmitting }) => {
        try {
          setSubmitting(true);
          setTimeout(() => {}, 1000);
          await api.post("/users/create", {
            email: values.email,
            password: values.password,
            userName: values.username,
          });
          navigate("/");
          toast.success("The accoung was successfully created! please login.");
        } catch (error) {
          toast.error(getErrorFromResponseData(error));
        } finally {
          setSubmitting(false);
        }
      }}
    >
      {({ handleChange, handleSubmit, values, errors, isSubmitting }) => (
        <Form
          onSubmit={handleSubmit}
          className="flex flex-col gap-4 w-full items-center"
        >
          <div className="w-full flex flex-col gap-2">
            <label className="text-sm text-muted-foreground">Username</label>
            <Input
              name="username"
              value={values.username}
              onChange={handleChange}
              type="text"
              placeholder="eg: kent"
            />
            {errors.username && (
              <span className="text-red-600 text-sm">{errors.username}</span>
            )}
            <label className="text-sm text-muted-foreground">Email</label>
            <Input
              name="email"
              value={values.email}
              onChange={handleChange}
              type="email"
              placeholder="eg: kent@gmail.com"
            />
            {errors.email && (
              <span className="text-red-600 text-sm">{errors.email}</span>
            )}
            <label className="text-sm text-muted-foreground">Password</label>
            <Input
              name="password"
              value={values.password}
              onChange={handleChange}
              type="password"
              placeholder="*******"
            />
            {errors.password && (
              <span className="text-red-600 text-sm">{errors.password}</span>
            )}
          </div>
          <Button type="submit" disabled={isSubmitting}>
            Create account
          </Button>
          <Link to={"/"}>
            Already a member? <span>Login</span>
          </Link>
        </Form>
      )}
    </Formik>
  );
};
