// generic
function wrap<T>(value: T): T {
  if (typeof value === "string") {
    console.log("문자열이 들어왔습니다.");
    //return value + ""
    //에러 발생, 컴파일 시점에 T타입이 string인지 확신할 수 없기 때문
  } else if (typeof value === "number") {
    console.log("숫자가 들어왔습니다.");
  }
  return value;
}

const result: number = wrap<number>(1);
console.log(result);

// const result2: string = wrap<string>("aasdf");
// console.log(result2);

// const result3: string = wrap<string>("");
// console.log(result3);

//제네릭없이 Union 타입으로 작성한 함수(깔끔)
function wrap2(value: string | number): string | number {
  if (typeof value === "string") {
    return value.length; // number
  } else {
    return `숫자:${value}`; // string
  }
}

const a = wrap2("hello"); // number
const b = wrap2(10); // string
console.log(a, b);

//제네릭 제한 걸기 (권장안함)
function wrap3<T extends string | number>(value: T): T {
  if (typeof value === "string") {
    console.log("#문자열");
    return (value + "!!!") as T;
  } else if (typeof value === "number") {
    console.log("#숫자");
    return (value + 100) as T;
  }
  return value;
}

const a3 = wrap3("hello"); // number
const b3 = wrap3(10); // string
console.log(a3, b3);

// 제네릭 활용
type ApiResponse<T> = {
  success: boolean;
  data: T;
};

type Customer = {
  name: string;
  age: number;
};

interface Board {
  bno: number;
  title: string;
  writter?: string;
}

const result1: ApiResponse<Customer> = {
  success: true,
  data: {
    name: "kim",
    age: 20,
  },
};

console.log(result1);

const result2: ApiResponse<Board> = {
  success: true,
  data: {
    bno: 12,
    title: "제목",
  },
};

console.log(result2);

// promise 객체 반환
function fetchData(): Promise<string> {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve("데이터 도착");
    }, 1000);
  });
}

fetchData().then((data) => {
  console.log(data);
});
