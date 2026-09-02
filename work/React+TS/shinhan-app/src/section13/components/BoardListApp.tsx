import BoardHeader from "./BoardHeader";
import BoardList from "./BoardList";

export default function BoardListApp() {
  return (
    <div className="min-h-screen w-full bg-gray-50 p-8">
      <div className="max-w-2xl mx-auto">
        <BoardHeader />
        <BoardList />
      </div>
    </div>
  );
}
