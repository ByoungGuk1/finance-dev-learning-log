package com.shinhan.day05;

public class BankExample {
	public static void main(String[] args) {
		f1();
	}
	private static void f1() {
		System.out.println(BankCustomer.count);
//		1. 객체 참조 변수 선언
		BankCustomer cust1, cust2, cust3, cust4;
		
//		2. 객체 생성
		cust1 = new BankCustomer();
		cust2 = new BankCustomer("김길동");
		cust3 = new BankCustomer("홍길동", 20);
		cust4 = new BankCustomer("박길동", 30, 2000);
		System.out.println();
		
//		3. 객체 사용
//		cust1.name = "초기화 되지 않은 사용자";
//		cust1.age = -1;
//		cust1.balance = -1;
//			참조변수를 이용해서 필드에 접근
//				정보은닉 / 캡슐화를 위해 사용되지 않음
		cust1.setName("이름");
		cust1.setAge(25);
		cust1.setBalance(3000);

		System.out.println(cust1.getName());
		System.out.println(cust1.getAge());
		System.out.println(cust1.getBalance());
		
		cust1.print();
		System.out.println();
		cust2.print();
		System.out.println();
		cust3.print();
		System.out.println();
		cust4.print();
		
		System.out.println(BankCustomer.count);
	}
}
