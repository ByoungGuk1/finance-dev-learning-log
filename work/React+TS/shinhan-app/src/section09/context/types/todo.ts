export interface todoType {
  id: number;
  content: string;
  isDone: boolean;
  createdDate: number;
}

export interface todoDispatchContextType {
  onCreate: (content: string) => void;
  onUpdate: (
    targetId: number,
    colname: "isDone" | "content",
    value?: string,
  ) => void;
  onDelete: (targetId: number) => void;
}
