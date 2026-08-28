import { useState, type ChangeEvent } from "react";

type PropsType = {
  title: string;
  MAX_SCORE?: number;
  options?: { size: string; color: string };
  clickHandler?: () => void;
};

// 기본 data type : 약한 비교
// 객체 data type : 깊은 비교
const ChildComponent = ({
  title,
  MAX_SCORE,
  options,
  clickHandler,
}: PropsType) => {
  const [score, setScore] = useState<number>(100);
  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    setScore(Number(e.target.value));
  };
  console.log("2.ChildComponent 렌더링");

  return (
    <>
      <div className="border px-4 py-2">
        <h1>ChildComponent</h1>
        <input
          className="border-2 border-blue-500"
          type="number"
          value={score}
          onChange={handleChange}
        />
        <p>당신이 입력한 점수 : {score} </p>
        <p>부모에게서 받음 : {title} </p>
        {MAX_SCORE ? <p>부모에게서 받음 : {MAX_SCORE} </p> : <></>}
        {options ? (
          <>
            <p>{options.size}</p>
            <p>{options.color}</p>
          </>
        ) : (
          <></>
        )}
        {clickHandler ? (
          <button onClick={clickHandler}>자식이 부모의 함수 사용</button>
        ) : (
          <></>
        )}
      </div>
    </>
  );
};

export default ChildComponent;
