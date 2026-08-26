import { Component } from "react";

// class component는 반드시 render() 함수가 있어야한다.
export class MyClassComp1 extends Component {
  render() {
    return <div>MyClassComp1</div>;
  }
}

// class 내부에 함수 정의시 function을 쓰지 않고 바로 `함수명(){}`
export class MyClassComp2 extends Component {
  f1() {
    return "test";
  }
  render() {
    return (
      <>
        <span>{this.f1()}</span>
        <div>MyClassComp2</div>
      </>
    );
  }
}
