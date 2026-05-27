package com.shinhan.day09.lab6;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 27. 오후 4:46:18
 * 설명			: A
 */
public class A {
	public void method1() {
		int a_method1_field = 1;
		
		class B{
			void method2() {
				System.out.println(a_method1_field);
			}
		}
		
//		a_method1_field++;
		B b = new B();
		b.method2();
	}
	
//	내부 클래스의 로직에서 그걸 감싸는 메서드의 지역변수에 접근하는 순간 해당 지역 변수에 final 적용
	
	
	public static void main(String[] args) {
		A a = new A();
		a.method1();
	}
}
