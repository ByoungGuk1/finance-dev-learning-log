package com.shinhan.day09;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 27. 오후 2:33:34
 * 설명			: InterfaceExample2
 */
interface MyInterA{	void f1();	}
interface MyInterB{	void f2();	}
interface MyInterC extends MyInterA, MyInterB{	void f3();	}
interface MyInterD{	void f4();	}

class Parent{	void f5() {}	}

class MyClass extends Parent implements MyInterC, MyInterD{
	@Override
	public void f1() {
		
	}
	@Override
	public void f2() {
		
	}
	@Override
	public void f3() {
		
	}
	@Override
	public void f4() {
		// TODO Auto-generated method stub
		
	}
}

public class InterfaceExample2 {
	public static void main(String[] args) {
		MyClass aa = new MyClass();
		
//		자동 형 변환
		Parent p = aa;
		MyInterA a = aa;
		MyInterB b = aa;
		MyInterC c = aa;
		MyInterD d = aa;
		
		p.f5();
		a.f1();
		b.f2();
		c.f1();
		c.f2();
		c.f3();
		d.f4();
	}
}
