const myname: string = "kim";
const age: number = 20;
const isLogin: boolean = false;

console.log(myname);
console.log(age);
console.log(isLogin);

const names: string[] = ["kim", "lee", "park"];
names.forEach(console.log);

interface PersonType {
  name: string;
  age: number;
}

const person: PersonType = { name: "kim", age: 20 };
console.log(person);

const person2: PersonType = { name: "kim", age: 20 };
console.log(person2);

export {};
