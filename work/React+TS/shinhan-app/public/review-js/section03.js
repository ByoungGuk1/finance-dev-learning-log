const person = { name: "MAX", age: 20, score: 99 };
const person2 = { name: "tester", age: 21, socre: 88 };

// 익명함수, literal 형태, 매개변수X return 존재
const f1 = function () {
  return "f1 함수 - 이름은 " + person.name;
};

const f2 = () => {
  return "f2 함수 화살표 함수";
};

// 다른 JS에서 해당 모듈을 import하기 { person, f1 }
export { person, person2, f1 as default, f2 };
