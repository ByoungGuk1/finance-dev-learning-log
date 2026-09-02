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
        <Route path="/board/*" element={<BoardHome />} />

        {/* 로그인 필요한 라우트들을 여기 감싸서 묶음 */}

        {/* <Route path="/diary/*" element={<Day6Lab3App />} /> */}

        <Route path="*" element={<NotFoundPage />} />
      </Routes>
    </>
  );
}
