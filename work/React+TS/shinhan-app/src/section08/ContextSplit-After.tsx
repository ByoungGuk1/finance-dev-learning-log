// AFTER: State Context와 Dispatch Context를 분리해서 관리
// → count(state)가 바뀌어도 dispatch만 구독하는 Controls는 리렌더링되지 않는다

import {
  createContext,
  useContext,
  useReducer,
  type Context,
  type Dispatch,
  type ReactNode,
} from "react";
import type { Action, State } from "./ContextSplit-Before";

// state 전용 Context와 dispatch 전용 Context를 별개로 분리
const CountStateContext = createContext<State | null>(null);
const CountDispatchContext = createContext<Dispatch<Action> | null>(null);

function reducer(state: State, action: Action): State {
  switch (action.type) {
    case "INCREMENT":
      return { ...state, count: state.count + 1 };
    case "DECREMENT":
      return { ...state, count: state.count - 1 };
    case "SET_NAME":
      return { ...state, name: action.payload };
    default:
      return state;
  }
}

function CountProvider({ children }: { children: ReactNode }) {
  const [state, dispatch] = useReducer(reducer, { count: 0, name: "아무개" });

  // dispatch는 useReducer가 반환하는 값으로, 컴포넌트가 재렌더링돼도
  // "참조가 바뀌지 않는다"는 것이 이 최적화가 성립하는 핵심 이유
  return (
    <CountStateContext.Provider value={state}>
      <CountDispatchContext.Provider value={dispatch}>
        {children}
      </CountDispatchContext.Provider>
    </CountStateContext.Provider>
  );
}

// "null 체크 + throw" 반복을 제네릭 팩토리 함수 하나로 정리
function createContextHook<T>(context: Context<T | null>, hookName: string) {
  return function useSafeContext(): T {
    const ctx = useContext(context);
    if (ctx === null) {
      throw new Error(`${hookName} must be used within CountProvider`);
    }
    return ctx;
  };
}
const useCountState = createContextHook(CountStateContext, "useCountState");
const useCountDispatch = createContextHook(
  CountDispatchContext,
  "useCountDispatch",
);

function Display() {
  const state = useCountState(); // ← StateContext만 구독
  console.log("[After] Display 렌더링");
  return (
    <div className="bg-blue-50 p-4 rounded-lg">
      <p className="text-xl font-bold">
        {state.name}의 카운트 : {state.count}
      </p>
    </div>
  );
}

function Controls() {
  const dispatch = useCountDispatch(); // ← DispatchContext만 구독 (state 구독 안 함)
  console.log("[After] Controls 렌더링");
  return (
    <div className="bg-green-50 p-4 rounded-lg mt-3 flex gap-3">
      <button
        onClick={() => dispatch({ type: "INCREMENT" })}
        className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
      >
        + 증가
      </button>
      <button
        onClick={() => dispatch({ type: "DECREMENT" })}
        className="px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600"
      >
        - 감소
      </button>
    </div>
  );
}
export default function BoardAfter() {
  return (
    <CountProvider>
      <h3 className="text-lg font-semibold mb-3">
        After: 분리된 Context (해결)
      </h3>
      <Display />
      <Controls />
    </CountProvider>
  );
}

// 실습 확인 포인트:
// "+ 증가" 버튼을 5번 클릭하면 Display 렌더링은 계속 올라가지만,
// Controls 렌더링은 멈춰있는 걸 콘솔에서 확인할 수 있다.
