import { Link } from "react-router-dom";

const posts = [
  { id: "1", title: "React Router 수업 공지" },
  { id: "2", title: "9월 사내 세미나 안내" },
  { id: "3", title: "TypeScript 마이그레이션 후기" },
];

export default function PostListPage() {
  return (
    <div>
      <h1 className="mb-3 text-lg font-bold text-slate-800">게시글 목록</h1>
      <ul className="space-y-2">
        {posts.map((p) => (
          <li key={p.id}>
            <Link
              to={`/posts/${p.id}`}
              className="block rounded-lg border border-slate-200 bg-white p-3 text-sm hover:border-teal-400"
            >
              {p.title}
            </Link>
          </li>
        ))}
      </ul>
    </div>
  );
}
