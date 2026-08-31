import { createContext } from "react";

export type AgeContextType = {
  age: number;
  setAge: (age: number) => void;
};

export const AgeContext = createContext<AgeContextType | null>(null);
