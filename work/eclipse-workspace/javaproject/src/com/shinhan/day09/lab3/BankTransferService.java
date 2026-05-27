package com.shinhan.day09.lab3;

//② 구현체 A — 일반 계좌 이체
public class BankTransferService implements PaymentService {

	@Override
	public void transfer(String fromAccount, String toAccount, long amount) {
		System.out.println(fromAccount + " → " + toAccount + " : " + amount + "원 이체");
	}

	@Override
	public long getBalance(String accountNumber) {
		return 1_000_000L; // DB 조회 로직 (생략)
	}
 
}