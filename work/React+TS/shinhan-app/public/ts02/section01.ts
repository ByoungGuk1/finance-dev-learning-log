interface PersonType {
  name: string;
  age: number;
}

const person1: PersonType = {
  name: "홍길동",
  age: 20,
};

console.log(person1);

interface StudentType extends PersonType {
  major: string;
}

const s1: StudentType = {
  name: "as",
  age: 18,
  major: "전공",
};

console.log(s1);
