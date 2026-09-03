import { createRoot } from "react-dom/client";
import "./index.css";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";

// import { BrowserRouter } from "react-router-dom";
// import StartApp from "./StartApp";
// import { AuthProvider } from "./auth/AuthProvider";
import AppDay7 from "./day7/AppDay7";

//물리DOM   #root <div id="root"></div>
createRoot(document.getElementById("root")!).render(
  <>
    <div className="flex h-screen bg-gray-100 ">
      {/* <AuthProvider>
        <BrowserRouter>
          <StartApp />
        </BrowserRouter>
      </AuthProvider> */}
      <AppDay7 />
    </div>
  </>,
);
