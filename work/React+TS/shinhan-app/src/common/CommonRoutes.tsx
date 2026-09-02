import { Route, Routes } from "react-router-dom";

import TodoListApp from "@/recive/section10_zustand/components/TodoListApp";
import { HomePage } from "@/common/HomePage";
import NotFoundPage from "@/common/NotFoundPage";
import App3 from "@/section01/App3";
import Section2Start from "@/section02/Section02Start";
import PageLayout from "@/section03/PageLayout";
import CounterApp from "@/section04/CounterApp";
import LifeCycleParent from "@/section04/Section04Start";
import Lab1_AccountOpenForm from "@/section05/Section05Start";

import Debounce_ProfileEditor from "@/section08/Section08Start";
import Lab2_AccountTransaction from "@/lab/day04/lab2/AccountTransaction";
import BoardHome from "@/recive/section12_boardParam/components/BoardHome";
import BoardHomeAxios from "@/section13/components/BoardHomeAxios";
import { AuthLayout } from "@/auth/components/AuthLayout";
import AuthPage from "@/auth/AuthPage";

export default function CommonRoutes() {
  return (
    <>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/day1" element={<App3 />} />
        <Route path="/day2" element={<Section2Start />} />
        <Route path="/day3" element={<PageLayout />} />
        <Route path="/day4" element={<CounterApp />} />
        <Route path="/lifecycle" element={<LifeCycleParent />} />
        <Route path="/lab1" element={<Lab1_AccountOpenForm />} />
        <Route path="/lab2" element={<Lab2_AccountTransaction />} />
        <Route path="/debounce" element={<Debounce_ProfileEditor />} />
        <Route path="/todo" element={<TodoListApp />} />

        {/* 로그인하지 않아도 접근할 수 있는 로그인/회원가입 화면 */}
        <Route path="/auth/*" element={<AuthPage />} />

        {/* 로그인해야 접근할 수 있는 화면 */}
        <Route element={<AuthLayout />}>
          <Route path="/board/*" element={<BoardHome />} />
          <Route path="/axios/*" element={<BoardHomeAxios />} />
        </Route>
        <Route path="*" element={<NotFoundPage />} />
      </Routes>
    </>
  );
}
