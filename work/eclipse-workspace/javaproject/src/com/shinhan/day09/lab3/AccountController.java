package com.shinhan.day09.lab3;

//사용자가 요청-->Main-->Controller-->Service-->DAO--->DB

public class AccountController {
	//인터페이스 타입 ...구현체가 달라질수있음 
	PaymentService paymentService;
	public AccountController(PaymentService paymentService){
		this.paymentService = paymentService;
	}
	public void processTransfer() {
		paymentService.transfer("112", "999", 50_000);
	}

	public void f1(int amount) {
		paymentService.printReceipt(amount);
		PaymentService.printReceipt2(amount);
	}
}
