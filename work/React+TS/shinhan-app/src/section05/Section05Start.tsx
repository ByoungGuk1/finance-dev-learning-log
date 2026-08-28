import { useState } from "react";
import PointCount from "./PointCount";

const Section05Start = () => {
  const [vision, setVision] = useState<boolean>(true);

  return (
    <>
      <button
        className="px-6 py-8 bg-violet-200 rounded-xl max-w-sm self-center"
        onClick={() => {
          setVision((prev) => !prev);
        }}
      >
        포인트 컴포넌트 {vision ? "off" : "on"}
      </button>
      {vision ? <PointCount /> : ""}
    </>
  );
};

export default Section05Start;
