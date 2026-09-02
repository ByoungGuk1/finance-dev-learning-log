import { Link } from "react-router-dom";
import logo from "../assets/react.svg";
import vite from "../assets/vite.svg";

export default function LinkComponent() {
  return (
    <>
      {/* ===== Sidebar ====  public폴더의 이미지는 경로 직접사용(그대로배포),
        src폴더의 이미지는 import하여사용(build시 번들링됨)  */}
      <aside className="w-72 shrink-0 bg-slate-800 text-white flex flex-col p-4">
        {" "}
        <div className="flex items-center gap-2 mb-6">
          {/* public/images */}
          <img src="images/chat.png" alt="chat" className="w-8 h-8 rounded" />
          <img src={logo} alt="logo" className="w-8 h-8 rounded" />
          <img src={vite} alt="vite" className="w-8 h-8 rounded" />
          <span className="text-lg font-bold">React Lab</span>
        </div>
        <nav className="flex flex-col gap-2 text-sm">
          <Link to="/" className="sidebar-link">
            Home
          </Link>
          <Link to="/day1" className="sidebar-link">
            day1
          </Link>
          <Link to="/day2" className="sidebar-link">
            day2
          </Link>
          <Link to="/day3" className="sidebar-link">
            day3
          </Link>
          <Link to="/day4" className="sidebar-link">
            day4
          </Link>
          <Link to="/lifecycle" className="sidebar-link">
            lifecycle
          </Link>
          <Link to="/lab1" className="sidebar-link">
            lab1
          </Link>
          <Link to="/lab2" className="sidebar-link">
            Lab2
          </Link>
          <Link to="/debounce" className="sidebar-link">
            debounce
          </Link>
          <Link to="/post" className="sidebar-link">
            post(mock)
          </Link>
          <Link to="/todo" className="sidebar-link">
            todo(zustand)
          </Link>
          <Link to="/board" className="sidebar-link">
            Board(param연습)
          </Link>
          <Link to="/axios" className="sidebar-link">
            Board(axios연습)
          </Link>
        </nav>
        <div className="mt-auto text-xs text-gray-400">CSR / Router 실습</div>
      </aside>
    </>
  );
}
