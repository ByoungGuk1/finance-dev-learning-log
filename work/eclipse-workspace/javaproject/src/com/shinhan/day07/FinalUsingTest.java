package com.shinhan.day07;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 22. 오후 2:08:15
 * 설명			: FinalUsingTest
 */

//	final을 사용할 수 있는 위치 : class, method, field
//		class : 상속불가
//		method : 오버라이딩 불가
//		field : 수정 불가

// final class는 상속 불가
final class MyClass {}
//class MyClassChild extends MyClass {}

class MyClass2{
	final int score = 99;
	void f1() {}
	final void f2() {}
}
class MyClass2Child extends MyClass2 {
	void f1() {	//	오버라이딩-재정의 가능
//		score = 88;	//	final field는 변경 불가능
	}
//	void f2() {}	//	오버라이딩-재정의 불가능
}

public class FinalUsingTest {;}
