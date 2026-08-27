// 상태관리 : 상태값이 바뀌면 React가 UI를 리랜더링 한다.
// class형 : this.state, this.setState()
// 함수형 : useState(), Hook 함수 이용

import { useState, type ChangeEvent } from "react";

const lunchList = [];
const Counter = () => {
  const [lunch, setLunch] = useState("아무거나");
  const [count, setCount] = useState(0);
  // count = 0의 의미, 값 변경은 반드시 set사용

  const buttonStyle =
    "mt-5 px-6 py-3 text-xl rounded bg-purple-400 text-gray-650 mr-5 ml-5";

  const onClickPlusBtn = () => {
    setCount((prev) => prev + 1);
  };
  const onClickMinusBtn = () => {
    setCount((prev) => prev - 1);
  };
  const onClickResetBtn = () => {
    setCount(0);
  };

  const onChangeLunch = (e: ChangeEvent<HTMLInputElement>) => {
    if (e.target.value === "enter") {
      lunchList.push(lunch);
    }
    setLunch(e.target.value);
  };

  return (
    <>
      <div>
        <h1>{count}</h1>
        <button className={buttonStyle} onClick={onClickMinusBtn}>
          -
        </button>
        <button className={buttonStyle} onClick={onClickResetBtn}>
          0
        </button>
        <button className={buttonStyle} onClick={onClickPlusBtn}>
          +
        </button>
      </div>
      <br />
      <br />
      <div>
        <h2>{lunch}</h2>
        <input onChange={onChangeLunch} value={lunch}></input>
      </div>
    </>
  );
};

export default Counter;
