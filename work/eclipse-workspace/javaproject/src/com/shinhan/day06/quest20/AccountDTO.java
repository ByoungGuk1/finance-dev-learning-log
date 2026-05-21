package com.shinhan.day06.quest20;

/**
 * 작성자	: 송병국
 * 작성일	: 2026. 5. 21.
 * 설명	: Account
 */
public class AccountDTO {
	private String accountNumber;
	private String name;
	private int balance;
	
	public AccountDTO() {;}
	public AccountDTO(String accountNumber, String name, int balance) {
		this.accountNumber = accountNumber;
		this.name = name;
		this.balance = balance;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", name=" + name + ", balance=" + balance + "]";
	}
}
