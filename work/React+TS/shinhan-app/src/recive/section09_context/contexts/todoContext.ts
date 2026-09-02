import { createContext } from "react";
import type { todoDispatchContextType, todoType } from "../types/todo";

export const TodoStateContext = createContext<{ todo: todoType[] }>({
  todo: [],
});
export const TodoDispatchContext = createContext<todoDispatchContextType>({
  onCreate: () => {},
  onUpdate: () => {},
  onDelete: () => {},
});
