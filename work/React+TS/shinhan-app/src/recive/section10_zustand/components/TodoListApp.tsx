import TodoEditor from "./TodoEditor";
import TodoList from "./TodoList";
import TodoHeader from "./TodoHeader";

export default function TodoListApp() {
  return (
    <div className="min-h-screen w-full  bg-gradient-to-br from-blue-50 to-purple-50 p-8">
      <div className="max-w-4xl mx-auto shadow-2xl rounded-2xl overflow-hidden">
        <TodoHeader />
        <TodoEditor />
        <TodoList />
      </div>
    </div>
  );
}
