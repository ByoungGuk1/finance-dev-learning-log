import { createRoot } from "react-dom/client";
import "./index.css";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";

import { BrowserRouter } from "react-router-dom";
import StartApp from "./StartApp";

//물리DOM   #root <div id="root"></div>
createRoot(document.getElementById("root")!).render(
  <>
    <div className="flex h-screen bg-gray-100 ">
      <BrowserRouter>
        <StartApp />
      </BrowserRouter>
    </div>
  </>,
);
