export interface todoType {
  id: number;
  content: string;
  isDone: boolean;
  createdDate: number;
  writer?: string | void;
}

export interface todoDispatchContextType {
  onCreate: (content: string, writer: string) => void;
  onUpdate: (
    targetId: number,
    colname: "isDone" | "content" | "writer",
    value?: string,
    writer?: string,
  ) => void;
  onDelete: (targetId: number) => void;
}
