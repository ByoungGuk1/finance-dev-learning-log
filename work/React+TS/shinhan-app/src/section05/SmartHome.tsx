import { useState } from "react";
import Light from "./Light";

const SmartHome = () => {
  const [masterOn, setMasterOn] = useState<boolean>(false);
  const [kitchenOn, setKitchenOn] = useState<boolean>(false);
  const [bathOn, setBathOn] = useState<boolean>(false);
  const toggleMaster = () => {
    setMasterOn(!masterOn);
  };
  const toggleKitchen = () => {
    setKitchenOn(!kitchenOn);
  };
  const toggleBath = () => {
    setBathOn(!bathOn);
  };
  return (
    <div>
      <button onClick={toggleMaster}>
        침실<Light on={masterOn} room="침실"></Light>
      </button>
      <button onClick={toggleKitchen}>
        주방<Light on={kitchenOn} room="주방"></Light>
      </button>
      <button onClick={toggleBath}>
        욕조<Light on={bathOn} room="욕조"></Light>
      </button>
    </div>
  );
};

export default SmartHome;
