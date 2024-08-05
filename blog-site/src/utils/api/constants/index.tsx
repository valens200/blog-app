import _ from "axios";

const backend = "http://localhost:8000/api/v1";

export const api = _.create({
  baseURL: backend,
});

export const authApi = _.create({
  baseURL: backend,
  headers: {
    Authorization: `Bearer ${localStorage.getItem("token")}`,
  },
});
