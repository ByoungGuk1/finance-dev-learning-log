import { create } from "zustand";
import type { todoType } from "../types/todo";
import { mockTodo } from "../data/mockData";

type TodoStore = {
  todo: todoType[];
  idRef: number;

  onCreate: (content: string) => void;
  onUpdate: (
    targetId: number,
    colname: "isDone" | "content",
    value?: string,
  ) => void;
  onDelete: (targetId: number) => void;
};

const useTodoStore = create<TodoStore>((set) => ({
  todo: mockTodo,
  idRef: 3,

  onCreate: (content) => {
    console.log("생성");
    set((state) => ({
      todo: [
        ...state.todo,
        {
          id: state.idRef,
          content: content,
          isDone: false,
          createdDate: new Date().getTime(),
        },
      ],
      idRef: state.idRef + 1,
    }));
  },
  onUpdate: (targetId, colname, value) => {
    console.log("수정");
    set((state) => ({
      todo: state.todo.map((item) => {
        if (item.id === targetId) {
          if (colname === "isDone") {
            return { ...item, isDone: !item.isDone };
          }
          if (colname === "content" && value) {
            return { ...item, content: value };
          }
        }
        return item;
      }),
    }));
  },
  onDelete: (targetId) => {
    set((state) => ({
      todo: state.todo.filter((item) => item.id !== targetId),
    }));
  },
}));

export default useTodoStore;
