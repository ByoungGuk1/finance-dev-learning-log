import { type RouteObject } from "react-router-dom";
import EmployeeListPage from "../pages/employee/EmployeeListPage";
import EmployeeDetailPage from "../pages/employee/EmployeeDetailPage";

// 직원 도메인과 관련된 라우트만 여기에 모은다.
export const employeeRoutes: RouteObject[] = [
  { path: "employees", element: <EmployeeListPage /> },
  { path: "employees/:employeeId", element: <EmployeeDetailPage /> },
];
