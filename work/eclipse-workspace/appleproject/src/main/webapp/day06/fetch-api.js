// GET 기본
const url = `https://api.frankfurter.dev/v1/latest?base=USD&symbols=KRW`;
function hideLoading() {
    document.querySelector("#loading").style.display = `none`;
}

const getData = async (url) => {
    const res = await fetch(url);
    if (!res.ok) throw new Error(`HTTP ${res.status}`);  // 에러 체크 필수
    return res.json();
};

getData(url).then(console.log).then(() => { hideLoading(); });

// POST
const postData = async (url, body) => {
    const res = await fetch(url, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(body),
    });
    return res.json();
};
// PUT / DELETE
//await fetch(`/api/items/${id}`, { method: 'PUT',    body: JSON.stringify(data) });
//await fetch(`/api/items/${id}`, { method: 'DELETE' });
// HTTP 상태 코드
// 200 OK  /  201 Created  /  400 Bad Request
// 401 Unauthorized  /  404 Not Found  /  500 Server Error
//1. JSONPlaceholder — 테스트용 무료 API (키 없음)
'https://jsonplaceholder.typicode.com/posts'        // 게시글 100개
'https://jsonplaceholder.typicode.com/posts/1'      // 게시글 1개
'https://jsonplaceholder.typicode.com/users'        // 사용자 10명
'https://jsonplaceholder.typicode.com/todos'        // 할일 200개
// 2. 날씨 — Open-Meteo (키 없음)
'https://api.open-meteo.com/v1/forecast?latitude=37.5665&longitude=126.9780&current=temperature_2m&timezone=Asia/Seoul'
// 3. 환율 — Frankfurter (키 없음)
'https://api.frankfurter.dev/v1/latest?base=USD&symbols=KRW'
// 4. 도시 검색 — Geocoding (키 없음)
'https://geocoding-api.open-meteo.com/v1/search?name=Seoul&count=1'
// 5. 랜덤 유저 — RandomUser (키 없음)
'https://randomuser.me/api/'                        // 랜덤 사용자 1명
'https://randomuser.me/api/?results=5'              // 랜덤 사용자 5명