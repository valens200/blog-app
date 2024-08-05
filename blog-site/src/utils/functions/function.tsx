/* eslint-disable @typescript-eslint/no-explicit-any */
import { useNavigate } from "react-router-dom";
import { ErrorResponse, UserRole } from "../../types";
import { jwtDecode } from "jwt-decode";

export const getErrorFromResponse = (error: any): string => {
  if (!error.response) return "Network Error";
  const errorData: ErrorResponse = error.response.data;
  return errorData.message;
};

export const getErrorFromResponseData = (error: any): string => {
  if (!error.response) return "Network Error";
  return error.response.data.message;
};

export const capitalize = (str: string): string => {
  return str.charAt(0).toUpperCase() + str.slice(1);
};

export const authentiCate = () => {
  const navigate = useNavigate();

  const token = localStorage.getItem("token");
  if (!token) navigate("/");
};

export const getUseRoute = (role: UserRole): string => {
  switch (role) {
    case UserRole[UserRole.USER]:
      return "/user";
    case UserRole[UserRole.ADMIN]:
      return "/admin";
    default:
      return "/";
  }
};

export const decodeToken = (token: string): any => {
  try {
    const data = jwtDecode(token);
    return data;
  } catch (e) {
    return null;
  }
};
