import { RouterProvider } from "react-router-dom";
import { router } from "./routes"; //파일인가?폴더인가? 폴더아래의 index.ts

// src/App.tsx
export default function AppDay7() {
  return <RouterProvider router={router} />;
}

//RouterProvider : 미리 만들어둔 router 객체를 실제 React 트리에 "연결"하는 컴포넌트.
