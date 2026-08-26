/*
  JSX (JavaScript + XML)
  TSX (TypeScript + XML)

  root = 1개
  tag 열고 닫기
*/

// component : 가장 작은 단위의 요소, 재사용 가능

export function MyFuntionComp1() {
  return (
    <>
      <h1>function Comtonent1</h1>
    </>
  );
}

export function MyFuntionComp2() {
  return (
    <>
      <ul>
        <li>
          <span>JavaScript</span>
        </li>
        <li>
          <span>TypeScript</span>
        </li>
      </ul>
    </>
  );
}

export const MyFuntionComp3 = () => (
  <>
    <h3>
      <span>테스트 입니다.</span>
    </h3>
  </>
);
