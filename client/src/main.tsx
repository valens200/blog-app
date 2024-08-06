import React from "react";
import ReactDOM from "react-dom/client";

import "./styles/index.css";
import "nprogress/nprogress.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { IndexPage } from "./pages";
import { Register } from "./pages/register";
import { Login } from "./pages/login";
import { AddPost } from "./pages/add-post";
import { EditPost } from "./pages/edit-post";
import { PostDetail } from "./pages/post-detail";
import { AuthProvider } from "./hooks/useAuth";
import { Toaster } from "react-hot-toast";
import { createTheme, MantineProvider } from "@mantine/core";
import { ConfirmModal } from "./components/modals/ConfirmModal";
import NotFoundPage from "./pages/NotFoundPage";

const theme = createTheme({});
ReactDOM.createRoot(document.getElementById("root")!).render(
  <MantineProvider theme={theme}>
    <React.StrictMode>
      <Router>
        <AuthProvider>
          <Routes>
            <Route path="/user" element={<IndexPage />} />
            <Route path="/admin" element={<IndexPage />} />
            <Route path="/auth/register" element={<Register />} />
            <Route path="/" element={<Login />} />
            <Route path="/admin/new-post" element={<AddPost />} />
            <Route path="/admin/p/:id/edit" element={<EditPost />} />
            <Route path="/p/:id" element={<PostDetail />} />
            <Route path="*" element={<NotFoundPage />} />
          </Routes>
          <Toaster />
          <ConfirmModal />
        </AuthProvider>
      </Router>
    </React.StrictMode>
  </MantineProvider>
);
