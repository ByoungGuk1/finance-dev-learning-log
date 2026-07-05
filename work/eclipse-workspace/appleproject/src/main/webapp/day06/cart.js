// ── 상품 데이터 ──
const products = [
    { id: 1, name: '프리미엄 운동화', category: '신발', price: 89000, color: '#dbeafe' },
    { id: 2, name: '클래식 스니커즈', category: '신발', price: 62000, color: '#e0e7ff' },
    { id: 3, name: '러닝화 프로', category: '신발', price: 75000, color: '#fce7f3' },
    { id: 4, name: '린넨 셔츠', category: '의류', price: 39000, color: '#d1fae5' },
    { id: 5, name: '후드 집업', category: '의류', price: 79000, color: '#fee2e2' },
    { id: 6, name: '크로스백', category: '잡화', price: 45000, color: '#ede9fe' },
];

let cart = [];
const CART_KEY = "CART";

window.onload = () => {
    //    console.log("window load 완료");
    //    base().setLocalStorage();
    //    base().getLocalStorage();
    loadCart();
    renderCart();
    renderProducts();
    document.querySelector("#productGrid").addEventListener("click", (e) => {
        if (!e.target.classList.contains("btn-add")) return;
        const id = Number(e.target.dataset.id);
        addCart(id);
    });

    //위임 형태
    document.querySelector("#cartList").addEventListener("click", (e) => {
        if (e.target.classList.contains("qty-btn")) {
            changeQty(e.target.dataset.id, e.target.dataset.delta);
        }
        if (e.target.classList.contains("btn-del")) {
            removeCartProduct(e.target.dataset.id);
        }
    });
    document.querySelector("#clearBtn").addEventListener("click", () => {
        cart = [];
        renderCart();
    });
}

function saveCart(cart) {
    localStorage.setItem(CART_KEY, JSON.stringify(cart));
}
function loadCart() {
    const jsonCart = localStorage.getItem(CART_KEY);
    cart = JSON.parse(jsonCart ?? '[]');
    return cart;
}

function addCart(id) {
    const existing = cart.find((p) => p.id === id);
    if (existing) {
        existing.qty++;
    } else {
        const product = products.find(p => p.id === id);
        let newProduct = { ...product, qty: 1 };
        cart = [...cart, newProduct];
    }
    renderCart();
    saveCart(cart);
}

function changeQty(id, delta) {
    let selectedProduct = cart.find((p) => { return p.id === Number(id) });
    if (!selectedProduct) return;
    selectedProduct.qty += Number(delta);

    if (selectedProduct.qty <= 0) {
        const newCart = cart.filter((p) => p.id !== selectedProduct.id);
        cart = newCart;
    }
    renderCart();
    saveCart(cart);
}

function removeCartProduct(id) {
    let selectedProduct = cart.find((p) => { return p.id === Number(id) });
    if (!selectedProduct) return;
    const newCart = cart.filter((p) => p.id !== selectedProduct.id);
    cart = newCart;
    renderCart();
    saveCart(cart);
}

function renderProducts() {
    const grid = document.querySelector("#productGrid");
    //    products.map(renderProduct).forEach(grid.appendChild);


    const prodlist = products.map(
        (p) => `
                <div class="p-card">
                  <div class="p-img" style="background:${p.color}"></div>
                  <p class="p-category">${p.category}</p>
                  <p class="p-name">${p.name}</p>
                  <p class="p-price">${p.price.toLocaleString()}원</p>
                  <button class="btn-add" data-id="${p.id}">🛒 담기</button>
                </div>
              `
    );
    grid.innerHTML = prodlist.join("");
}


function renderCart() {
    //    const renderTarget = document.querySelector("#cartList");
    //    const cartHTML = cart.map((p) => { return `<p class="p-name">${p.name}</p>` });
    //    renderTarget.innerHTML = cartHTML.join("");

    //load시에, 담기, 수량변경, 비우기  호출 
    // 장바구니 렌더링 + 총 금액 (reduce)
    const cartList = document.querySelector("#cartList");
    const cartFooter = document.querySelector("#cartFooter");
    if (cart.length === 0) {
        cartList.innerHTML = `<div class="cart-empty">장바구니가 비어있어요 🛒</div>`;
        cartFooter.style.display = "none";
        return;
    }
    cartList.innerHTML = cart.map((item) => `<div class="cart-item" data-id="${item.id}">
      <span class="cart-item-name">${item.name}</span>
      <div class="qty-wrap">
        <button class="qty-btn" data-action="minus" data-id="${item.id}"  data-delta="-1">−</button>
        <span class="qty-num">${item.qty}</span>
        <button class="qty-btn" data-action="plus" data-id="${item.id}"  data-delta="1">+</button>
      </div>
      <span class="cart-item-price">${(item.price * item.qty).toLocaleString()}원</span>
      <button class="btn-del" data-id="${item.id}">✕</button>
    </div> `).join("");

    // 총 금액 계산
    const total = cart.reduce((sum, item) => sum + item.price * item.qty, 0);
    document.querySelector("#totalPrice").textContent = total.toLocaleString() + "원";
    cartFooter.style.display = "block";
}


function renderProduct(product) {
    const divBox = document.createElement("div");
    divBox.innerHTML = `<p>${product.id}</p>`;
    divBox.innerHTML += `<p>name : ${product.name}</p>`;
    divBox.innerHTML += `<p>category : ${product.category}</p>`;
    divBox.innerHTML += `<p>price : ${product.price}</p>`;
    divBox.innerHTML += `<p>color : ${product.color}</p>`;
    console.log(divBox);
    return divBox;
}


function base() {
    return {
        //localStorage : 브라우저에 영속성 저장 영역
        // 저장시 문자만 가능, [], {} 도 모두 문자로 저장
        // object 를 json으로 : JSON.stringify(ObjectData);
        // json 을 object로 : JSON.parse(JSONData)
        // setItem(), getItem()

        setLocalStorage: function() {
            localStorage.setItem("my-name", "data");
            //localStorage.setItem("phone", ["010-1234-5678", "010-2345-6789"]);

            // js 객체를 JSON(JavaScript Object Natation)으로 변경
            const phone = ["010-1234-5678", "010-2345-6789"];
            const jsonPhone = JSON.stringify(phone);
            localStorage.setItem("phone", jsonPhone);
        },

        getLocalStorage: function() {
            const myName = localStorage.getItem("my-name");
            const jsonPhones = localStorage.getItem("phone");
            console.log(`my-name : ${myName}, phone : ${jsonPhones}`);
            console.log(`phone의 타입 : ${typeof (jsonPhones)}`);

            const phone = JSON.parse(jsonPhones);
            console.log(`my-name : ${myName}, phone : ${phone}`);
            console.log(`phone의 타입 : ${typeof (phone)}`);

            phone.forEach((data, i) => {
                console.log(`phone[${i}] : ${data}`);
                console.log(`phone[${i}] : ${typeof (phone[0])}`);
            });
        }

    }
}
