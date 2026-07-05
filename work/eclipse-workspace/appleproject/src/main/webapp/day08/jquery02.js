window.onload = () => {
    const btn1 = document.querySelector("#btn1");
    const btn2 = document.querySelector("#btn2");
    const btn3 = document.querySelector("#btn3");

    btn1.addEventListener("click", btn1Action);
    btn2.addEventListener("click", btn2Action);
    btn3.addEventListener("click", btn3Action);
}

function btn1Action() {
    //JS 를 이용한 통신
    let xhr = new XMLHttpRequest();
    xhr.open("get", "jquery01.html", true); // 3번 매개변수 = 동기 비동기 여부 => false : 동기 :: default : true
    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const responseData = xhr.responseText;
            console.log(responseData);
            document.querySelector("#display").innerHTML = responseData;
        }
    };
    xhr.send();
    console.log("btn1Action main stream end");
}

function btn2Action() {
    //    $.ajax(url, options);
    $.ajax({
        url: "jquery01.html",
        type: "get",
        success: (data, status, xhr) => {
            console.log(status);
            console.log(xhr);
            $("#display").html(data);
        }
    });
}

function btn3Action() {
    $("#display").load("jquery01.html", {
        success: (d, s, xhr) => {
            console.log(xhr);
        }
    });
}