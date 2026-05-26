package com.shinhan.day08;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 26. 오후 2:41:19
 * 설명			: InheritenceExample
 */
class Parent{
	int score1 = 100;
	void f1() {
		System.out.println("부모에서 만든 f1() 입니다.");
	}
}
class ChildA extends Parent{
	String score1 = "백점";
	int subject = 7;
	void f1() {
		System.out.println("ChildA에서 재정의된 f1() 입니다.");
	}
}
class ChildB extends Parent{
	boolean score1 = true;
	public void f1() {
		System.out.println("ChildB에서 재정의된 f1() 입니다.");
	}
}

public class InheritenceExample {
	public static void main(String[] args) {
		ChildA a = new ChildA();
		System.out.println(a.score1);
		ChildB b = new ChildB();
		System.out.println(b.score1);
		work(a);
		work(b);
	}
	
	private static void work(Parent p) {
//		Field는 타입을 따른다.
		System.out.println(p.score1);
		p.f1();
		if(p instanceof ChildA a) {
			System.out.println("A는 "+a.subject);
		}
	}
}
