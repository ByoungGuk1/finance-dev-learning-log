package com.shinhan.day09.lab3;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 27. 오후 2:17:58
 * 설명			: Main
 */
public class Main {
	public static void main(String[] args) {
//		interface type ps = 구현체 -> 자동 형 변환
		PaymentService ps1 = new BankTransferService();
		PaymentService ps2 = new FintechTransferService();
		
		AccountController ac1 = new AccountController(ps1);
		AccountController ac2 = new AccountController(ps2);
		
		ac1.processTransfer();
		ac2.processTransfer();
		
		ac1.f1(10000);
		ac2.f1(20000);
	}

}
