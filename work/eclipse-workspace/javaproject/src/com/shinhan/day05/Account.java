package com.shinhan.day05;

public class Account {
	private String accNo;
	private long balance;
	
	public void deposit(long amount) {
		this.balance += amount;
		System.out.println(this.accNo + "계좌에 " + amount + "원이 입금되었습니다.");
		System.out.println("계좌의 잔고는 " + balance + "원 입니다.");
	}
	public void withdraw(long amount) {
		this.balance -= amount;
		System.out.println(this.accNo + "계좌에 " + amount + "원이 출금되었습니다.");
		System.out.println("계좌의 잔고는 " + balance + "원 입니다.");
	}
	
	public Account() {;}
	
	public Account(String accNo) {
		this(accNo, 0L);
	}
	public Account(String accNo, long balance) {
		this.accNo = accNo;
		this.balance = balance;
		System.out.println(accNo + "계좌가 개설되었습니다.");
		System.out.println(accNo + "계좌의 잔고는 " + balance + "원입니다.");
	}
	
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "Account [accNo=" + accNo + ", balance=" + balance + "]";
	}
}
