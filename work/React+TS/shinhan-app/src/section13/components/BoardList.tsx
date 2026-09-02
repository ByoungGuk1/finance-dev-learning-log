import { Link } from "react-router-dom";
import useBoardStore from "../types/boardType";
import BoardItem from "./BoardItem";
import { useEffect } from "react";

export default function BoardList() {
  const posts = useBoardStore((state) => state.posts);
  const fetchPosts = useBoardStore((state) => state.fetchPosts);

  useEffect(() => {
    if (posts.length === 0) fetchPosts();
  }, []);

  return (
    <div className="bg-white p-6 rounded-2xl shadow-lg">
      <h4 className="text-xl font-bold mb-4 text-gray-800 flex items-center gap-2 flex-wrap">
        <span>📄</span>
        <span>게시글 목록</span>
        <span className="text-sm font-normal text-gray-500">
          {/* 선택된게시글: {selectedTitle} */}
        </span>
      </h4>

      <Link
        to="/axios/new"
        className="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600  text-sm font-medium"
      >
        새 글쓰기
      </Link>

      <div className="space-y-2">
        {posts !== undefined && posts.length > 0 ? (
          posts.map((it) => <BoardItem key={it.bno} {...it} />)
        ) : (
          <div className="text-center py-8 text-gray-400">
            게시글이 없습니다
          </div>
        )}
      </div>
    </div>
  );
}
