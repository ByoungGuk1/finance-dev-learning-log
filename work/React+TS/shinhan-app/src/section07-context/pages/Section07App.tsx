import Page from "../components/Page";
import AgeProvider from "../contexts/AgeProvider";

const Section07App = () => {
  console.log("App8 렌더링");

  return (
    <>
      <AgeProvider>
        <Page />
      </AgeProvider>
    </>
  );
};

export default Section07App;
