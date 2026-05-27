package com.shinhan.day09.lab2;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 27. 오후 12:35:02
 * 설명			: Test
 */
interface A {void f1();}
class B implements A{
	@Override
	public void f1() {;}
	public void f2() {}
}
class C implements A{
	@Override
	public void f1() {;}
}
class D extends B{}
class E extends C{}

public class Test {
	public static void main(String[] args) {
		A a = new D();
		if(a instanceof D d) {
			a.f1();
			d.f2();
		}
	}

}
