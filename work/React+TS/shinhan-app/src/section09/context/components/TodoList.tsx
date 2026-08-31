import { useContext, useMemo, useState } from "react";
import { TodoStateContext } from "../contexts/todoStateContext";
import TodoItem from "./TodoItem";
import { useDebounce } from "../contexts/useDebounce";
import RenderTableRow from "./RenderTableRow";

const TodoList = () => {
  const { todo } = useContext(TodoStateContext);
  const [search, setSearch] = useState("");
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

      {todo.length !== 0 ? (
        <div className="flex justify-center w-full">
          <table>
            <thead>
              <tr>
                <th>제목</th>
                <th>작성일</th>
                <th>완료 여부</th>
              </tr>
            </thead>
            <tbody>
              {todo.map((data, index) => (
                <RenderTableRow key={index} data={data} />
              ))}
            </tbody>
          </table>
        </div>
      ) : (
        "데이터 없음"
      )}
    </div>
  );
};

export default TodoList;
