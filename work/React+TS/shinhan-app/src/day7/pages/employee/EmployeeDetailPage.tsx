import { useParams, Link } from "react-router-dom";

export default function EmployeeDetailPage() {
  const { employeeId } = useParams<{ employeeId: string }>();

  return (
    <div className="rounded-lg border border-slate-200 bg-white p-4">
      <p className="text-xs text-slate-400">employeeId (useParams)</p>
      <p className="mt-1 text-lg font-bold text-slate-800">{employeeId}</p>
      <Link to="/employees" className="mt-3 inline-block text-sm text-teal-600 hover:underline">
        ← 목록으로
      </Link>
    </div>
  );
}
