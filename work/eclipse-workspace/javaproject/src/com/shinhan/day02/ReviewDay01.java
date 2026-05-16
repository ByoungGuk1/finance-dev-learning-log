package com.shinhan.day02;

class AA{
//	퍼블릭이 아닌 클래스는 생성 가능
}

public class ReviewDay01 {
//	.java와 클래스의 이름을 동일하게
//	class = 변수들 + 함수들
	int v1 = 100;
	String v2 = "자바";
	
	public static void main(String[] args) {
		System.out.println("main에서 프로그램 시작");
		f1();
		f2();
		f3();
		f4();
		f5();
		f6();
		f7();
		System.out.println("main에서 프로그램 종료");
	}

	private static void f1() {
		System.out.println("f1() 시작");
		
//		기본형 datatype : byte(1) < short(2), char(2) < int (4) < long(8) < float(4) < double(8)
//		값의 저장, 읽기, 계산, 비교
//		Wrapper Class = 기본형 datatype + 추가 기능
//		Byte(1) < Short(2), Character(2) < Integer (4) < Long(8) < Float(4) < Double(8)
		int i = 10;
		String s = Integer.toBinaryString(i);	//	Integer.toString(i, 2)
		System.out.println(i);
		System.out.println(s);
		System.out.println(Integer.MAX_VALUE);

		System.out.println("f1() 종료");
	}

	private static void f2() {
		int v1;
		char v2 = 'A';
		
//		자동 형 변환 : 큰 방 <= 작은 값
		v1 = v2;
		System.out.println(v1);
		
//		강제 형 변환 <casting 필요> : 작은 방 <= (작은 방 타입)큰 값
//		데이터의 손실 가능성이 있다.
		v2 = (char)v1;
		System.out.println((int)Character.MAX_VALUE);
		System.out.println(v2);

	}
	
	private static void f3() {
		byte v1 = 10;
		byte v2 = 20;
//		연산식에서 사용되는 정수는 기본적으로 int
		int result1 = v1 + v2;
		byte result2 = (byte)(v1 + v2);

		int v3 = 30;
//		double result3 = v3 + 3.14;
		int result3 = (int)(v3 + 3.14);

		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);

	}

	private static void f4() {
//		기본형 => 문자
		int v1 = 100;
		String v2 = v1 + "";
		String v3 = String.valueOf(v1);	//	추천

		System.out.println(v1 + 200);
		System.out.println(v2 + 200);
		System.out.println(v3 + 200);
	}
	
	private static void f5() {
		String v1 = "자바";
		String v2 = "프로그램";
		String result = v1 + v2;
//		스트링 버퍼 생성
//		"자바"
//		oppand ("프로그램")
//		String tmp = "자바프로그램"
//		스택 영역으로 반환
//		스트링 버퍼 종료
		System.out.println(result);
		
	}

	private static void f6() {
		StringBuffer v1 = new StringBuffer("자바");
		StringBuffer v2 = new StringBuffer("프로그램");
		String result = v1.append(v2).toString();
		System.out.println(result);

	}

	private static void f7() {
		int v1 = 100;
		System.out.println(v1 + 200 == 300);
		
//		AutoBoxing
//		Integer v2 = new Integer(200);
		Integer v2 = 200;
//		Auto Unboxing
//		System.out.println(v2.intValue() + 200 == 400);
		System.out.println(v2 + 200 == 400);

		System.out.println(Integer.toHexString(v2));

	}

}
