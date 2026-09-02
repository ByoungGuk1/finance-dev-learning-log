import ApiService, { refreshAccessToken } from "@/auth/service/ApiService";
import TokenService from "@/auth/service/TokenService";
import { useCallback, useEffect, useState } from "react";
import { AuthContext, type LoginRequest } from "@/auth/AuthContext";

export function AuthProvider({ children }: { children: React.ReactNode }) {
  const [user, setUser] = useState(TokenService.getUser());
  const [token, setToken] = useState<string | null>(null);
  const [isInitializing, setIsInitializing] = useState(true);

  const login = async ({ mid, mpassword }: LoginRequest) => {
    const res = await ApiService.post("/auth/login", { mid, mpassword });
    const { accessToken, member } = res.data;
    // 기존 token/user 무조건 초기화
    TokenService.clear();
    TokenService.set(accessToken);
    TokenService.setUser(member);
    setToken(accessToken);
    setUser(member);
  };

  const logout = useCallback(() => {
    TokenService.clear();
    setToken(null);
    setUser(null);
  }, []);

  // accessToken이 메모리에만 있어 새로고침 시 사라지므로,
  // 마운트 시 refreshToken 쿠키로 세션 복구를 한 번 시도한다.
  useEffect(() => {
    refreshAccessToken()
      .then(setToken)
      .catch(() => {
        TokenService.clear();
        setUser(null);
      })
      .finally(() => setIsInitializing(false));
  }, []);
  //isLoggedIn : user와 token이 모두있으면 true

  return (
    <AuthContext.Provider
      value={{
        user,
        token,
        isLoggedIn: !!user && !!token,
        isInitializing,
        login,
        logout,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
}
