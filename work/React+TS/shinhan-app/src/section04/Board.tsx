import { useState, type ChangeEvent, type ReactNode } from "react";

type BoardType = {
  title: string;
  content: string;
  writer: string;
};

const Board = () => {
  const initBoard = { title: "", content: "", writer: "" };
  const [board, setBoard] = useState(initBoard);
  const [boardList, setBoardList] = useState<BoardType[]>([]);

  const onChangeInputHandler = (e: ChangeEvent<HTMLInputElement>) => {
    setBoard((prev) =>
      prev ? { ...prev, [e.target.name]: e.target.value } : initBoard,
    );
  };

  const onClickBtn = () => {
    if (!board?.title || !board?.content || !board?.writer) return;
    setBoardList((prev) => [...prev, board]);
    console.log(boardList);
    setBoard(initBoard);
  };

  const renderBoardList = (boardList: BoardType[]): ReactNode => {
    return boardList.map((board: BoardType, index: number) => (
      <tr key={index} className="mt-6">
        <td>{board.title}</td>
        <td>{board.content}</td>
        <td>{board.writer}</td>
      </tr>
    ));
  };

  return (
    <>
      <div className="max-w-sm mx-auto p-4 space-y-4 bg-white rounded shadow">
        <h2 className="text-lg font-semibold">사용자 등록</h2>
        <input
          type="text"
          name="title"
          placeholder="제목"
          value={board.title}
          className="w-full border px-3 py-2 rounded"
          onChange={onChangeInputHandler}
        />
        <input
          type="text"
          name="content"
          value={board.content}
          placeholder="본문"
          className="w-full border px-3 py-2 rounded"
          onChange={onChangeInputHandler}
        />
        <input
          type="text"
          name="writer"
          value={board.writer}
          placeholder="작성자"
          className="w-full border px-3 py-2 rounded"
          onChange={onChangeInputHandler}
        />
        <button type="button" onClick={onClickBtn}>
          추가
        </button>
      </div>
      <table>
        <thead>
          <tr>
            <th>제목</th>
            <th>본문</th>
            <th>작성자</th>
          </tr>
        </thead>
        <tbody>{renderBoardList(boardList)}</tbody>
      </table>
    </>
  );
};

export default Board;
