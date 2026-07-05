//콜백 — 비동기의 시작
setTimeout(() => console.log('1초 후'), 1000);
console.log('이게 먼저 출력됨!');

// 콜백 지옥 (Callback Hell)
setTimeout(() => {
    console.log('1단계');
    setTimeout(() => {
        console.log('2단계');
        setTimeout(() => {
            console.log('3단계');  // 피라미드 모양 → 가독성 최악
        }, 1000);
    }, 1000);
}, 1000);