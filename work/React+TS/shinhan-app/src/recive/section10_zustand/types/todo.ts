import { create } from "zustand";
import { mockTodo } from "../data/mockData";

export interface todoType {
  id: number;
  content: string;
  isDone: boolean;
  createdDate: number;
}

interface todoStore {
  //상태
  todo: todoType[];
  idRef: number;
  //액션
  onCreate: (content: string) => void;
  onUpdate: (
    targetId: number,
    colname: "isDone" | "content",
    value?: string,
  ) => void;
  onDelete: (targetId: number) => void;
}

//store이용하기 , zustand
//공통공간에 상태관리, 액션관리, 필요한 컴포넌트에서  가져다 사용
//state +  reducer + hook
export const useTodoStore = create<todoStore>((set) => {
  //console.log("========create========", set, get);
  return {
    todo: mockTodo,
    idRef: 3,
    onCreate: (content) => {
      //console.log("-----onCreate function-----");
      set((state) => ({
        todo: [
          ...state.todo,
          {
            id: state.idRef++,
            content: content,
            isDone: false,
            createdDate: new Date().getTime(),
          },
        ],
      }));
    }, //onCreate end
    onUpdate: (targetId, colname, value) => {
      //console.log("-----onUpdate function-----");
      set((state) => ({
        todo: state.todo.map((it) => {
          if (it.id === targetId) {
            if (colname === "isDone") return { ...it, isDone: !it.isDone };
            if (colname === "content" && value)
              return { ...it, content: value };
          }
          return it;
        }),
      }));
    }, //onUpdate end
    onDelete: (targetId) => {
      //console.log("-----onDelete function-----");
      set((state) => ({ todo: state.todo.filter((it) => it.id !== targetId) }));
    },
  }; //useTodoStore create return end
});
