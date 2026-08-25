console.log(`${a}`);
//var는 호이스팅
// console.log(`${b}`);
var a = 10;
let b = 20;
const c = 30;

console.log(a + b + c);
var a = "test";
console.log(a);
//var는 재선언 가능
//const는 재할당 불가

{
  var v1 = "TS";
  let l1 = "React"; //block 내부에서만 유효
}
console.log(v1);
// console.log(l1);
