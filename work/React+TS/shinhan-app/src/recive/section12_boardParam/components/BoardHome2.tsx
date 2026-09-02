import { Link, Outlet } from "react-router-dom";

export default function BoardHome2() {
  return (
    <div>
      <h1>Board CRUD</h1>
      <Link
        to={""}
        className="px-4 py-2 bg-green-500 text-white rounded-lg  no-underline text-sm font-medium"
      >
        목록
      </Link>
      <Link
        to={"new"}
        className="px-4 py-2 bg-pink-500 text-white rounded-lg  no-underline text-sm font-medium"
      >
        신규작성
      </Link>

      {/* 자식영역 */}
      <Outlet />
    </div>
  );
}
