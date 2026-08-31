import Header from "./Header";
import TodoEditor from "./TodoEditor";
import TodoList from "./TodoList";

const TodoListApp_zustand = () => {
  return (
    <>
      <div className="min-h-screen w-full  bg-gradient-to-br from-blue-50 to-purple-50 p-8">
        <div className="max-w-4xl mx-auto shadow-2xl rounded-2xl overflow-hidden">
          <Header />
          <TodoEditor />
          <TodoList />
        </div>
      </div>
    </>
  );
};

export default TodoListApp_zustand;
