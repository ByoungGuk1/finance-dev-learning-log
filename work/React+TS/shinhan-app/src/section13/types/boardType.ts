import { create } from "zustand";
import axios from "axios";
import { BASE_URL } from "@/common/util";

export interface postType {
  bno: number;
  title: string;
  content: string;
  writer: string;
  regDate?: number;
  modDate?: number;
}

// axios로 값을 가져와서 zustand로 관리하기
interface BoardStoreType {
  // 상태
  posts: postType[];

  // 액션
  fetchPosts: () => void;
  onCreate: (title: string, content: string, writer: string) => Promise<void>;
  onUpdate: (
    targetId: number,
    title: string,
    content: string,
    writer: string,
  ) => Promise<void>;
  onDelete: (targetId: number) => Promise<void>;
}

const useBoardStore = create<BoardStoreType>((set) => ({
  posts: [],

  fetchPosts: () => {
    axios({ url: `${BASE_URL}/freeboard/list`, method: "get" })
      .then((response) => {
        set({ posts: response.data });
      })
      .catch((err) => console.log(err));
  },

  onCreate: (title, content, writer) =>
    axios({
      url: `${BASE_URL}/freeboard/register`,
      method: "POST",
      data: { title, content, writer },
    })
      .then((response) => {
        set((state) => ({ posts: [response.data, ...state.posts] }));
      })
      .catch((err) => console.log(err)),

  onUpdate: (targetId, title, content, writer) =>
    axios({
      url: `${BASE_URL}/freeboard/modify`,
      method: "put",
      data: { title, content, writer, bno: `${targetId}` },
    })
      .then(() => {
        set((state) => ({
          posts: state.posts.map((it) =>
            it.bno === targetId ? { ...it, title, content, writer } : it,
          ),
        }));
      })
      .catch((err) => console.log(err)),

  onDelete: (targetId) =>
    axios({
      url: `${BASE_URL}/freeboard/remove`,
      method: "delete",
      params: { bno: targetId },
    })
      .then(() => {
        set((state) => ({
          posts: state.posts.filter((it) => it.bno !== targetId),
        }));
      })
      .catch((err) => console.log(err)),
}));

export default useBoardStore;
