import { Link } from "react-router-dom";

const employees = [
  { id: "1", name: "김신한", dept: "개발팀" },
  { id: "2", name: "이금융", dept: "인사팀" },
  { id: "3", name: "박은행", dept: "영업팀" },
];

export default function EmployeeListPage() {
  return (
    <div>
      <h1 className="mb-3 text-lg font-bold text-slate-800">직원 목록</h1>
      <ul className="space-y-2">
        {employees.map((e) => (
          <li key={e.id}>
            <Link
              to={`/employees/${e.id}`}
              className="block rounded-lg border border-slate-200 bg-white p-3 text-sm hover:border-teal-400"
            >
              {e.name} · <span className="text-slate-400">{e.dept}</span>
            </Link>
          </li>
        ))}
      </ul>
    </div>
  );
}
