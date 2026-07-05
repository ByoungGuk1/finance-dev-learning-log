/* window.onload = () => {
  alert("1");
};
window.onload = () => {
  alert("2");
};
//$() == window.onload
// $(대상).함수();
$(document).ready(()=>{
  alert("5");
});
//(document).ready의 경우 생략 가능
$(()=>{alert("3");});
$(()=>{alert("4");});
 */
//---------//
$(() => {
    $("#btn1").on("click", func1);
    $("#btn2").on("click", func2);
    $("#btn3").on("click", func3);
})

function func1() {
    $("h1").css("color", "red");
    $("h1").css({ "border": "1px solid black", "background-color": "#e2e2e2" });
    $("h1.orange").css("color", "orange");
}

function func2() {
    $("input[type='text']").css("background-color", "yellow");
    $("input:text").css("background-color", "gray");

    $("tr:even").css("background-color", "gray");
}

function func3() {
    let arr = ["라면", "짬뽕"];
    $.each(arr, function(index, item) {
        console.log(index, item);
    });
}
