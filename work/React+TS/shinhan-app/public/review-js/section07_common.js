function 라면장보기() {
  return new Promise((resolve, reject) => {
    console.log("[라면] 1. 재료 사러 가기");
    setTimeout(() => {
      const 돈있음 = true; // ← 실패 테스트 시 false로 변경
      if (돈있음) {
        resolve("[라면] 2. 재료 구매 성공");
      } else {
        reject("[라면] 2. 재료 구매 실패 (돈 없음)");
      }
    }, 1000);
  });
}

function 커피장보기() {
  return new Promise((resolve, reject) => {
    console.log("[커피] 1. 사러 가기");
    setTimeout(() => {
      const 돈있음 = true; // ← 실패 테스트 시 false로 변경
      if (돈있음) {
        resolve("[커피] 2. 구매 성공");
      } else {
        reject("[커피] 2. 구매 실패 (돈 없음)");
      }
    }, 500);
  });
}

export { 라면장보기, 커피장보기 };
