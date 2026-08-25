import { 라면장보기, 커피장보기 } from "./section07_common.js";

async function 장보기() {
  console.log("비동기 장보기 시작");
  try {
    const result1 = await 라면장보기();
    console.log(result1);
    const result2 = await 커피장보기();
    console.log(result2);
  } catch (error) {
    console.log("[실패]", error);
  }
}

장보기();
