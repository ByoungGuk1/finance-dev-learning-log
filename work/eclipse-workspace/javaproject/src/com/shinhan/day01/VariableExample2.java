package com.shinhan.day01;

public class VariableExample2 {

	public static void main(String[] args) {
		f1();
		f2();
		f3();
	}

	private static void f1() {
//		메서드 안에 선언된 변수는 지역 변수.
//		지역 변수는 초기화 하지 않으면 사용 불가

//		형변환
//		1. 자동 형 변환 : 작은 타입의 값을 큰 타입으로 전환
//		2. 강제 형 변환 : 작은 타입 = (작은 타입) 큰 값
//			ㄴ> 값의 손실이 있을 수 있다.
		int v1;
		byte v2 = 127;
		short v3 = 32767;
		long v4 = 10000000000L;
		float v5 = 3.14f;
		double v6 = 0.1;
		char v7 = 'A';
		boolean v8 = true;
		
		v1 = v2;
		v1 = v3;
		v1 = (int)v4;	// 강제 형 변환은 수용 범위를 벗어나면 기대값이 아니게된다.
		v1 = (int)v5;	// 강제 형 변환으로 소수점 이하의 값은 손실된다.
		v1 = (int)v6;
		v1 = v7;
//		v1 = (int)v8;	// 불가능 : boolean의 경우는 다른 타입으로 변환 불가능
		System.out.println(v1);
		System.out.println(v8);
	}
	
	private static void f2() {
		int result;
		byte result2;	// Byte.MAX_VALUE = 127
		
		byte x = 10;
		byte y = 120;

		result = x + y;
//		연산식에서 사용되는 정수는 기본적으로 int
		result2 = (byte)(x + y);
		
		System.out.println(result);
		System.out.println(result2);
	}
	
	private static void f3() {
		// String -> 기본타입
		String s1 = "100";
		int v1 = Integer.parseInt(s1) + 100;
		long v2 = Long.parseLong(s1) + 200L;
		byte v3 = (byte)(Byte.parseByte(s1) + 1);
		short v4 = (short)(Short.parseShort(s1) + 1);
		float v5 = Float.parseFloat(s1) + 3.14f;
		double v6 = Double.parseDouble(s1) + 3.14;
		
		System.out.println(v1);
		System.out.println(v2);
		System.out.println(v3);
		System.out.println(v4);
		System.out.println(v5);
		System.out.println(v6);
	}

}
