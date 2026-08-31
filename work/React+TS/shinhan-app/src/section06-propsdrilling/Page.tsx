import Section from "./Section";

export type PropsType = {
  age: number;
  setAge: (age: number) => void;
};

const Page = ({ age, setAge }: PropsType) => {
  console.log("Page 렌더링");
  return (
    <>
      <Section age={age} setAge={setAge} />
    </>
  );
};

export default Page;
