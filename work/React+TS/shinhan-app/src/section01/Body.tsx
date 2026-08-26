import type { ReactNode } from "react";

export const Body = () => {
  const subject: string = "ReactJS";
  const price: number = 1234;
  const numberArray: number[] = [100, 90, 80];
  const valueBoolean: boolean = true;
  const valueNull = null;
  const valueUndifined: undefined = undefined;
  const valueObject: { name: string; age: number } = { name: "kim", age: 20 };

  const renderValueObject = (valueObject: {
    name: string;
    age: number;
  }): ReactNode => {
    return (
      <>
        <p>{valueObject?.name}</p>
        <p>{valueObject?.age}</p>
      </>
    );
  };
  const ValueObjectComponent = (): ReactNode => {
    return renderValueObject(valueObject);
  };

  const isLogin: boolean = true;
  const LoginFunc = (isLogin: boolean): ReactNode => {
    if (isLogin) {
      return <LoginSuccess />;
    } else {
      return <LoginFail />;
    }
  };
  const LoginSuccess = (): ReactNode => {
    return <p>로그인 완료</p>;
  };
  const LoginFail = (): ReactNode => {
    return <p>로그인 실패</p>;
  };
  const LoginComponent = (): ReactNode => {
    return LoginFunc(isLogin);
  };

  return (
    <>
      <section>
        <p>Body Component</p>
        <p>내용 : {subject}</p>
        <p>가격 : {price}</p>
        <p>배열 : {numberArray.join(",")}</p>
        <p>boolean : {valueBoolean}</p>
        <p>null : {valueNull || "rendering 안함"}</p>
        <p>undifined : {valueUndifined || "rendering 안함"}</p>
        <div>
          <p>-Object-</p>
          <ValueObjectComponent />
        </div>
        <LoginComponent />
      </section>
    </>
  );
};
