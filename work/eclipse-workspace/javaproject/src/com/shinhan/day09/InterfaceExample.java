package com.shinhan.day09;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 27. 오전 11:01:09
 * 설명			: InterfaceExample
 */
//	추상클래스
abstract class A{
	int a;
	A(){
//		생성자가 있어도 직접 생성 불가능
//		자식 클래스에서 호출할 목적으로 생성
		a = 100;
	}
	void f1() {
		
	}
	abstract void f2();
}
//	추상 클래스를 상속받은 자식 클래스
class A2 extends A{
	A2(){
		super();
	}
	@Override
	void f2() {
		
	}
}
//	인터페이스를 구현한다 -> 추상을 반드시 구현
//	구현 class는 interface의 기능이 가능 -> 이름이 ( ...able )
interface B{
	/*public final static */int MAX_VALUE = 100;
	/*public abstract */void f1();
	public default void f2() {	f4();	}
	public static void f3() {	f5();	}
	private void f4() {}
	private static void f5() {}
}

//class C extends Object implements B, Serializable, Cloneable, Closeable{
//	@Override
//	public void close() throws IOException {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void f1() {
//		// TODO Auto-generated method stub
//		
//	}
//}

public class InterfaceExample {

}
