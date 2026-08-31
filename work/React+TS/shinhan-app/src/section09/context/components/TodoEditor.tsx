import { useContext, useRef, useState } from "react";
import { TodoDispatchContext } from "../contexts/todoStateContext";

const TodoEditor = () => {
  const { onCreate } = useContext(TodoDispatchContext);
  const [content, setContent] = useState("");
  const [writer, setWriter] = useState("");
  const inputRef = useRef<HTMLInputElement>(null);
  const inputWriterRef = useRef<HTMLInputElement>(null);

  const onChangeContent = (e: React.ChangeEvent<HTMLInputElement>) => {
    setContent(e.target.value);
  };
  const onChangeWriter = (e: React.ChangeEvent<HTMLInputElement>) => {
    setWriter(e.target.value);
  };

  const onSubmit = () => {
    if (!content.trim()) {
      inputRef.current?.focus();
      return;
    }
    onCreate(content, writer);
    setContent("");
    setWriter("");
  };

  const onKeyDown = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === "Enter") {
      onSubmit();
    }
  };

  return (
    <div className="bg-white p-6 border-b border-gray-200">
      <h4 className="text-xl font-semibold mb-4 text-gray-800">
        새로운 Todo 작성하기{" "}
      </h4>
      <div className="flex gap-2 max-w-full">
        <input
          ref={inputWriterRef}
          value={writer}
          onChange={onChangeWriter}
          onKeyDown={onKeyDown}
          placeholder="작성자 입력"
          className="flex-1 px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent"
        />
        <input
          ref={inputRef}
          value={content}
          onChange={onChangeContent}
          onKeyDown={onKeyDown}
          placeholder="새로운 Todo..."
          className="flex-1 px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent"
        />
        <button
          onClick={onSubmit}
          className="px-6 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600 transition-colors font-medium"
        >
          추가
        </button>
      </div>
    </div>
  );
};

export default TodoEditor;
