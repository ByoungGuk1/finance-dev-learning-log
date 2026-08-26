import type { Account } from "./Lab1";

const accounts: Account[] = [
  {
    accountId: 1,
    accountNumber: "110-111",
    balance: 850000,
    accountType: "SAVINGS",
    ownerName: "김민준",
    isActive: true,
  },
  {
    accountId: 2,
    accountNumber: "110-222",
    balance: 0,
    accountType: "CHECKING",
    ownerName: "이서연",
    isActive: false,
  },
  {
    accountId: 3,
    accountNumber: "110-333",
    balance: 1200000,
    accountType: "SAVINGS",
    ownerName: "박도윤",
    isActive: true,
  },
];

const Lab2 = () => {
  const filterActiveAccounts = (list: Account[]): Account[] => {
    return list.filter((data) => data.isActive === true);
  };

  const getTotalBalance = (list: Account[]): number => {
    return list.reduce((sum, data) => sum + data.balance, 0);
  };

  const formatTotal = (total: number): string => {
    return total.toLocaleString();
  };

  const renderAccount = (accounts: Account[]) => {
    return accounts.map((account) => (
      <ul>
        <li>{account.accountId}</li>
        <li>{account.accountNumber}</li>
        <li>{account.balance}</li>
        <li>{account.accountType}</li>
        <li>{account.ownerName}</li>
        <li>{account.isActive}</li>
      </ul>
    ));
  };

  return (
    <>
      <h3>lab2 start</h3>
      <>filterActiveAccounts:{renderAccount(filterActiveAccounts(accounts))}</>
      <p>getTotalBalance: {getTotalBalance(accounts)}</p>
      <p>formatTotal: {formatTotal(getTotalBalance(accounts))}</p>
      <h3>lab2 end</h3>
    </>
  );
};

export default Lab2;
