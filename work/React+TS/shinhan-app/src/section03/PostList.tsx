const PostList = () => {
  const boardList = [
    { id: 1, title: "Tailwind 연습", writer: "홍길동" },
    { id: 2, title: "React Router 정리", writer: "홍길동" },
  ];

  return (
    <div className="bg-white rounded-lg shadow p-4">
      <h3 className="font-semibold mb-3">최근 게시글</h3>
      <div className="grid grid-cols-12 text-sm font-medium text-gray-500 border-b pb-2 mb-2">
        <span className="col-span-7 text-center">제목</span>
        <span className="col-span-3 text-center">작성자</span>
        <span className="col-span-2 text-center">상세보기</span>
      </div>
      <ul className="space-y-1">
        {boardList.map((board) => (
          <li
            key={board.id}
            className="grid grid-cols-12 items-center py-2 text-sm hover:bg-gray-50 rounded transition"
          >
            <span className="col-span-7 truncate">{board.title}</span>

            <span className="col-span-3 text-center text-gray-600">
              {board.writer}
            </span>

            <button className="col-span-2 text-blue-500 hover:underline text-center">
              보기
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default PostList;
