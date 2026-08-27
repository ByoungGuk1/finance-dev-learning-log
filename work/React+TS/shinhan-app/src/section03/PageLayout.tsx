import Footer from "./Footer";
import Header from "./Header";
import MainContent from "./MainContent";
import Sidebar from "./Sidebar";

const PageLayout = () => {
  return (
    <div className="min-h-screen w-full bg-gray-100 flex flex-col">
      <Header />
      <div className="flex flex-1">
        <Sidebar />
        <MainContent />
      </div>
      <Footer />
    </div>
  );
};

export default PageLayout;
