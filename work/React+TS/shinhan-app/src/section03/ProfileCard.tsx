const ProfileCard = () => {
  return (
    <div className="bg-white rounded-lg shadow p-4 flex items-center gap-4">
      <img
        src="https://i.pravatar.cc/150?img=2"
        alt="profile"
        className="w-20 h-20 rounded-full object-cover"
      />

      <div>
        <h3 className="font-semibold text-lg">홍길동</h3>
        <p className="text-sm text-gray-500">Frontend Developer</p>
      </div>
    </div>
  );
};

export default ProfileCard;
