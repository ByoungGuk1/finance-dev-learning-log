import { useContext } from "react";
import { AgeContext } from "../contexts/AgeContext";

const useAge = () => {
  const context = useContext(AgeContext);
  if (!context) {
    throw new Error("useAge는 AgeProvider 안에서만 사용해야 합니다.");
  }
  return context;
};

export default useAge;

//훅을 만들지않으면 매번 다음코드 :
// const context = useContext(AgeContext); 와 오류처리추가후 구조분해
// const { age, setAge } = context;
//훅을 만들면 간단 : const { age, setAge } = useAge();
