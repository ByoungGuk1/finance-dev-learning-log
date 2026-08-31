import type { ChangeEvent } from "react";
import useAge from "../hooks/useAge";

const ProfileEditor = () => {
  console.log("ProfileEditor 렌더링");
  const { age, setAge } = useAge();
  const handleAgeChange = (e: ChangeEvent<HTMLInputElement>) => {
    setAge(Number(e.target.value));
  };
  return (
    <>
      <div className="border-2">
        <p>나이: {age}</p>
        <span>나이입력:</span>
        <input
          className="border-2 border-blue-500"
          type="number"
          value={age}
          onChange={handleAgeChange}
        />
      </div>
    </>
  );
};

export default ProfileEditor;
