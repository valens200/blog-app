/**
 * Creates a react-router-dom BrowserRouter for all page
 */

import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
  RouterProvider,
} from "react-router-dom";

import { IndexPage } from "./pages";
import { AddPost } from "./pages/add-post";
import { EditPost } from "./pages/edit-post";
import { PostDetail } from "./pages/post-detail";
import { Register } from "./pages/register";
import { Login } from "./pages/login";
import { AuthProvider } from "./hooks/useAuth";

// Create router from JSX.Element
const router = createBrowserRouter(
  createRoutesFromElements(
    <Route>
      <Route path="/" element={<IndexPage />} />
      <Route path="/register" element={<Register />} />
      <Route path="/login" element={<Login />} />
      <Route path="/new-post" element={<AddPost />} />
      <Route path="/p/:id/edit" element={<EditPost />} />
      <Route path="/p/:id" element={<PostDetail />} />
    </Route>
  )
);

export default function Routes() {
  return <RouterProvider router={router} />;
}
