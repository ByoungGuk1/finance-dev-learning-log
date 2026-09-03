import { type RouteObject } from "react-router-dom";
import ContractListPage from "../pages/contract/ContractListPage";
import ContractDetailPage from "../pages/contract/ContractDetailPage";

export const contractRoutes: RouteObject[] = [
  { path: "contracts", element: <ContractListPage /> },
  { path: "contracts/:contractId", element: <ContractDetailPage /> },
];
