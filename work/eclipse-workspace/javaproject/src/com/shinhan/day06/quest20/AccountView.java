package com.shinhan.day06.quest20;

import java.util.Scanner;

/**
 * 작성자	: 송병국
 * 작성일	: 2026. 5. 21.
 * 설명	: AccountView
 */
public class AccountView {
	static Scanner sc = new Scanner(System.in);
	public static int printMenu() {
		System.out.println("--------------------");
		System.out.println("1. 계좌생성 | 2. 계좌목록 | 3. 예금 | 4. 출금 | 5. 종료");
		System.out.println("--------------------");
		int in = sc.nextInt();
		sc.nextLine();
		return in;
	}
	public static void printAccount(AccountDTO account) {
		if(account == null) return;
		System.out.println("----------");
		System.out.println("계좌정보");
		System.out.println("----------");
		System.out.println(account.getAccountNumber()+"\t"+account.getName()+"\t"+account.getBalance());
	}
	public static void printAccounts(AccountDTO ...accounts ) {
		System.out.println("----------");
		System.out.println("계좌목록");
		System.out.println("----------");
		for(AccountDTO account : accounts) {
			if(account == null) break;
			System.out.println(account.getAccountNumber()+"\t"+account.getName()+"\t"+account.getBalance());
		}
	}
	public static void printErrorMessage(String message) {
		System.err.println(message);
	}
	public static String inputAccountNumber() {
		System.out.print("계좌번호 입력 : ");
		String accountNumber = sc.nextLine();
		return accountNumber;
	}
	public static AccountDTO inputAccountInfo() {
		String accountNumber = null;
		String name = null;
		int balance = 0;
		AccountDTO account = null;
		
		System.out.println("----------");
		System.out.println("계좌 생성");
		System.out.println("----------");
		System.out.print("계좌번호 : ");
		accountNumber = sc.nextLine();
		System.out.print("계좌주 : ");
		name = sc.nextLine();
		System.out.print("초기 입금액 : ");
		balance = sc.nextInt();
		
		account = new AccountDTO(accountNumber, name, balance);
		return account;
	}
	public static int inputMoney() {
		System.out.print("할 금액 입력 : ");
		int money = sc.nextInt();
		sc.nextLine();
		return money;
	}
}
