import { add, BASE_URL, type UserType } from "../common/util.ts";

const Footer = () => {
  const user1: UserType = {
    id: 1,
    name: "kim",
    email: "kim@gmail.com",
    phone: "010-1234-1234",
  };

  return (
    <>
      <footer>
        <p>Footer Component</p>
        <p>BASE_URL : {BASE_URL}</p>
        <p>add(10,20) : {add(10, 20)}</p>
        <p>{user1.name}</p>
      </footer>
    </>
  );
};

export default Footer;
