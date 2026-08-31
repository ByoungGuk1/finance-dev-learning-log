import type { PropsType } from "./Page";
import ProfileEditor from "./ProfileEditor";

const Section = ({ age, setAge }: PropsType) => {
  console.log("Section 렌더링");
  return (
    <>
      <ProfileEditor age={age} setAge={setAge} />
    </>
  );
};

export default Section;
