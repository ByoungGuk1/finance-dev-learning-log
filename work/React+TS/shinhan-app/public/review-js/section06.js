import { 라면장보기, 커피장보기 } from "./section07_common.js";

console.log("==== 동기 방식 ====");
console.log("1. 물 올리기");
sleepBlocking(3000); // 물이 끓을 때까지 3초간 아무것도 못 하고 '멈춰서 기다림'
console.log("2. 계란 풀기");
console.log("3. 그릇 꺼내기");
console.log("4. 물 다 끓음 → 라면 넣기");
// 총 소요시간 = 3초(그냥 대기) + 계란풀기 + 그릇꺼내기 = 낭비되는 시간이 생김

function sleepBlocking(ms) {
  const start = Date.now();
  while (Date.now() - start < ms) {
    // 아무것도 안 하고 CPU만 붙잡고 있음
  }
}

console.log("==== 비동기 방식 ====");
console.log("1. 물 올리기 (3초 뒤 끓음)");
setTimeout(() => {
  console.log("4. 물 다 끓음 → 라면, 스프 넣기");
}, 3000);

// 물이 끓는 3초 동안 "물 끓는 것과 상관없는" 다른 준비를 먼저 해버린다
console.log("2. (물 끓는 동안) 계란 풀어두기");
console.log("3. (물 끓는 동안) 그릇 꺼내기");

//-예제-
// ================동기 버전 (Promise/setTimeout 없이 함수 호출로 그대로 실행================
// [전체] 시작
// [라면] 1. 재료 사러 가기
// [라면] 2. 재료 구매 성공
// [라면] 3. 완성
// [커피] 1. 사러 가기
// [커피] 2. 구매 성공
// [커피] 3. 마심
// [전체] 끝          ← 모든 게 다 끝난 뒤 맨 마지막

// ================비동기 버전================
// [전체] 시작
// [라면] 1. 재료 사러 가기
// [전체] 끝          ← 라면 결과도 나오기 전에 먼저 출력!
// [라면] 2. 재료 구매 성공
// [라면] 3. 완성
// [커피] 1. 사러 가기
// [커피] 2. 구매 성공
// [커피] 3. 마심

//----------------------동기

// console.log("[전체] 시작");

// function 라면장보기() {
//   console.log("[라면] 1. 재료 사러 가기");
//   const 돈있음 = true; // ← 실패 테스트 시 false로 변경
//   if (돈있음) {
//     return "[라면] 2. 재료 구매 성공";
//   } else {
//     throw "[라면] 2. 재료 구매 실패 (돈 없음)";
//   }
// }

// function 커피장보기() {
//   console.log("[커피] 1. 사러 가기");
//   const 돈있음 = true; // ← 실패 테스트 시 false로 변경
//   if (돈있음) {
//     return "[커피] 2. 구매 성공";
//   } else {
//     throw "[커피] 2. 구매 실패 (돈 없음)";
//   }
// }

// try {
//   const r1 = 라면장보기();
//   console.log(r1);
//   console.log("[라면] 3. 완성");

//   const r2 = 커피장보기();
//   console.log(r2);
//   console.log("[커피] 3. 마심");
// } catch (error) {
//   console.log("[실패]", error);
// }

// console.log("[전체] 끝");

//------------------------비동기
console.log("[전체]비동기 시작");

라면장보기()
  .then((result) => {
    console.log(result);
    console.log("[라면] 3. 완성");
    return 커피장보기();
  })
  .then((result) => {
    console.log(result);
    console.log("[커피] 3. 마심");
  })
  .catch((error) => {
    console.log("[실패]", error);
  });

console.log("[전체] 비동기끝!!!!!");
