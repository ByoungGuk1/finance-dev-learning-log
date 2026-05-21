package com.shinhan.day06.quest20;

/**
 * 작성자	: 송병국
 * 작성일	: 2026. 5. 21.
 * 설명	: AccountService
 */
public class AccountService {
	private static AccountDTO[] accountList = new AccountDTO[100];
	private static int accountListIndex = 0;
	
	public static AccountDTO[] getAccountList() {
		return accountList;
	}
	
	public static AccountDTO findAccount(String accNo, AccountDTO[] accountList) {
		AccountDTO account = null;
		for(int index = 0; index < accountList.length; index++) {
			if(accountList[index] == null) break;
			if(accountList[index].getAccountNumber().equals(accNo)) {
				account = accountList[index];
				break;
			}
		}
		return account;
	}
	public static AccountDTO createAccount(AccountDTO account) {
		if(accountList[accountList.length - 1] != null) {
			AccountView.printErrorMessage("계좌가 가득 찼습니다.");
			return null;
		}
		accountList[accountListIndex++] = account;
		return account;
	}
	public static AccountDTO deposit(AccountDTO account, int money) {
		account.setBalance(account.getBalance() + money);
		return account;
	}
	public static AccountDTO withdraw(AccountDTO account, int money) {
		AccountDTO result = account;
		if(account.getBalance() < money) {
			return null;
		}
		account.setBalance(account.getBalance() - money);
		return result;
	}
}
