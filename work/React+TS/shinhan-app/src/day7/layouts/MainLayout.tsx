import { NavLink, Outlet } from "react-router-dom";

/**
 * layouts/MainLayout.tsx
 * 세 도메인(직원/게시글/계약) 라우트를 하나의 레이아웃으로 감싼다.
 */
const navClass = ({ isActive }: { isActive: boolean }) =>
  isActive
    ? "rounded-md bg-teal-600 px-3 py-1.5 text-sm font-semibold text-white"
    : "rounded-md px-3 py-1.5 text-sm font-medium text-slate-600 hover:bg-slate-100";

export default function MainLayout() {
  return (
    <div className="min-h-screen bg-slate-50">
      <header className="border-b border-slate-200 bg-white px-6 py-4">
        <div className="mx-auto flex max-w-2xl items-center justify-between">
          <span className="text-sm font-bold text-slate-800">
            라우터 테스트
          </span>
          <nav className="flex gap-1">
            <NavLink to="/" className={navClass} end>
              홈
            </NavLink>
            <NavLink to="/employees" className={navClass}>
              직원
            </NavLink>
            <NavLink to="/posts" className={navClass}>
              게시글
            </NavLink>
            <NavLink to="/contracts" className={navClass}>
              계약
            </NavLink>
          </nav>
        </div>
      </header>

      <main className="mx-auto max-w-2xl p-6">
        <Outlet />
      </main>
    </div>
  );
}
