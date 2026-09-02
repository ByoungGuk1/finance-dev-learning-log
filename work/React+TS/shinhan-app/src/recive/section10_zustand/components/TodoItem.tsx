import React, { useEffect, useRef, useState } from "react";
import { useTodoStore, type todoType } from "../types/todo";

import { useDebounce } from "../../../section08/useDebounce";

//Component에 속성전달
function TodoItem({ id, content, isDone, createdDate }: todoType) {
  //const { onUpdate, onDelete } = useContext(TodoDispatchContext);

  const onUpdate = useTodoStore((state) => state.onUpdate);
  const onDelete = useTodoStore((state) => state.onDelete);

  const [updateContent, setUpdateContent] = useState(content);
  const onChangeCheckbox = () => {
    onUpdate(id, "isDone");
  };
  const onClickDelete = () => {
    onDelete(id);
  };
  const contentHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value;
    setUpdateContent(value);
  };

  // 디바운스된 값: 타이핑 멈추고 500ms 후 확정
  const debouncedContent = useDebounce(updateContent, 500);

  //seRef는 컴포넌트 인스턴스마다 별도로 생성되므로, TodoItem 컴포넌트 안에 아래처럼 넣으면 각 아이템이 자기 자신의 "첫 실행"만 건너뛰고, 이후 진짜로 사용자가 입력을 바꿨을 때만 onUpdate가 호출
  const isFirstRun = useRef(true);
  // 확정된 값이 바뀔 때만 상위로 전달
  useEffect(() => {
    if (isFirstRun.current) {
      isFirstRun.current = false;
      return;
    }
    if (debouncedContent.trim()) {
      onUpdate(id, "content", debouncedContent);
    }
  }, [debouncedContent, id, onUpdate]);

  return (
    <div className="flex items-center gap-4 p-4 bg-white border border-gray-200 rounded-lg hover:shadow-md transition-shadow">
      <div className="flex items-center">
        <input
          onChange={onChangeCheckbox}
          checked={isDone}
          type="checkbox"
          className="w-5 h-5 text-blue-500 rounded focus:ring-2 focus:ring-blue-500 cursor-pointer"
        />
      </div>
      <div className="flex-1 flex gap-2">
        <input
          value={updateContent}
          onChange={contentHandler}
          className={`flex-1 px-3 py-1 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-500 ${
            isDone ? "line-through text-gray-400" : "text-gray-800"
          }`}
        />

        <div className="text-sm text-gray-500 min-w-[100px]">
          {new Date(createdDate).toLocaleDateString()}
        </div>

        <button
          onClick={onClickDelete}
          className="px-4 py-1 bg-red-500 text-white rounded hover:bg-red-600 transition-colors text-sm font-medium"
        >
          삭제
        </button>
      </div>
    </div>
  );
}

export default React.memo(TodoItem);
