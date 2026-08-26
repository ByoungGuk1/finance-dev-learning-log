import type { UserType } from "../common/util";

export type Props = {
  name: string;
  age: number;
};

// <UserCard name="kim" age={20} />
// Property 변수 이름은 자유

export default function UserCard({
  name,
  age,
  onClick,
  f1,
  changeHandler,
}: Props & {
  onClick?: () => void;
  f1?: (a: number, b: number) => number;
  changeHandler?: (e: React.ChangeEvent<HTMLInputElement>) => void;
}) {
  return (
    <>
      <ul>
        <li>
          <p>이름 : {name}</p>
        </li>
        <li>
          <p>나이 :{age}</p>
        </li>
      </ul>
      <button
        onClick={() => {
          return f1 && f1(10, 20);
        }}
      >
        함수 호출
      </button>
      <button onClick={onClick}>버튼</button>
      <input onChange={changeHandler}></input>
    </>
  );
}

export function UserCard3({ id, name, email, phone }: UserType) {
  return (
    <>
      <ul>
        <h3>UserCard3</h3>
        <li>id: {id}</li>
        <li>name: {name}</li>
        <li>email: {email || "공백"}</li>
        <li>phone: {phone || "공백"}</li>
      </ul>
    </>
  );
}
