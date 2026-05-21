package com.shinhan.day06.quest20;

/**
 * 작성자	: 송병국
 * 작성일	: 2026. 5. 21.
 * 설명	: BackApplication
 */
public class BankApplication {
	public static void main(String[] args) {
		boolean flag = true;
		
		while(flag) {
			int in = AccountView.printMenu();
			switch(in) {
				case 1 -> {
					AccountDTO account = AccountView.inputAccountInfo();
					AccountService.createAccount(account);
				}
				case 2 -> AccountView.printAccounts(AccountService.getAccountList());
				case 3 -> trade("입금");
				case 4 -> trade("출금");
				case 5 -> {
					flag = false;
					System.out.println("정상 종료");
				}
				default -> AccountView.printErrorMessage("선택입력이 잘못됐습니다.");
			}
		}
	}
	
	private static void trade(String value) {
		int money = 0;
		String accNo = null;
		AccountDTO account = null;
		accNo = AccountView.inputAccountNumber();
		account = AccountService.findAccount(accNo, AccountService.getAccountList());
		if(account == null) {
			AccountView.printErrorMessage("계좌를 찾을 수 없습니다.");
			return;
		}
		System.out.print(value);
		money = AccountView.inputMoney();
		if(value.equals("출금")) {
			account = AccountService.withdraw(account, money);
		}else if(value.equals("입금")) {
			account = AccountService.deposit(account, money);
		}
		if(account == null) {
			AccountView.printErrorMessage("거래도중 오류가 발생했습니다.");
		}else {
			AccountView.printAccount(account);
		}
	}
}
