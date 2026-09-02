import { useCallback, useMemo, useRef, useState } from "react";
import TodoEditor from "./TodoEditor";
import TodoList from "./TodoList";
import type { todoType } from "../types/todo";
import { mockTodo } from "../data/mockData";
import { TodoDispatchContext, TodoStateContext } from "../contexts/todoContext";
import TodoHeader from "./TodoHeader";

export default function TodoListApp() {
  const [todo, setTodo] = useState<todoType[]>(mockTodo);
  const idRef = useRef(3);

  const onCreate = useCallback((content: string) => {
    const newItem: todoType = {
      id: idRef.current,
      content,
      isDone: false,
      createdDate: new Date().getTime(),
    };

    setTodo((prev) => [newItem, ...prev]);
    idRef.current += 1;
  }, []);

  const onUpdate = useCallback(
    (targetId: number, colname: "isDone" | "content", value?: string) => {
      console.log("수정~");
      setTodo((prev) =>
        prev.map((it) => {
          if (it.id === targetId) {
            if (colname === "isDone") {
              return { ...it, isDone: !it.isDone };
            } else if (colname === "content" && value) {
              return { ...it, content: value };
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
        <div className="min-h-screen w-full  bg-gradient-to-br from-blue-50 to-purple-50 p-8">
          <div className="max-w-4xl mx-auto shadow-2xl rounded-2xl overflow-hidden">
            <TodoHeader />
            <TodoEditor />
            <TodoList />
          </div>
        </div>
      </TodoDispatchContext.Provider>
    </TodoStateContext.Provider>
  );
}
