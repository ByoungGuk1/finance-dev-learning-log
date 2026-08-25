// TODO [★★☆] 계좌조회callback 함수를 완성하세요 (5분)
// 조건: 매개변수 기본값은 잔액있음 = true
// 힌트: new Promise((resolve, reject) => { setTimeout(() => { ... }, 800); })
const 계좌조회callback = (잔액있음 = true) =>
  new Promise((resolve, reject) => {
    setTimeout(() => {
      // TODO Step1 [★☆☆] 콘솔에 "1. 계좌 조회 중..." 출력 (2분)
      console.log("1. 계좌 조회 중...");
      // TODO Step2 [★☆☆] 콘솔에 "2. 잔액 확인 중..." 출력 (2분)
      console.log("2. 잔액 확인 중...");
      // TODO Step3 [★★☆] 잔액있음이 false이면 reject("계좌조회실패") 후 return (5분)
      if (!잔액있음) {
        reject("계좌조회실패");
        return;
      } else {
        resolve("계좌조회완료");
      }
      // 주의: return을 빼먹으면 reject 다음 줄의 resolve까지 같이 실행되어버립니다.
      // 힌트: if (!잔액있음) { reject("..."); return; }
      // TODO Step4 [★☆☆] 정상이면 resolve("계좌조회완료") (2분)
    }, 800);
  });

// TODO [★★☆] 송금하기callback 함수를 완성하세요 (5분)
// 조건: 매개변수 기본값은 한도초과 = false (원본과 반대로 "true일 때 실패"인 조건입니다. 주의!)
const 송금하기callback = (한도초과 = false) =>
  new Promise((resolve, reject) => {
    setTimeout(() => {
      // TODO Step1 [★☆☆] 콘솔에 "A. 수취인 확인 중..." 출력
      console.log("A. 수취인 확인 중...");
      // TODO Step2 [★☆☆] 콘솔에 "B. 송금액 검증 중..." 출력
      console.log("B. 송금액 검증 중...");
      // TODO Step3 [★★☆] 한도초과가 true이면 reject("송금한도초과") 후 return
      if (한도초과) {
        reject("송금한도초과");
        return;
      } else {
        resolve("송금완료");
      }
      // 힌트: 원본은 "!돈있음"이면 실패였지만, 여기는 "한도초과"가 true면 실패입니다.
      // TODO Step4 [★☆☆] 정상이면 resolve("송금완료")
    }, 500);
  });

// TODO [★★☆] 은행업무처리 함수를 완성하세요 (10분)
// 조건: 계좌조회 먼저 await → 성공하면 결과 출력 후 "3. 이체 화면으로 이동" 출력
// 조건: 이어서 송금하기 await → 성공하면 결과 출력 후 "C. 처리 완료 알림 발송" 출력
// 조건: 둘 중 하나라도 실패하면 catch에서 에러 메시지 + "업무 중단" 출력
// 힌트: 원본의 약속배려함수 구조(try 안에서 await 두 번, catch에서 err 출력)를 그대로 참고
const 은행업무처리 = async () => {
  try {
    // 여기에 작성 (계좌조회callback 호출 → 결과 출력)
    const 계좌조회결과 = await 계좌조회callback();
    console.log(계좌조회결과);
    console.log("3. 이체 화면으로 이동");
    // 여기에 작성 (송금하기callback 호출 → 결과 출력)
    const 송금하기결과 = await 송금하기callback();
    console.log(송금하기결과);
    console.log("C. 처리 완료 알림 발송");
  } catch (err) {
    // 여기에 작성 (에러 출력 + "업무 중단" 출력)
    console.log(err + "업무중단");
  }
};

은행업무처리();

// 완성 후 아래 두 시나리오를 직접 실행해서 결과를 비교해보세요.
// 1) 계좌조회callback(true), 송금하기callback(false) → 정상 흐름 확인
// 2) 계좌조회callback(false) 로 바꿔서 실행 → catch로 바로 빠지는지 확인
// 완성 후 Claude에게 "reject 다음 줄에 return을 빼먹으면 어떤 문제가 생기나요?"라고 질문하고 답변과 실제 실행 결과가 일치하는지

// (선택, ★★★ 심화) 계좌조회와 송금하기가 서로 의존관계가 없다고 가정하고,
// await를 순차 실행이 아닌 Promise.all()로 동시 실행하도록 바꿔서 실행 시간 차이를 비교해보세요.

async function 동시실행() {
  const start = Date.now();
  await Promise.all([계좌조회callback(), 송금하기callback()]);
  console.log(`동시 실행 소요시간: ${Date.now() - start}ms`);
}
동시실행();
