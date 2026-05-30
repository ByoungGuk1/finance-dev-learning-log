package com.shinhan.day07;

/**
 * 작성자	: 송병국
 * 작성일	: 2026. 5. 22.
 * 설명	: Account
 */
public /*final*/ class Account {	//	final을 사용하면 상속이 제한된다.
	public String accNo;
	protected String owner;
	private int balance;
	
	public Account() {
		System.out.println("Account default constructor");
	}
	
	public Account(String accNo, String owner, int balance) {
		super();
		this.accNo = accNo;
		this.owner = owner;
		this.balance = balance;
		System.out.println("Account argument 3 cunstructor");
	}

	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}

	public void deposit(int amount) {
		balance += amount;
	}
	public int withdraw(int amount) {
		if(balance < amount) {
			System.err.println("잔액 부족");
			return 0;
		}
		balance -= amount;
		return amount;
	}

	@Override
	public String toString() {
		return "Account(부모 객체) [accNo=" + accNo + ", owner=" + owner + ", balance=" + balance + "]";
	}

}
