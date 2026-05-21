package com.shinhan.day06.quest19;

/**
 * 작성자	: 송병국
 * 작성일	: 2026. 5. 21.
 * 설명	: AccountExample
 */
public class AccountExample {
	public static void main(String[] args) {
		Account account = new Account();
		account.setBalance(10000);
		System.out.println("현재 잔고: " + account.getBalance());    
		account.setBalance(-100);
		System.out.println("현재 잔고: " + account.getBalance());    
		//현재 잔고: 10000
		//현재 잔고: 10000
		account.setBalance(2000000);
		System.out.println("현재 잔고: " + account.getBalance());    
		account.setBalance(300000);
		System.out.println("현재 잔고: " + account.getBalance());    
		//현재 잔고: 10000
		//현재 잔고: 300000
	}
}
