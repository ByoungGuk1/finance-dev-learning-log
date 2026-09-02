import type { Member } from "@/auth/service/TokenService";
import { createContext } from "react";

export type LoginRequest = {
  mid: string;
  mpassword: string;
};

type AuthContextType = {
  user: Member | null;
  token: string | null;
  isLoggedIn: boolean;
  isInitializing: boolean;
  login: (user: LoginRequest) => Promise<void>;
  logout: () => void;
};

//Component에서 export하지않도록 한다.
export const AuthContext = createContext<AuthContextType | null>(null);
