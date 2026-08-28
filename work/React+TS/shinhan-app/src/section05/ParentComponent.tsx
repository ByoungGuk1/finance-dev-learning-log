import { useCallback, useEffect, useMemo, useState } from "react";
import ChildComponent from "./ChildComponent";

const ParentComponent = () => {
  console.log("1.ParentComponent 렌더링");
  const [userName, setUerName] = useState<string>("");
  const MAX_SCORE = 100;
  type OptionType = { size: string; color: string };
  // useMemo<리턴타입>(()=>(),[])
  const options: OptionType = useMemo(
    () => ({ size: "large", color: "blue" }),
    [],
  );
  // 함수도 객체처럼 리랜더링시 다시 생성
  // 이를 막기 위해 useCallback
  const clickHandler = useCallback(() => {
    console.log("부모가 정의, 자식에게 전달");
  }, []);
  useEffect(() => {
    console.log("랜더링시 함수가 재생성");
  }, [clickHandler]);
  return (
    <>
      <div className="border px-4 py-2 m-10">
        <h1>ParentComponent : {userName}</h1>
        이름 :
        <input
          className="border-2 border-blue-500"
          onChange={(e) => setUerName(e.target.value)}
        />
        <ChildComponent
          title="A"
          MAX_SCORE={MAX_SCORE}
          options={options}
          clickHandler={clickHandler}
        />
        <ChildComponent title="B" />
      </div>
    </>
  );
};

/*
  자식이 변경되면 자식만 리랜더링
  부모가 변경되면 자식도 리랜더링
    => 단순 타입의 속성을 전달하는 경우 메모이제이션 사용 React.memo()
    => 객체 타입의 속성을 전달하는 경우 useMemo(f,[])
    => 함수 타입의 속성을 전달하는 경우 useCallback(f,[])
*/

export default ParentComponent;
