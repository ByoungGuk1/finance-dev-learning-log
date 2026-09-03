import { Link } from "react-router-dom";

const contracts = [
  { id: "1", title: "국내시장복귀계좌(RIA) 이용약관" },
  { id: "2", title: "전자금융거래 이용약관" },
];

export default function ContractListPage() {
  return (
    <div>
      <h1 className="mb-3 text-lg font-bold text-slate-800">계약 목록</h1>
      <ul className="space-y-2">
        {contracts.map((c) => (
          <li key={c.id}>
            <Link
              to={`/contracts/${c.id}`}
              className="block rounded-lg border border-slate-200 bg-white p-3 text-sm hover:border-teal-400"
            >
              {c.title}
            </Link>
          </li>
        ))}
      </ul>
    </div>
  );
}
