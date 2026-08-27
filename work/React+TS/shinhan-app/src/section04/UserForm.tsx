import { useState, type ChangeEvent } from "react";
type SubjectType = "Java" | "Web" | "JavaScript" | "Spring";
type UserType = {
  name: string;
  age: number;
  phone?: string;
  subjects?: SubjectType[];
};

const UserForm = () => {
  const SUBJECTS: SubjectType[] = ["Java", "Web", "JavaScript", "Spring"];
  const initUser: UserType = { name: "", age: 0, phone: "", subjects: [] };
  const [user, setUser] = useState(initUser);
  const [userList, setUserList] = useState<UserType[]>([]);

  const onChangeInput = (e: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    // if (value === "Enter") {
    //   setUserList((prev) => ({ ...prev, user }));
    //   return;
    // }
    setUser((prev) =>
      prev
        ? { ...prev, [name]: name === "age" ? Number(value) : value }
        : initUser,
    );
  };

  const handleSubjectChange = (subject: SubjectType): void => {
    setUser((prev: UserType) => {
      if (!prev) return prev;

      const isSelected = prev.subjects?.includes(subject);

      return {
        ...prev,
        subjects: isSelected
          ? prev.subjects?.filter((s) => s !== subject)
          : [...prev.subjects, subject],
      };
    });
  };

  const onClickSubmitBtn = () => {
    if (!user.name || !user.age) return;
    setUserList((prev) => [...prev, user]);
    setUser(initUser);
  };

  return (
    <>
      <div className="max-w-sm mx-auto p-4 space-y-4 bg-white rounded shadow">
        <h2 className="text-lg font-semibold">사용자 등록</h2>
        <input
          type="text"
          name="name"
          placeholder="이름"
          value={user.name}
          className="w-full border px-3 py-2 rounded"
          onChange={onChangeInput}
        />
        <input
          type="number"
          name="age"
          value={user.age || ""}
          placeholder="나이"
          className="w-full border px-3 py-2 rounded"
          onChange={onChangeInput}
        />
        <input
          type="text"
          name="phone"
          value={user.phone}
          placeholder="전화번호"
          className="w-full border px-3 py-2 rounded"
          onChange={onChangeInput}
        />
        <div className="space-y-2">
          <p className="font-medium">좋아하는 과목</p>
          {SUBJECTS.map((subject) => (
            <label key={subject} className="flex items-center gap-2 text-sm">
              <input
                type="checkbox"
                checked={user.subjects?.includes(subject)}
                onChange={() => handleSubjectChange(subject)}
              />
              {subject}
            </label>
          ))}
        </div>

        {user?.name && user?.age ? (
          <div className="mt-4 p-3 bg-gray-100 rounded space-y-1">
            <p>이름: {user.name}</p>
            <p>나이: {user.age}</p>
            <p>전화: {user.phone || "-"}</p>
          </div>
        ) : (
          <p className="text-sm text-gray-500">등록할 사용자가 없습니다.</p>
        )}
        <button
          onClick={onClickSubmitBtn}
          className="w-full bg-blue-500 text-white py-2 rounded hover:bg-blue-600 transition"
        >
          배열등록
        </button>

        {userList.length !== 0 ? (
          <>
            <h2 className="text-lg font-semibold mt-6">등록된 사용자 목록</h2>
            <ul className="mt-4 space-y-2">
              {userList.map((data, index) => (
                <li key={index} className="p-2 bg-gray-50 rounded">
                  {index + 1} : {data.name} ({data.age}세){" "}
                  {data.phone && <>phone({data.phone})</>}{" "}
                  {data.subjects?.join(", ")}
                </li>
              ))}
            </ul>
          </>
        ) : (
          <p className="text-sm text-gray-500">
            현재 등록된 사용자가 없습니다.
          </p>
        )}
      </div>
    </>
  );
};

export default UserForm;
