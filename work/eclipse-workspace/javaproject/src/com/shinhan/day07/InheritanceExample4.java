package com.shinhan.day07;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 22. 오후 2:25:32
 * 설명			: InheritanceExample4
 */
class Parent2{
	int a = 10;
	void f1() {
		System.out.println("Parent2.f1() method");
	}
}

class Child2 extends Parent2{
	int a = 15;
	int b = 20;
	void f1() {
		System.out.println("Child2.f1() Override method");
	}
	void f2() {
		System.out.println("Child2.f2() method");
	}
}
class Child3 extends Parent2{
	int c = 30;
	void f3() {
		System.out.println("Child3.f2() method");
	}
}

public class InheritanceExample4 {
	public static void main(String[] args) {
		test();

		f1();
		f2();
		
		Account acc1 = new Account("111", "a", 1000);
		Account acc2 = new CheckingAccount("112", "b", 1000, "999");
		Account acc3 = new CreditLineAccount("113", "c", 1000, 2000);
		Account acc4 = new BonusPointAccount("114", "d", 1000, 40);
		f3(acc1);
		f3(acc2);
		f3(acc3);
		f3(acc4);
		
		f4();
	}

	private static void test() {
		Parent2 p = new Child2();
		System.out.println(p.a);
		p.f1();
		Child2 c = (Child2) p;
		System.out.println(c.a);
		c.f1();
		Parent2 pp = c;
		System.out.println(pp.a);
		pp.f1();
	}

	private static void f1() {
//	자동 형 변환
//		1. 기본형
//			큰방 <= 작은방	{
		int a;
		char b = 97;
		a = b;
//			}

//		2. 참조형
//			부모 클래스 타입 <= 자식 클래스 타입	{
		Object obj1 = new String("자바");
//		System.out.println(obj1.length());	//	사용불가
//			}
//			참조변수가 부모이므로 부모의 field, method만 보인다.
		
		System.out.println(a);
		System.out.println(obj1);
	}
	private static void f2() {
//		자동 형 변환이 이뤄지는 경우 field와 method는 타입을 따라간다.
//		메서드가 Override 되었다면, 재정의 된 메서드를 수행 
		Account acc1 = new Account("111", "a", 1000);
		Account acc2 = new CheckingAccount("112", "b", 1000, "999");
		Account acc3 = new CreditLineAccount("113", "c", 1000, 2000);
		Account acc4 = new BonusPointAccount("114", "d", 1000, 40);
		
		System.out.println(acc1);
		System.out.println(acc2);
		System.out.println(acc3);
		System.out.println(acc4);
		
		System.out.println(acc3.withdraw(3000));
		acc4.deposit(5000);
		System.out.println(acc4);
	}
//	field와 method는 타입을 따라간다. method는 재정의 되었다면 재정의된 메서드가 수행된다.
	private static void f3(Account acc) {
		System.out.println(acc.getAccNo());
		acc.deposit(10000);
		System.out.println(acc);
		System.out.println(acc.withdraw(13000));
		
//		강제 형 변환
//		1. 기본형
		int a = 97;
		char b;
		b = (char)a;
		System.out.println(b);
//		2. 참조형 - new 로 생성한 instance로 되돌린다.
		if(acc instanceof CheckingAccount data) {
//			CheckingAccount data = (CheckingAccount)acc;
			System.out.println("CheckingAccount");
			data.pay("112", 5);
		}
		if(acc instanceof CreditLineAccount data) {
//			CreditLineAccount data = (CreditLineAccount)acc;
			System.out.println("CreditLineAccount");
			System.out.println(data.creditLine);
		}
		if(acc instanceof BonusPointAccount data) {
//			BonusPointAccount data = (BonusPointAccount)acc;
			System.out.println("BonusPointAccount");
			System.out.println(data.bonusPoint);
		}
	}
	private static void f4() {
//		1. 자동 형 변환
		Parent2 parent = new Child2();
		System.out.println(parent.a);
		parent.f1();
		
//		2. 강제 형 변환
		Child2 child2 = (Child2) parent;
		System.out.println(child2.a);
		System.out.println(child2.b);
		child2.f1();
		child2.f2();
		
////		주의 => 런타임 에러
//		Child3 child3 = (Child3) parent;
//		System.out.println(child3.a);
//		System.out.println(child3.c);
//		child3.f1();
//		child3.f3();
		
//		안전하게 형변환하기
		if(parent instanceof Child2 child) {	//	조건문에서 검사와 동시에 생성하고 저장까지 가능하다.
			System.out.println(child.a);
			System.out.println(child.b);
			child.f1();
			child.f2();
		}
		if(parent instanceof Child3 child) {
			System.out.println(child.a);
			System.out.println(child.c);
			child.f1();
			child.f3();
		}
	}
}
