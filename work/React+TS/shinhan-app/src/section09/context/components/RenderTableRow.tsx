import { type todoType } from "../../zustand/types/todo";
const RenderTableRow = ({ data }: { data: todoType }) => {
  return (
    <>
      <tr>
        <td>{data.content}</td>
        <td>{new Date(data.createdDate).toLocaleDateString()}</td>
        <td>{data.isDone.toString()}</td>
      </tr>
    </>
  );
};

export default RenderTableRow;
