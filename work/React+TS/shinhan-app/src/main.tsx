import { createRoot } from "react-dom/client";
import "./index.css";
// import {
//   MyFuntionComp1,
//   MyFuntionComp2,
//   MyFuntionComp3,
// } from "./section01/TestComponent01";
// import { MyClassComp1, MyClassComp2 } from "./section01/TestComponent02";
// import App3 from "./section01/App3";
// import Section02Start from "./section02/Section02Start";
// import Section03Start from "./section03/Section03Start";
// import Section04Start from "./section04/Section04Start";
// import Section05Start from "./section05/Section05Start";
// import LabComponent from "./lab/LabComponent";
import Day05Start from "./day05/day05Start";

// const element1 = <h1>hello</h1>;
// const element2 = (
//   <>
//     <h3>hello</h3>
//   </>
// );
// const score = 100;

// 가상DOM
// const virtualDOM = (
//   <>
//     {element1}
//     {element2}
//     <span>점수는 {score}</span>
//     {/*속성 값은 반드시 따옴표 안에 작성*/}
//     <input
//       className="className"
//       type="text"
//       value="신한DS"
//       style={{ backgroundColor: "pink", border: "none" }}
//     />
//     <MyFuntionComp1 />
//     <MyFuntionComp2 />
//     <MyFuntionComp3 />
//     <MyClassComp1 />
//     <MyClassComp2 />
//   </>
// );

// 물리 DOM<div id = "root"></div>
createRoot(document.getElementById("root")!).render(
  <>
    {/* <LabComponent /> */}
    {/* <Section02Start /> */}
    {/* <Section03Start /> */}
    {/* <Section04Start /> */}
    {/* <Section05Start /> */}
    <Day05Start />
    {/* <App3 /> */}
  </>,
);
