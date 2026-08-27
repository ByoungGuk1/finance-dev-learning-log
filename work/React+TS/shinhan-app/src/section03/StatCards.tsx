import StatCard from "./SataCardProps";

const StatCards = () => {
  return (
    <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
      <StatCard title="게시글" value="24" />
      <StatCard title="댓글" value="128" />
      <StatCard title="계약" value="3" />
    </div>
  );
};

export default StatCards;
