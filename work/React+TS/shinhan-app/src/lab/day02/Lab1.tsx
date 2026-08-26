export interface Account {
  accountId: number;
  accountNumber: string;
  balance: number;
  accountType: "SAVINGS" | "CHECKING";
  ownerName: string;
  isActive?: boolean;
}

const acc1: Account = {
  accountId: 1,
  accountNumber: "110-222-333333",
  balance: 850000,
  accountType: "SAVINGS",
  ownerName: "hong",
  isActive: true,
};

const Lab1 = () => {
  function findById<T extends { accountId: number }>(
    list: T[],
    id: number,
  ): T | undefined {
    return list.find((data) => {
      return data.accountId === id;
    });
  }

  const renderResult = () => {
    const {
      accountId,
      accountNumber,
      balance,
      accountType,
      ownerName,
      isActive,
    } = findById([acc1], 1)!;
    return (
      <li>
        <div>
          <p>{accountId}</p>
          <p>{accountNumber}</p>
          <p>{balance}</p>
          <p>{accountType}</p>
          <p>{ownerName}</p>
          <p>{isActive}</p>
        </div>
      </li>
    );
  };
  return (
    <>
      <h3>lab1 start</h3>
      <ul>{renderResult()}</ul>
      <h3>lab1 end</h3>
    </>
  );
};

export default Lab1;
