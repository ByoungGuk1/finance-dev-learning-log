import { useRef, useState, type ChangeEvent, type ReactNode } from "react";

type BoardType = {
  bno: number;
  title: string;
  content: string;
  writer: string;
};

const boardDatas: BoardType[] = [
  { bno: 1, title: "title1", content: "content", writer: "writer1" },
  { bno: 2, title: "title2", content: "content", writer: "writer2" },
  { bno: 3, title: "title3", content: "content", writer: "writer3" },
  { bno: 4, title: "title4", content: "content", writer: "writer4" },
  { bno: 5, title: "title5", content: "content", writer: "writer5" },
];

const Board = () => {
  const initBoard = { bno: 0, title: "", content: "", writer: "" };
  const [board, setBoard] = useState(initBoard);
  const [boardList, setBoardList] = useState<BoardType[]>(boardDatas);
  const bnoRef = useRef<number>(boardDatas.length + 1);

  const onChangeInputHandler = (e: ChangeEvent<HTMLInputElement>) => {
    setBoard((prev) =>
      prev ? { ...prev, [e.target.name]: e.target.value } : initBoard,
    );
  };

  const onClickBtn = () => {
    if (!board?.title || !board?.content || !board?.writer) return;
    const newBoard = {
      ...board,
      bno: bnoRef.current++,
    };
    setBoardList((prev) => [...prev, newBoard]);
    setBoard(initBoard);
  };

  const renderBoardList = (boardList: BoardType[]): ReactNode => {
    return boardList.map((board: BoardType, index: number) => (
      <tr key={index} className="mt-6">
        <td>{board.bno}</td>
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
            <th>bno</th>
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
