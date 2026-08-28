import { useReducer, type ChangeEvent } from "react";

type Subject = "Java" | "Web" | "JavaScript" | "Spring";
export type User = {
  name: string;
  age: number;
  phone: string;
  subjects: Subject[];
};

/** ===== 과목을 상수로 저장  =====*/
const SUBJECTS: Subject[] = ["Java", "Web", "JavaScript", "Spring"];

/** ===== 상태 타입 ===== */
type State = {
  user: User;
  userList: User[];
};

/** ===== 액션 타입 ===== */
type Action =
  | { type: "CHANGE_FIELD"; name: "name" | "age" | "phone"; value: string }
  | { type: "TOGGLE_SUBJECT"; subject: Subject }
  | { type: "SUBMIT" }
  | { type: "RESET" }
  | { type: "ROW_CLICK"; subject: string };

/** ===== 초기 상태 ===== */
const initialState: State = {
  user: { name: "", age: 0, phone: "", subjects: [] },
  userList: [],
};

/** ===== reducer ===== */
function userReducer(state: State, action: Action): State {
  switch (action.type) {
    case "CHANGE_FIELD": {
      const { name, value } = action;
      return {
        ...state,
        user: {
          ...state.user,
          [name]: name === "age" ? Number(value) : value,
        },
      };
    }

    case "TOGGLE_SUBJECT": {
      const { subject } = action;
      const isSelected = state.user.subjects.includes(subject);

      return {
        ...state,
        user: {
          ...state.user,
          subjects: isSelected
            ? state.user.subjects.filter((s) => s !== subject)
            : [...state.user.subjects, subject],
        },
      };
    }

    case "SUBMIT": {
      if (!state.user.name || !state.user.age) return state;

      return {
        user: initialState.user,
        userList: [...state.userList, state.user],
      };
    }

    case "RESET":
      return initialState;

    case "ROW_CLICK": {
      const clickedName = action.subject;
      if (clickedName) {
        const clickedUser = state.userList.find((u) => u.name === clickedName);
        if (clickedUser) {
          return { ...state, user: clickedUser };
        }
      }
      return state;
    }
    default:
      return state;
  }
}

const UserFormUsingReduce = () => {
  const [state, dispatch] = useReducer(userReducer, initialState);
  const { user, userList } = state;
  const handleChange = (e: ChangeEvent<HTMLInputElement>): void => {
    const { name, value } = e.target;
    dispatch({
      type: "CHANGE_FIELD",
      name: name as "name" | "age" | "phone",
      value,
    });
  };
  const handleSubjectChange = (subject: Subject): void => {
    dispatch({
      type: "TOGGLE_SUBJECT",
      subject,
    });
  };

  const handleSubmit = (): void => {
    dispatch({ type: "SUBMIT" });
  };
  const listClickHandler = (e: React.MouseEvent<HTMLLIElement>) => {
    const clickedName = e.currentTarget.textContent?.split(" ")[0];
    dispatch({ type: "ROW_CLICK", subject: clickedName });
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
          onChange={handleChange}
        />
        <input
          type="number"
          name="age"
          value={user.age || ""}
          placeholder="나이"
          className="w-full border px-3 py-2 rounded"
          onChange={handleChange}
        />
        <input
          type="text"
          name="phone"
          value={user.phone}
          placeholder="전화번호"
          className="w-full border px-3 py-2 rounded"
          onChange={handleChange}
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
          onClick={handleSubmit}
          className="w-full bg-blue-500 text-white py-2 rounded hover:bg-blue-600 transition"
        >
          배열등록
        </button>

        {userList.length !== 0 ? (
          <>
            <h2 className="text-lg font-semibold mt-6">등록된 사용자 목록</h2>
            <ul className="mt-4 space-y-2">
              {userList.map((data, index) => (
                <li
                  key={index}
                  className="p-2 bg-gray-50 rounded"
                  onClick={listClickHandler}
                >
                  {data.name} ({data.age}세){" "}
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

export default UserFormUsingReduce;
