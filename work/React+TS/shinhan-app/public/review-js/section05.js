import f1, { person, person2, f2 } from "./section03.js";
import { clean, baseData } from "./section04.js";

console.log(person);
console.log(person2);
console.log(f1());
console.log(f2() + "타입은 - " + typeof f2);
console.log(baseData);
console.log(clean("123"));

call1();
function call1() {
  console.log("함수1 : 선언적 함수");
}

const call2 = function () {
  console.log("함수2 : 리터럴 함수");
};
call2();

const call3 = () => {
  console.log("함수3 : 화살표 함수");
};
call3();

// -구조분해할당-

const subject = ["자바스크립트", "리엑트"]; // iterable
const student = { studentName: "홍길동", major: "컴공" };

const [sub1, sub2] = subject; // iterable
const { studentName, major } = student;
console.log(sub1, sub2);
console.log(studentName, major);

const subject2 = [...subject, "스프링"];
const student2 = { ...student, 학번: "1234", address: "마포" };
console.log(subject2);
console.log(student2);

const subject3 = [subject, "스프링"];
const student3 = { student, 학번: "1234", address: "마포" };
console.log(subject3);
console.log(student3);

// -class-
class Animal {
  constructor(name) {
    this.name = name;
    console.log("Animal생성자실행");
  }
  speak() {
    console.log(`${this.name}가 소리를 냅니다!!`);
  }
}

class Dog extends Animal {
  constructor(name, age) {
    super(name);
    this.age = age;
    console.log("Dog생성자실행");
  }
  speak() {
    super.speak(); //부모의 메서드 호출
    console.log(`${this.name}가 멍멍 소리를 냅니다.(${this.age})`);
  }
}
var ani = new Animal("하하");
ani.speak();
var dog1 = new Dog("뽀삐", 3);
dog1.speak();

// -배열-
const names = ["홍길동", "김길동", "박길동", "김기도"];
const users = [
  { name: "김민석", isActive: true },
  { name: "이남경", isActive: false },
  { name: "정수필", isActive: true },
];
const numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
const names2 = names.map((name) => name + "@daum.net");
const names3 = names.filter((name) => name.substring(0, 1) === "김");
const names4 = users.filter((user) => user.isActive);

const result = numbers.reduce((sum, n) => sum + n, 100);
//sum의 초기값=100,sum = sum+n
console.log("reduce:" + result);
console.log("--------------------------------------------------------");
names.forEach((name) => console.log("forEach:", name));
console.log("--------------------------------------------------------");
names2.forEach((name) => console.log("map:", name));
console.log("--------------------------------------------------------");
names3.forEach((name) => console.log("filter1:", name));
console.log("--------------------------------------------------------");
names4.forEach((obj) => console.log("filter2:", obj));

console.log("---");
names
  .filter((name) => name.substring(0, 1) === "김")
  .forEach((name) => console.log(name));

users.filter((user) => user.isActive).forEach((user) => console.log(user));
