import type { UserType } from "../common/util";
import ChildComponent from "./ChildComponent";
import UserCard, { UserCard3 } from "./UserCard";

/*
  Property(속성)
  부모가 자식에게 전달하는 단방향 데이터
*/

const Section02Start = () => {
  const userCard3obj: UserType = {
    id: 1,
    name: "kim",
    email: "kim@gmail.com",
    phone: "010-1234-1234",
  };

  //event는 일어나는 사건, eventHandler : 이벤트 발생시의 동작
  const onClickUserCardHandler = () => {
    alert("클릭");
  };

  const f1 = (a: number, b: number): number => {
    const result: number = a + b;
    alert(`${a} + ${b} = ${result}`);
    return result;
  };

  // input tag에서 사용 예정
  const changeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    console.log("input tag에서 사용자가 입력한 값: " + e.target.value);
  };

  return (
    <>
      <ChildComponent />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <UserCard
        name="kim"
        age={20}
        onClick={onClickUserCardHandler}
        f1={f1}
        changeHandler={changeHandler}
      />
      <UserCard3 {...userCard3obj} />
      <UserCard3 id={1} name="dd" />
    </>
  );
};

export default Section02Start;
