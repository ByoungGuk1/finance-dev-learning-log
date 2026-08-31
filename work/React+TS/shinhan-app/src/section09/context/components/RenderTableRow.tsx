import { type todoType } from "../types/todo";
const RenderTableRow = ({ data }: { data: todoType }) => {
  return (
    <>
      <tr>
        <td>{data.content}</td>
        <td>{data.writer ? data.writer : "-"}</td>
        <td>{new Date(data.createdDate).toLocaleDateString()}</td>
        <td>{data.isDone.toString()}</td>
      </tr>
    </>
  );
};

export default RenderTableRow;
