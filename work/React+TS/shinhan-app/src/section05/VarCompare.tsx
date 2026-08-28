import { useRef, useState } from "react";
import type { JSX } from "react/jsx-runtime";

const VarCompare = (): JSX.Element => {
  //JSX.Element생략가능
  // 1)State (리렌더링 O)
  const [countState, setCountState] = useState<number>(0); // 일반 변수처럼 입력
  const stateHandler = () => {
    setCountState((prev) => prev + 1);
    console.log(countState);
  };

  //2)Ref (리렌더링 X)
  const countRef = useRef<number>(0); // {countRef : 0} 오브젝트 형태로 저장
  const refHandler = () => {
    countRef.current += 1;
    console.log("Ref:", countRef.current);
  };
  //3) 일반 변수 (렌더링마다 초기화)..오류무시하고 수행
  let countLet: number = 0;
  const letHandler = () => {
    /* eslint-disable-next-line */ // 다음줄 확인 넘어가기
    countLet = countLet + 1;
    console.log("Let:", countLet);
  };
  // 강제 리렌더링용 state
  const [render, setRender] = useState<boolean>(false);
  const reRenderHandler = () => setRender((prev) => !prev);
  return (
    <div className="max-w-md mx-auto mt-10 p-6 bg-white rounded-xl shadow space-y-6">
      <h2 className="text-xl font-bold text-center text-gray-800">
        useState / useRef / let 비교
      </h2>

      {/* 값 표시 영역 */}
      <div className="space-y-2 text-lg">
        <p>
          State 값:
          <span className="font-semibold text-blue-600">{countState}</span>
        </p>
        <p>
          Ref 값:
          <span className="font-semibold text-green-600">
            {/* eslint-disable-next-line */}
            {countRef.current}
          </span>
        </p>
        <p>
          Let 값:
          <span className="font-semibold text-red-600">{countLet}</span>
        </p>
      </div>
      {/* 증가 버튼 */}
      <div className="flex gap-3">
        <button
          onClick={stateHandler}
          className="flex-1 px-3 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
        >
          State +1 <p>(상태관리)</p>
        </button>
        <button
          onClick={refHandler}
          className="flex-1 px-3 py-2 bg-green-500 text-white rounded hover:bg-green-600"
        >
          Ref +1 <p>(Ref는 렌더링안함)</p>
        </button>
        <button
          // eslint-disable-next-line
          onClick={letHandler}
          className="flex-1 px-3 py-2 bg-red-500 text-white rounded hover:bg-red-600"
        >
          Let +1
        </button>
      </div>
      {/* 강제 리렌더링 */}
      <p>render: {render ? "참" : "거짓"}</p>
      <button
        onClick={reRenderHandler}
        className="w-full px-4 py-2 bg-gray-800 text-white rounded hover:bg-gray-900"
      >
        강제 리렌더링
      </button>
    </div>
  );
};

export default VarCompare;
