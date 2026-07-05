$(() => {
    $("h1").on("click", function(e) {
        console.log(e.pageX, e.pageY);
        let text = $(this).text() + "!";
        $(this).text(text);
    });
    let obj = {
        "mouseenter": () => {
            $(this).addClass("reverse");
        },
        "mouseleave": () => {
            $(this).removeClass("reverse");
        },
    };
    $("h1").on(obj);
    /* $("h1").hover(
      ()=>{
        $(this).addClass("reverse");
      },
      ()=>{
        $(this).removeClass("reverse");
      }
    ); */
    $("#here").on(obj);

    const h1AutoClick = setInterval(() => {
        //이벤트 호출
        $("h1").last().trigger("click");
    }, 1000);
    $("#btn-h1-break").on("click", () => {
        clearInterval(h1AutoClick);
    })

    //default event cancel
    $("a.emp").on("click", () => { alert("click") });

    $("#my-form").on("submit", myFormSubmit);
});

function myFormSubmit(e) {
    e.preventDefault();
    const inputName = $("input[name='my-name']").val();
    console.log(inputName);
}