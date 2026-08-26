type InfoType = {
  custId: number;
  custName: string;
};

const datas: InfoType[] = [
  {
    custId: 100,
    custName: "홍길동",
  },
  {
    custId: 101,
    custName: "홍길동",
  },
  {
    custId: 102,
    custName: "홍길동",
  },
];

const ChildComponent = () => {
  const onClickBtn1 = () => {
    alert("버튼클릭");
  };

  const onChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    console.log(e.target.value);
  };

  const renderCustInfo = (datas: InfoType[]) => {
    return datas.map((data) => {
      return (
        <>
          <ChildComponent1 {...data} />
          <br />
          <button onClick={onClickBtn1}>버튼</button>
          <input onChange={onChangeHandler} />
          <br />
          <br />
        </>
      );
    });
  };

  return (
    <>
      <h1>고객정보</h1>
      <ul>{renderCustInfo(datas)}</ul>
    </>
  );
};

function ChildComponent1({ custId, custName }: InfoType) {
  return (
    <>
      <li>
        <p>고객 번호: {custId}</p>
      </li>
      <li>
        <p>고객 이름: {custName}</p>
      </li>
    </>
  );
}

export default ChildComponent;
