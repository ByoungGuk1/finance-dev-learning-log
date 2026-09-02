import { BASE_URL } from "@/common/util";
import axios from "axios";
import { Link, Outlet } from "react-router-dom";

export default function BoardHome2() {
  const axiosTestBtn = () => {
    axios({
      method: "GET",
      url: `https://jsonplaceholder.typicode.com/posts`,
    })
      .then(console.log)
      .catch(console.error);
  };

  /**
   * errer-message
   * => Access to XMLHttpRequest at 'http://localhost:8000/api/freeboard/list' from origin 'http://localhost:5173' has been blocked by CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource.
   */
  const axiosTestBtnErr = () => {
    axios({
      method: "GET",
      url: `${BASE_URL}/freeboard/list`,
    })
      .then(({ data }) => console.log(data))
      .catch(console.error);
  };

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

      <button
        className="px-4 py-2 bg-purple-400 text-white rounded-lg  no-underline text-sm font-medium"
        onClick={axiosTestBtn}
      >
        Axios 버튼 테스트
      </button>
      <button
        className="px-4 py-2 bg-purple-400 text-white rounded-lg  no-underline text-sm font-medium"
        onClick={axiosTestBtnErr}
      >
        Axios 버튼 테스트 (cors 오류 확인)
      </button>

      {/* 자식영역 */}
      <Outlet />
    </div>
  );
}
