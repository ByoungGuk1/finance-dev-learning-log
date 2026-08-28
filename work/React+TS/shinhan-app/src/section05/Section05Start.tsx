import { useState } from "react";
import PointCount from "./PointCount";
import RefTest from "./RefTest";
import VarCompare from "./VarCompare";
import Board from "./Board";

const Section05Start = () => {
  const [vision, setVision] = useState<boolean>(false);
  const [nowVision] = useState<boolean>(true);

  return (
    <>
      <>{nowVision ? <Board /> : ""}</>
      <button
        className="px-6 py-8 bg-violet-200 rounded-xl max-w-sm self-center"
        onClick={() => {
          setVision((prev) => !prev);
        }}
      >
        이전 컴포넌트 {vision ? "off" : "on"}
      </button>
      <>{vision ? <VarCompare /> : ""}</>
      <>{vision ? <RefTest /> : ""}</>
      <>{vision ? <PointCount /> : ""}</>
    </>
  );
};

export default Section05Start;
