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

function fetchBalance(accountId: number): Promise<number> {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      if (Math.random() > 0.2) {
        resolve(findBalanceByAccountId(accountId));
      } else {
        reject("[err]");
      }
    }, 1000);
  });
}

function findBalanceByAccountId(accountId: number): number {
  return accounts
    .filter((data) => data.accountId === accountId)
    .map((acc) => acc.balance)[0];
}

async function checkBalance(accountId: number): Promise<void> {
  try {
    const result = await fetchBalance(accountId);
    console.log(`잔액 조회 성공: ${result}원`);
  } catch (err) {
    console.log("조회 실패, 다시 시도해주세요.", err);
  }
}
checkBalance(3);

const Lab3 = () => {
  return <></>;
};

export default Lab3;
