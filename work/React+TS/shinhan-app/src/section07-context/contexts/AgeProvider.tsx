import { useState, type ReactNode } from "react";
import { AgeContext, type AgeContextType } from "./AgeContext";

type AgeProviderProps = {
  children: ReactNode;
};

const AgeProvider = ({ children }: AgeProviderProps) => {
  const [age, setAge] = useState<number>(20);

  const value: AgeContextType = {
    age,
    setAge,
  };
  return <AgeContext.Provider value={value}>{children}</AgeContext.Provider>;
};

export default AgeProvider;
