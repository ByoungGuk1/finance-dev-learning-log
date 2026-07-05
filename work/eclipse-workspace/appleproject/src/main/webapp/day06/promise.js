// Promise 상태: pending → fulfilled / rejected
// fetchUser()
// id를 받아서 조건을 통해 성공인지, 실패인지를 판단 후 Promise객체를 return
const fetchUser = (id) => new Promise((resolve, reject) => {
    //setTimeout(() => {
    if (id > 0) resolve({ id, name: '홍길동', grade: 'VIP' });
    else reject(new Error('잘못된 ID'));
    //}, 1000);
});
// then / catch / finally
fetchUser(1)
    .then(user => { console.log(user.name); return user.grade; })
    .then(grade => { console.log(grade); })
    .catch(err => { console.error(err.message); })
    .finally(() => { console.log('항상 실행'); });

// Promise.all — 동시에 여러 요청
Promise.all([fetchUser(1), fetchUser(2), fetchUser(3)])
    .then(([u1, u2, u3]) => console.log(u1, u2, u3))
    .catch(err => console.error('하나라도 실패:', err));

// Promise.allSettled — 실패해도 모두 기다림
Promise.allSettled([fetchUser(1), fetchUser(-1)])
    .then(results => results.forEach(r => console.log(r)));