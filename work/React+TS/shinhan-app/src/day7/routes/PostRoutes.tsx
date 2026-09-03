import { type RouteObject } from "react-router-dom";
import PostListPage from "../pages/post/PostListPage";
import PostDetailPage from "../pages/post/PostDetailPage";

export const postRoutes: RouteObject[] = [
  { path: "posts", element: <PostListPage /> },
  { path: "posts/:postId", element: <PostDetailPage /> },
];
