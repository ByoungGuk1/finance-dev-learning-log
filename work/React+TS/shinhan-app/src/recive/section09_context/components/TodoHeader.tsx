import React from "react";

const TodoHeader = () => {
  return (
    <div className="bg-gradient-to-r from-blue-500 to-purple-600 text-white p-8 rounded-t-2xl shadow-lg">
      <h3 className="text-lg font-medium mb-2">오늘은 </h3>
      <h1 className="text-3xl font-bold">{new Date().toDateString()}</h1>
    </div>
  );
};

export default React.memo(TodoHeader);
