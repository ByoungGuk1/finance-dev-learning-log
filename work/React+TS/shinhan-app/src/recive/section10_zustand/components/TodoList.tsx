import { useMemo, useState } from "react";

import { useDebounce } from "../../../section08/useDebounce";

import TodoItem from "./TodoItem";
import { useTodoStore } from "../types/todo";

function TodoList() {
  //const { todo } = useContext(TodoStateContext);
  const todo = useTodoStore((state) => state.todo);

  const [search, setSearch] = useState<string>("");
  const debouncedSearch = useDebounce(search, 300);

  const onChangeSearch = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearch(e.target.value);
  };

  const filteredTodos = useMemo(() => {
    if (!debouncedSearch) return todo;
    return todo.filter((it) =>
      it.content.toLowerCase().includes(debouncedSearch.toLowerCase()),
    );
  }, [todo, debouncedSearch]);

  return (
    <div className="bg-white p-6 rounded-b-2xl">
      <h4 className="text-xl font-semibold mb-4 text-gray-800">Todo List </h4>
      <input
        value={search}
        onChange={onChangeSearch}
        placeholder="검색어를 입력하세요"
        className="w-full px-4 py-2 mb-4 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent"
      />
      <div className="space-y-3">
        {filteredTodos.length > 0 ? (
          filteredTodos.map((it) => <TodoItem key={it.id} {...it} />)
        ) : (
          <div className="text-center py-8 text-gray-400">
            {search ? "검색 결과가 없습니다" : "Todo를 추가해보세요!"}
          </div>
        )}
      </div>
    </div>
  );
}

export default TodoList;
