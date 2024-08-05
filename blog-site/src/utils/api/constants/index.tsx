import _ from "axios";
import image from "../../../assets/AI-DarkMode-2-1.png";
import image2 from "../../../assets/image2.png";

const backend = "http://localhost:8000/api/v1";

export const api = _.create({
  baseURL: backend,
});
export const images = {
  image1: image,
  image2: image2,
};

export const authApi = _.create({
  baseURL: backend,
  headers: {
    Authorization: `Bearer ${localStorage.getItem("token")}`,
  },
});
