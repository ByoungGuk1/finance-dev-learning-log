import LinkComponent from "./common/LinkComponent";
import CommonRoutes from "./common/CommonRoutes";

function StartApp() {
  return (
    <>
      {/* Sidebar */}
      <div className="flex h-screen w-full bg-gray-100">
        <LinkComponent />
        <main className="flex-1 min-w-0 p-6 overflow-auto">
          <div className="bg-white rounded shadow p-6 min-h-full">
            {/* CommonRoutes */}
            <CommonRoutes />
          </div>
        </main>
      </div>
    </>
  );
}
export default StartApp;
