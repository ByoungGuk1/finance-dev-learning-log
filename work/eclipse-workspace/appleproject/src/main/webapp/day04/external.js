console.log("HTML 외부에서 만든 JS");

const call = () => {
    alert("HTML 외부에서 만든 call 함수");
}

const call2 = () => {
    var result = confirm("확인");
    alert("결과 : " + result);
}

const call3 = () => {
    var result = prompt("나이 입력");
    alert("결과\n" + result);
}