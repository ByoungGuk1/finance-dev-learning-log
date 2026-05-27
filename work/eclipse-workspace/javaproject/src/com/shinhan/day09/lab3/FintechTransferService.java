package com.shinhan.day09.lab3;

//③ 구현체 B — 핀테크 간편송금
public class FintechTransferService implements PaymentService {

	@Override
	public void transfer(String fromAccount, String toAccount, long amount) {
		System.out.println("[핀테크] 즉시송금: " + amount + "원");
	}

	@Override
	public long getBalance(String accountNumber) {
		return 500_000L;
	}

	@Override
	public void printReceipt(int amount) {
		PaymentService.super.printReceipt(amount);
		System.out.println("..");
	}
}