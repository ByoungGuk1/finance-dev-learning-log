const BASE_URL = "http://localhost:8000/api";

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
