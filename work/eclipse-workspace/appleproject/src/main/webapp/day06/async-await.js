const fetchUser = async (id) => { return { id, name: "홍길동", grade: "A" }; };
const fetchOrders = async (id) => { return { orderId: id + 100, item: "노트북", color: "black" }; };

// 개발자도구에서 오류 나오는데 이유 찾기
function hideLoading() {
    document.querySelector("#loading").style.display = `none`;
}

// async 함수 — 항상 Promise 반환
const loadUserData = async (userId) => {
    try {
        const user = await fetchUser(userId);   // 대기
        const orders = await fetchOrders(user.id); // 다음 요청
        return { user, orders };
    } catch (err) {
        console.error('실패:', err.message);
        throw err;
    } finally {
        hideLoading();   // 항상 로딩 숨기기
    }
}

loadUserData(10)
    .then(({ user, orders }) => { console.log(user, orders); })
    .catch(console.log)
    .finally(() => { console.log("async 함수는 promise객체를 리턴"); });

// 병렬 — await Promise.all
/*const [user, products] = await Promise.all([
    fetchUser(1),
    fetchProducts(),
]);*/
