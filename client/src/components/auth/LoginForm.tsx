import { Form, Formik } from "formik";
import { Link, useNavigate } from "react-router-dom";

import { Input } from "../ui/input";
import { Button } from "../ui/button";
import { api } from "@/utils/api/constants";
import {
  getErrorFromResponseData,
  getUseRoute,
} from "@/utils/functions/function";
import toast from "react-hot-toast";

interface Props {}

// Form values types
interface InitialFormValues {
  email: string;
  password: string;
}

export const LoginForm: React.FC<Props> = () => {
  const navigate = useNavigate();

  return (
    <Formik<InitialFormValues>
      initialValues={{ password: "", email: "" }}
      validate={(values) => {
        const errors: { [key: string]: string } = {};

        // Email validation using Regexes
        if (!values.email) {
          errors.email = "Required";
        } else if (
          !/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(values.email)
        ) {
          errors.email = "Invalid email address";
        }

        // Password length validation
        if (!values.password) {
          errors.password = "Required";
        } else if (!/^.{8,}$/i.test(values.password)) {
          errors.password = "Password must be atleast 8 characters long";
        }

        return errors;
      }}
      onSubmit={async (values, { setSubmitting }) => {
        try {
          setSubmitting(true);
          setTimeout(() => {}, 1000);
          const response = await api.post("/auth/login", {
            email: values.email,
            password: values.password,
          });
          localStorage.removeItem("token");
          localStorage.removeItem("user");
          localStorage.setItem("token", response.data.data.token);
          localStorage.setItem("user", JSON.stringify(response.data.data.user));
          const route: string = getUseRoute(
            response.data.data.user.roles[0].roleName
          );
          navigate(route);
          toast.success("Wellcome!! you have loggedIn successfully!!");
        } catch (error) {
          toast.error(getErrorFromResponseData(error));
        } finally {
          setSubmitting(false);
        }
      }}
    >
      {({ handleSubmit, handleChange, values, errors, isSubmitting }) => (
        <Form className="flex flex-col gap-4 w-full" onSubmit={handleSubmit}>
          <div className="w-full flex flex-col gap-2">
            <label className="text-sm text-muted-foreground">Email</label>
            <Input
              value={values.email}
              type="email"
              name="email"
              onChange={handleChange}
              placeholder="eg: kent@gmail.com"
            />
            {errors.email && (
              <span className="text-red-600 text-sm">{errors.email}</span>
            )}
            <label className="text-sm text-muted-foreground">Password</label>
            <Input
              value={values.password}
              name="password"
              onChange={handleChange}
              type="password"
              placeholder="*******"
            />
            {errors.password && (
              <span className="text-red-600 text-sm">{errors.password}</span>
            )}
          </div>
          <Button type="submit" disabled={isSubmitting}>
            Log in
          </Button>

          <Link to={"/auth/register"}>
            Don't have account? <span>Sign up</span>
          </Link>
        </Form>
      )}
    </Formik>
  );
};
