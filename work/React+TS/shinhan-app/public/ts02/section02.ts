// ts func

// 1 선언 함수

function add(a: number, b: number): number {
  return a + b;
}

console.log(add(1, 2));

// 2 화살표 함수 + param default

const login = (id: string = "admin", pwd: string = "1234"): boolean =>
  id === "admin" && pwd === "1234";

console.log(login("admin", "1234"));
console.log(login() && "성공");

// optional
const printUser = (name: string, age?: number): void => {
  if (age) {
    console.log(`이름은 ${name}이고, 나이는 ${age}입니다.`);
  } else {
    console.log(`이름은 ${name}입니다.`);
  }
};

printUser("s");
printUser("s", 2);

const sumArray = (arr: number[] = [10, 20, 30]): number => {
  let sum = 0;
  for (const n of arr) {
    sum += n;
  }
  return sum;
};

console.log(sumArray());

// 함수 타입 정의
let addFunc: (a: number, b: number) => number;
addFunc = (a, b) => a + b;
console.log(addFunc(1, 2));
addFunc = (a, b) => a + b + 1;
console.log(addFunc(1, 2));

// callback
function work(callback: (n: number) => number): void {
  console.log(callback(10));
}

work((n) => n + 2);

// 가변 인자
const sum = (...numbers: number[]): number => {
  let sum = 0;
  for (const number of numbers) {
    sum += number;
  }
  return sum;
};

console.log(sum(1, 2, 3, 4, 5));

// union type
type UnionType = string | number;
const u1: UnionType = "문자열";
const u2: UnionType = 123;
console.log(u1, u2);

// any
// let anyType: any = "문자열";
// console.log(anyType);
// anyType = 123;
// console.log(anyType);
// anyType = 2.3;
// console.log(anyType);

// type에서 optional

// readonly

// generic => section03.ts
