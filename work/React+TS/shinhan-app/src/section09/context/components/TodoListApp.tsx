import { useCallback, useMemo, useRef, useState } from "react";
import type { todoType } from "../types/todo";
import { mockTodo } from "../data/mockData";
import {
  TodoDispatchContext,
  TodoStateContext,
} from "../contexts/todoStateContext";
import Header from "./Header";
import TodoEditor from "./TodoEditor";
import TodoList from "./TodoList";

const TodoListApp = () => {
  const [todo, setTodo] = useState<todoType[]>(mockTodo);
  const idRef = useRef(3);

  const onCreate = useCallback((content: string, writer: string) => {
    const newItem: todoType = {
      id: idRef.current,
      content,
      isDone: false,
      createdDate: new Date().getTime(),
      writer: writer,
    };

    setTodo((prev) => [newItem, ...prev]);
    idRef.current += 1;
  }, []);

  const onUpdate = useCallback(
    (
      targetId: number,
      colname: "isDone" | "content" | "writer",
      value?: string,
    ) => {
      console.log("수정~");
      setTodo((prev) =>
        prev.map((it) => {
          if (it.id === targetId) {
            if (colname === "isDone") {
              return { ...it, isDone: !it.isDone };
            } else if (colname === "content" && value) {
              return { ...it, content: value };
            } else if (colname === "writer" && value) {
              return { ...it, writer: value };
            }
          }
          return it;
        }),
      );
    },
    [],
  );

  const onDelete = useCallback((targetId: number) => {
    setTodo((prev) => prev.filter((it) => it.id !== targetId));
  }, []);

  const dispatch = useMemo(
    () => ({ onCreate, onUpdate, onDelete }),
    [onCreate, onUpdate, onDelete],
  );

  return (
    <TodoStateContext.Provider value={{ todo }}>
      <TodoDispatchContext.Provider value={dispatch}>
        <div className="w-full  bg-gradient-to-br from-blue-50 to-purple-50 p-8">
          <div className="max-w-4xl mx-auto shadow-2xl rounded-2xl overflow-auto">
            <Header />
            <TodoEditor />
            <TodoList />
          </div>
        </div>
      </TodoDispatchContext.Provider>
    </TodoStateContext.Provider>
  );
};

export default TodoListApp;
