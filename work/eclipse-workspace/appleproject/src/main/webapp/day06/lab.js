const postUrl = 'https://jsonplaceholder.typicode.com/posts';

window.onload = () => {
    const div1 = document.querySelector("#lab1");
    const div2 = document.querySelector("#lab2");
    const btn1 = document.querySelector("#btn-lab1");
    const btn2 = document.querySelector("#btn-lab2");
    const btnCity = document.querySelector("#select");

    btn1.addEventListener("click", (e) => {
        div2.style.display = "none";
        div1.style.display = "block";
        renderTable();
    });
    btn2.addEventListener("click", (e) => {
        div1.style.display = "none";
        div2.style.display = "block";
    });
    btnCity.addEventListener("click", (e) => {
        renderWeather();
    });
}

const getData = async (url) => {
    const res = await fetch(url);
    if (!res.ok) throw new Error(`HTTP ${res.status}`);
    return res.json();
};

async function renderTable() {
    const div1 = document.querySelector("#lab1");
    try {
        const htmlText = `<table>
					<thead>
						<td>userId</td>
						<td>id</td>
						<td>title</td>
						<td>contents</td>
					</thead>
					<tbody>
						${await getDataHtml()}
					</tbody>
				</table>`;
        div1.innerHTML = htmlText;
    }
    catch (err) {
        throw Error(err);
    }
}

async function renderWeather() {
    const div2 = document.querySelector("#lab2");
    const cityData = await findCity();
    const curTemp = await getTemperature(cityData.latitude, cityData.longitude);
    div2.innerHTML += `<h2>현재온도 : ${curTemp}</h2>
		<p>경도 : ${cityData.latitude}</p>
		<p>위도 : ${cityData.longitude}</p>`;
    console.log(cityData.cityName);
}

async function getTemperature(latitude, longitude) {
    const url = `https://api.open-meteo.com/v1/forecast?latitude=${latitude}&longitude=${longitude}&current=temperature_2m`
    return await getData(url)
        .then(({ current }) => current.temperature_2m);
}

async function findCity() {
    const city = getInputCity();
    const url = `https://geocoding-api.open-meteo.com/v1/search?name=${city}&count=1`;
    const cityData = await getData(url)
        .then(({ results }) => {
            const cityName = results[0].name;
            const latitude = results[0].latitude;
            const longitude = results[0].longitude;
            const data = { cityName, latitude, longitude };
            return data;
        });
    return cityData;
}

async function getDataHtml() {
    return await getData(postUrl).then((dataObject) =>
        dataObject.map((data) =>
            `<tr>
                            <td>
                              <span>${data.userId}</span>
														</td>
														<td>
                            	<span>${data.id}</span>
														</td>
														<td>
                            	<span>${data.title}</span>
														</td>
														<td>
                            	<span>${data.body}</span>
														</td>
                          </tr>
                          `
        ))
}

function getInputCity() {
    return document.querySelector("#city").getAttribute("value");
}