const Sidebar = () => {
  const menuItemBase =
    "px-3 py-2 rounded cursor-pointer transition-colors text-gray-800";
  const menuHover = [
    { label: "직원관리", hover: "hover:bg-blue-100 hover:text-blue-700" },
    { label: "게시글관리", hover: "hover:bg-green-100 hover:text-green-700" },
    { label: "계약관리", hover: "hover:bg-purple-100 hover:text-purple-700" },
  ];
  return (
    <aside className="w-60 bg-white shadow-sm p-4">
      <ul className="space-y-1 text-sm">
        {menuHover.map((menu) => (
          <li key={menu.label} className={`${menuItemBase} ${menu.hover}`}>
            {menu.label}
          </li>
        ))}
      </ul>
    </aside>
  );
};

export default Sidebar;
