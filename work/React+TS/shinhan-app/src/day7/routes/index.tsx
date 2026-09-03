import { employeeRoutes } from "./EmployeeRoutes";

import { contractRoutes } from "./ContractRoutes";

import HomePage from "../HomePage";
import { createBrowserRouter } from "react-router-dom";
import MainLayout from "../layouts/MainLayout";
import { postRoutes } from "./PostRoutes";

/**
 * routes/index.tsx
 * 도메인별로 나눠 만든 라우트 배열(employeeRoutes, postRoutes, contractRoutes)을
 * 스프레드(...)로 펼쳐서 하나의 children 배열로 합친다.
 * -> 새 도메인(예: loanRoutes)이 생기면, 여기 한 줄만 추가하면 된다.
 */
export const router = createBrowserRouter([
  {
    path: "/",
    element: <MainLayout />,
    children: [
      { index: true, element: <HomePage /> },
      ...employeeRoutes,
      ...postRoutes,
      ...contractRoutes,
    ],
  },
]);

/*
1.JSX(컴포넌트)로 선언
<BrowserRouter>
  <Routes>
    <Route path="/" element={<MainLayout />}>
      <Route index element={<HomePage />} />  
           //index: true는 부모 경로("/")와 정확히 일치할 때만 보여줄 "기본 화면"
      <Route path="employees" element={<EmployeeListPage />} />
    </Route>
  </Routes>
</BrowserRouter>

2.순수 JS 객체(배열)로 선언
1)라우트 "설정 객체 배열"을 받아서, 실제로 동작하는 라우터 인스턴스를 만든다.
이 배열의 각 원소가 하나의 <Route>에 대응한다.
createBrowserRouter([])
2)
*/
