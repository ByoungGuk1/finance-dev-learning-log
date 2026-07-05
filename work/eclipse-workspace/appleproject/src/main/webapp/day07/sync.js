window.onload = () => {
    const btn1 = document.querySelector("#btn1");
    const btn2 = document.querySelector("#btn2");
    const btn3 = document.querySelector("#btn3");
    const btn4 = document.querySelector("#btn4");
    const btn5 = document.querySelector("#btn5");
    const btn6 = document.querySelector("#btn6");

    btn1.addEventListener("click", func1);
    btn2.addEventListener("click", func2);
    btn3.addEventListener("click", func3);
    btn4.addEventListener("click", func4);
    btn5.addEventListener("click", func5);
    btn6.addEventListener("click", func6);
}

//고차함수 : callback
// function을 받아서 호출
function fetchUser(userId, callback) {
    setTimeout(() => {
        callback(null, { userId, name: "홍길동" });
    }, 800);
}
function fetchOrders(userId, callback) {
    setTimeout(() => {
        callback(null, { orderId: 100, userId, item: "computer", price: 200 });
    }, 500);
}
function fetchPayment(orderId, callback) {
    setTimeout(() => {
        callback(null, { orderId, paid: true });
    }, 100);
}
function func1() {
    //userId와 함수 전달
    fetchUser(3, (err, user) => {
        if (err) {
            console.log(err);
            return;
        }
        console.log(user);
        //userId와 함수 전달
        fetchOrders(user.userId, (err, order) => {
            if (err) {
                console.log(err);
                return;
            }
            console.log(order);
            //orderId와 함수 전달
            fetchPayment(order.orderId, (err, payment) => {
                if (err) {
                    console.log(err);
                    return;
                }
                console.log(payment);
            });
        });
    });
}

//Promise
function fetchUser2(userId) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            resolve({ userId, name: "홍길동" });
        }, 800);
        //reject(null);
    });
}
function fetchOrders2(userId) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            resolve({ orderId: 100, userId, item: "computer", price: 200 });
        }, 500);
        //reject(null);
    });
}
function fetchPayment2(orderId) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            resolve({ orderId, paid: true });
        }, 100);
        //reject(null);
    });
}
function func2() {
    fetchUser2(3)
        .then((user) => fetchOrders2(user.userId))
        .then((order) => fetchPayment2(order.orderId))
        .then((pay) => { console.log(pay); })
        .catch((err) => { console.log(err) });
}

//async&await
async function processOrder(userId) {
    try {
        const user = await fetchUser2(userId);
        const order = await fetchOrders2(user.userId);
        const pay = await fetchPayment2(order.orderId);
        console.log(pay);
        return { user, order, pay };
    } catch (err) {
        console.log(err);
    }
}
function func3() {
    const result = processOrder(3);
    console.log(result);
}
async function func4() {
    const cityData = await getCityData("Souel");
    //const cityData = await getCityData("NewYork");
    const { name, latitude, longitude } = cityData;
    const cityWeather = await getCityWeatherData(latitude, longitude);
    const { current } = cityWeather;
    const { temperature_2m } = current;
    const temp_unit = cityWeather.current_units.temperature_2m;
    console.log(`${name}의 온도 : ${temperature_2m}${temp_unit}`);
}

const getData = async (url) => {
    const res = await fetch(url);
    if (!res.ok) throw new Error(`HTTP ${res.status}`);
    return res.json();
};
async function getCityData(city) {
    const url = `https://geocoding-api.open-meteo.com/v1/search?name=${encodeURIComponent(city)}&count=1&language=ko`;
    return await getData(url)
        .then(({ results }) => results[0])
        .catch(err => err);
}
async function getCityWeatherData(latitude, longitude) {
    const url = `https://api.open-meteo.com/v1/forecast?latitude=${latitude}&longitude=${longitude}&current=temperature_2m,weathercode,windspeed_10m,relativehumidity_2m&timezone=Asia/Seoul`;
    return await getData(url)
        .catch(err => err);
}

function func5() {
    func4();
}

function func6() {
    getBithumbData().then(console.log);
}

async function getBithumbData() {
    const options = { method: 'GET', headers: { accept: 'application/json' } };

    return await fetch('https://api.bithumb.com/v1/ticker?markets=KRW-BTC', options)
        .then(response => response.json())
        .catch(err => {
            console.error(err);
            return err;
        });
}