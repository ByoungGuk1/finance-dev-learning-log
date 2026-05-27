package com.shinhan.day09.lab3;

//① 인터페이스 = 계약서 (규격만 정의)
public interface PaymentService {
	void transfer(String fromAccount, String toAccount, long amount);

	long getBalance(String accountNumber);
	
//	구현체의 공통 기능을 구현, Override 가능
	default void printReceipt(int amount) {
		System.out.println("내역 : " + amount);
	}
	static void printReceipt2(int amount) {
		System.out.println("내역 : " + amount);
	}
}