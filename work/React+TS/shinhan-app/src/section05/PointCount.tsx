import { useEffect, useState } from "react";

const PointCount = () => {
  const [point, setPoint] = useState<number>(0);
  const [now, setNow] = useState(new Date().toLocaleTimeString());

  useEffect(
    () => {
      const timerId = setInterval(() => {
        setNow(new Date().toLocaleTimeString());
        console.log(now);
      }, 1000);
      // return ()=> {} // cleanup 함수 => 컴포넌트 제거 시 실행, 메모리 누수 방지를 위해
      return () => {
        clearInterval(timerId);
      };
    },
    [now], // 의존 배열 생략 시 랜더링 마다 실행, 빈 배열시 마운트 시에만, 값을 넣은 경우 해당 값의 변경이 일어날 때
  );

  const addPoint = () => {
    setPoint((prev) => prev + 10);
  };

  const usePoint = () => {
    setPoint((prev) => Math.max(0, prev - 10));
  };

  const resetPoint = () => {
    setPoint(0);
  };

  return (
    <div className="max-w-sm mx-auto mt-10 p-6 bg-white rounded-xl shadow space-y-4 text-center">
      <h2 className="text-xl font-bold text-gray-800">적립포인트: {point}P</h2>
      <p className="text-sm text-gray-500">현재 시각: {now}</p>
      <div className="flex justify-center gap-3">
        <button
          onClick={addPoint}
          className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
        >
          +10 적립
        </button>
        <button
          onClick={usePoint}
          className="px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600"
        >
          -10 사용
        </button>
        <button
          onClick={resetPoint}
          className="px-4 py-2 bg-gray-700 text-white rounded hover:bg-gray-800"
        >
          초기화
        </button>
      </div>
    </div>
  );
};

export default PointCount;
