const BASE_URL =
  import.meta.env.VITE_API_BASE_URL + "/api" || "http://localhost:8000/api";

function add(a: number, b: number): number {
  return a + b;
}

type UserType = {
  id: number;
  name: string;
  email?: string;
  phone?: string;
};

export { BASE_URL, add, type UserType };
