import type { ReactNode } from "react";
import "bootstrap/dist/css/bootstrap.min.css";

type StudentType = { stdId: number; stdName: string };
type functionType = (a: number, b: number) => number;

const TestComponent = () => {
  const myName: string = "이름";
  const s1: StudentType = {
    stdId: 100,
    stdName: "홍길동",
  };
  const f1 = (a: number, b: number): number => a + b + 100;
  const f2: functionType = (a, b) => a + b + 3;

  const h2Style = { color: "gray", backgroundColor: "yellow" };

  return (
    <div className="container">
      <h1 className="my-header">부모 Component</h1>
      <h2 style={h2Style}>CSS 연습(inline)</h2>
      <ul>
        <li>{myName}</li>
        <li>{s1.stdId}</li>
        <li>{s1.stdName}</li>
        <li>f1(1, 2) = {f1(1, 2)}</li>
        <li>f2(1,2) = {f2(1, 2)}</li>
      </ul>
      <ChildComponent title="React" writer="글쓴이">
        내부 컨텐츠
        <h2>태그 속 내용</h2>
      </ChildComponent>
    </div>
  );
};

type ChildPropsType = { title: string; writer: string };
function ChildComponent({
  title,
  writer,
  children,
}: ChildPropsType & { children?: string | ReactNode }) {
  const onClickBtn1 = (e) => {
    return alert(e.target);
  };
  return (
    <>
      <h2>자식 Component</h2>
      <p>{title}</p>
      <p>{writer}</p>
      <>{children ?? "내용없음"}</>
      <button className="btn btn-success" onClick={onClickBtn1}>
        버튼1
      </button>
      <button className="btn btn-danger" onClick={() => {}}>
        버튼2
      </button>
    </>
  );
}

export default TestComponent;
