import PostList from "./PostList";
import ProfileCard from "./ProfileCard";
import StatCards from "./StatCards";

const MainContent = () => {
  return (
    <main className="flex-1 p-6 space-y-6">
      <ProfileCard />
      <StatCards />
      <PostList />
    </main>
  );
};

export default MainContent;
