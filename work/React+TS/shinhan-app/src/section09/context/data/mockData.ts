import type { todoType } from "../types/todo";

export const mockTodo: todoType[] = [
  {
    id: 0,
    content: "리액트 공부하기",
    isDone: false,
    createdDate: new Date().getTime(),
    writer: "테스트 사용자",
  },
  {
    id: 1,
    content: "타입스크립트 배우기",
    isDone: true,
    createdDate: new Date().getTime() - 2 * 24 * 60 * 60 * 1000,
  }, //2일전
  {
    id: 2,
    content: "운동하기",
    isDone: false,
    createdDate: new Date().getTime() - 3 * 24 * 60 * 60 * 1000,
  }, //3일전
];
