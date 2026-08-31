import { useState } from "react";
import Page from "./Page";

const AppStart = () => {
  console.log("App 렌더링 ");
  const [age, setAge] = useState<number>(20);
  return (
    <>
      <Page age={age} setAge={setAge} />
    </>
  );
};

export default AppStart;
