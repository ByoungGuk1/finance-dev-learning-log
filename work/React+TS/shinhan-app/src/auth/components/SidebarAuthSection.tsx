import { useNavigate } from "react-router-dom";
import { useAuth } from "@/auth/useAuth";

export const SidebarAuthSection = () => {
  const { isLoggedIn, logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate("/", { replace: true });
  };

  return (
    <div className="mt-auto border-t border-slate-700 pt-4 text-sm">
      {isLoggedIn ? (
        <button
          onClick={handleLogout}
          className="w-full text-left px-2 py-2 rounded
            hover:bg-slate-700 text-red-300"
        >
          Logout
        </button>
      ) : (
        <button
          onClick={() => navigate("/auth")}
          className="w-full text-left px-2 py-2 rounded
            hover:bg-slate-700 text-green-300"
        >
          Login
        </button>
      )}

      <div className="mt-2 text-xs text-gray-400">CSR / Router 실습</div>
    </div>
  );
};
