import { useReducer, type ChangeEvent } from "react";

//1.상태타입
type State = {
  count: number;
  lunch?: string;
};
//2.액션타입
type Action =
  | { type: "INCREMENT" }
  | { type: "DECREMENT" }
  | { type: "RESET" }
  | { type: "LUNCH_CHANGE"; menu: string };
//3.초기상태
const initialState: State = {
  count: 0,
  lunch: "아무거나",
};
//4.Reducer함수
function countReducer(state: State, action: Action): State {
  switch (action.type) {
    case "INCREMENT":
      return { ...state, count: state.count + 1 };
    case "DECREMENT":
      return { ...state, count: state.count - 1 };
    case "RESET":
      return initialState;
    case "LUNCH_CHANGE":
      return { ...state, lunch: action.menu };
    default:
      return state;
  }
}

function CounterUsingReducer() {
  // useReducer는 useState의 대체, component의 복잡도를 줄이기 위해
  // [상태를 관리할 변수, 호출 로직 이름] = useReducer(로직, 초기값)
  // `호출로직이름`(값); => 값은 `로직`의 2번째로 전달
  // 상태값은 첫번째 변수값이 countReducer의 1번째로 전달
  const [data, dispatch] = useReducer(countReducer, initialState);
  const { count, lunch } = data;
  const onChangeLunch = (e: ChangeEvent<HTMLInputElement>) => {
    dispatch({ type: "LUNCH_CHANGE", menu: e.target.value });
  };
  const incrementHandler = () => {
    dispatch({ type: "INCREMENT" });
  };
  const decrementHandler = () => {
    dispatch({ type: "DECREMENT" });
  };

  const buttonStyle =
    "mt-5 px-6 py-3 text-xl rounded bg-purple-400 text-gray-650 mr-5 ml-5";
  return (
    <>
      <>
        <div>
          <h1>{count}</h1>
          <button className={buttonStyle} onClick={decrementHandler}>
            -
          </button>
          <button
            className={buttonStyle}
            onClick={() => dispatch({ type: "RESET" })}
          >
            0
          </button>
          <button className={buttonStyle} onClick={incrementHandler}>
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
      <>
        <p>count: {data.count}</p>
        <button
          className="px-4 py-2 bg-blue-500 rounded text-white shadow hover:bg-blue-700 mx-8"
          onClick={incrementHandler}
        >
          증가
        </button>
        <button
          className="px-4 py-2 bg-blue-500 rounded text-white shadow hover:bg-blue-700 mx-8"
          onClick={() => dispatch({ type: "RESET" })}
        >
          reset
        </button>
        <button
          className="px-4 py-2 bg-blue-500 rounded text-white shadow hover:bg-blue-700 mx-8"
          color="green"
          onClick={decrementHandler}
        >
          감소
        </button>
      </>
    </>
  );
}
export default CounterUsingReducer;
