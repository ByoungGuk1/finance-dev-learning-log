import BoardZustand from "./BoardZustand";
import BoardAfter from "./ContextSplit-After";
import BoardBefore from "./ContextSplit-Before";
import Debounce_ProfileEditor from "./Debounce-ProfileEditor";

const Section08Start = () => {
  return (
    <>
      <BoardAfter />
      <BoardBefore />
      <BoardZustand />
      <Debounce_ProfileEditor />
    </>
  );
};

export default Section08Start;
