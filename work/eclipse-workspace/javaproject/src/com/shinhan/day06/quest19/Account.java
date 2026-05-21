package com.shinhan.day06.quest19;

/**
 * 작성자	: 송병국
 * 작성일	: 2026. 5. 21.
 * 설명	: Account
 */
public class Account {
	private static final int MIN_BALANCE = 0;
	private static final int MAX_BALANCE = 1_000_000;
	private int balance;	// 최대 약 2G

	public Account() {;}
	public Account(int balance) {
		this.balance = balance;
	}
	
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		if(balance < MIN_BALANCE || balance > MAX_BALANCE) {
			return;
		}
		this.balance = balance;
	}
}
