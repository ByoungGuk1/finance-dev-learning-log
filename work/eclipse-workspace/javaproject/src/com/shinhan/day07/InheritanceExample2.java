package com.shinhan.day07;

/**
 * 작성자	: 송병국
 * 작성일	: 2026. 5. 22.
 * 설명	: InheritanceExample2
 */

//class MyString extends String{}	//	final 사용시 상속 제한

//	추상 class (abstract) : 정의는 되어있지만 구현은 안되어있다.
//class MyNumber extends Number{
//	@Override
//	public int intValue() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public long longValue() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public float floatValue() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public double doubleValue() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//}

/*final */class Parent{	//	final 사용시 상속 제한
	int score = 100;
	
	public Parent(int a) {
		System.out.println("부모 클래스의 생성자");
	}

	void f1() {
		System.out.println("Parent의 f1() 메서드");
	}
}
class Child extends Parent{
	String score = "합격";	//	필드 하이딩
	
	public Child() {
//		부모객체의 기본 생성자가 없으면 오류
		super(100);		//	명시적으로 부모 객체의 생성자 호출
		System.out.println("자식 클래스의 생성자");
	}
	
	@Override
	public void f1() {	//	오버라이딩
		System.out.println("Child의 f1() 메서드(오버라이딩)");
	}
	public void f1(String st) {	//	오버로딩
		System.out.println("Child의 f1(String) 메서드(오버로딩)");
	}
}

public class InheritanceExample2 {
	public static void main(String[] args) {
		Child c1 = new Child();
		System.out.println(c1.score);
		c1.f1();
		c1.f1("");
	}

}
