import BoardZustand from "./BoardZustand";
import BoardAfter from "./ContextSplit-After";
import BoardBefore from "./ContextSplit-Before";

const Section08Start = () => {
  return (
    <>
      <BoardAfter />
      <BoardBefore />
      <BoardZustand />
    </>
  );
};

export default Section08Start;
