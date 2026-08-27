import { useEffect, useState } from "react";
import Button from "../Button";

// 함수형 컴포넌트는 useEffect로 라이프 사이클을 관리
// 사이드 이펙트 처리
const LifeCycleFunction = () => {
  const [count, setCount] = useState<number>(0);
  const [email, setEmail] = useState<string>("");
  useEffect(() => {
    console.log("의존배열이 비어있으면 load시 1회");
  }, []);

  useEffect(() => {
    console.log("의존배열이 생략되면  load시 마다");
  });
  useEffect(() => {
    console.log("의존배열이 count ");
  }, [count]);
  useEffect(() => {
    console.log("의존배열이 email ");
  }, [email]);
  useEffect(() => {
    console.log("의존배열이 count, email ");
  }, [count, email]);

  // 의존 배열이 생략되면 Rerendering 마다 실행
  // return 함수를 하면 해당 함수를 종료시 실행 -> cleanup 함수
  useEffect(() => {
    const interval = setInterval(() => {
      console.log(new Date().toLocaleTimeString());
    }, 1000);
    return () => {
      clearInterval(interval);
    };
  });

  return (
    <>
      <h1>LifeCycle (Function Component)</h1>
      <p>
        count: {count} email:{email}{" "}
      </p>
      <button
        className="px-4 py-2 bg-blue-500 rounded text-white shadow hover:bg-blue-700"
        onClick={() => setCount((pre) => pre + 1)}
      >
        증가
      </button>
      <Button color="green" clickHandler={() => setCount((pre) => pre - 1)}>
        감소
      </Button>
      <input
        className="border px-3 py-2 rounded"
        onChange={(e) => setEmail(e.target.value)}
      />
    </>
  );
};

export default LifeCycleFunction;
