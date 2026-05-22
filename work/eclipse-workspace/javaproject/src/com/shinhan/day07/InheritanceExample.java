package com.shinhan.day07;

/**
 * 작성자	: 송병국
 * 작성일	: 2026. 5. 22.
 * 설명	: InheritenceExampele
 */
public class InheritanceExample {
	public static void main(String[] args) {
		method1();
		method2();
		method3();
		method4();
		method5();
	}
	
	
	private static void method1() {
		Account acc1 = new Account();
		System.out.println(acc1);
	}
	
	private static void method2() {
		CheckingAccount cac1 = new CheckingAccount();
		System.out.println(cac1.getAccNo());
		System.out.println(cac1.getBalance());
		System.out.println(cac1.getCardNo());
		System.out.println(cac1.owner);
		System.out.println(cac1.toString());
		cac1.deposit(0);
		System.out.println(cac1.withdraw(0));
	}
	
	private static void method3() {
		CheckingAccount cac1 = new CheckingAccount("112", "b", 1000, "99-88");
		System.out.println(cac1);
		
		cac1.deposit(500);
		System.out.println(cac1);
		
		System.out.println("출금액 : " + cac1.withdraw(300));
		System.out.println(cac1);
		System.out.println("출금액 : " + cac1.withdraw(3000));
		System.out.println(cac1);
		System.out.println("카드 출금액 : " + cac1.pay("9-88", 600));
		System.out.println(cac1);
		System.out.println("카드 출금액 : " + cac1.pay("99-88", 600));
		System.out.println(cac1);
	}
	
	private static void method4() {
		CreditLineAccount acc1 = new CreditLineAccount("112", "b", 1000, 2000);
		acc1.deposit(3000);
		System.out.println(acc1.withdraw(6000));
		System.out.println(acc1);
		System.out.println(acc1.withdraw(1000));
		System.out.println(acc1);
	}
	
	private static void method5() {
        BonusPointAccount acc1 = new BonusPointAccount("112", "b", 1000, 2000);
	    acc1.deposit(3000);
	    acc1.deposit(4000);
	    System.out.println("잔액:" + acc1.getBalance());
	    System.out.println("누적 포인트:" + acc1.bonusPoint);
	    System.out.println(acc1);
	}
}
