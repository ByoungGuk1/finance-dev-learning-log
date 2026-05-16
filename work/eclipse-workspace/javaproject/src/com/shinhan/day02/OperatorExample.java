package com.shinhan.day02;

import java.io.IOException;
import java.util.Scanner;

public class OperatorExample {

	public static void main(String[] args) {
		method1();
		method2();
		method4();
		method5();
		method6();
		method7();

	}

	private static void method1() {
		Scanner sc = new Scanner(System.in);

		System.out.print("나눌 수를 입력해주세요 >>> ");
		int su1 = sc.nextInt();
		System.out.print("나누는 수를 입력해주세요 >>> ");
		int su2 = sc.nextInt();

		try {
			System.out.println("몫 : " + su1 / su2);
			System.out.println("나머지 : " + su1 % su2);
//			System.out.println(10 / 0);	//	실행 시 오류 -> 오류 처리를 하면 프로그램이 중단되지 않고 계속 진행한다.
		} catch (ArithmeticException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}

		try {
			System.out.print("사용자 입력 >> ");
			int data = System.in.read();	//	컴파일 시 오류
			System.out.println(data);
		} catch (IOException e) {
			e.printStackTrace();
		}

		sc.close();
		System.out.println("== 함수 종료 ==");

	}

	private static void method2() {
		double data = 10;
		double num = method3();

		if (Double.isInfinite(data / num)) {
			System.out.println("무한대입니다. 값을 다시 한번 확인해 주세요");
		}else {
			System.out.println(data / num);	//	-> Infinity			
		}

		if (Double.isNaN(data % num)) {
			System.out.println("NaN 오류");
		}else {
			System.out.println(data % num);	//	-> NaN (Not a Number)			
		}

		String s1 = "가나다";
		String s2 = "가나다";
		System.out.println(s1 == s2);	//	값 비교가 아닌 주소값을 비교

		s1 += "라";
		System.out.println(s1 == "가나다라");
		
		System.out.println(s1.equals("가나다라"));	//	문자열의 값 비교

	}
	
	private static double method3() {
		return 0.0;
	}

	private static void method4() {
		int a = 10;
		a++;
		++a;
		System.out.println(++a);	//	기댓값 : 13
		System.out.println(a++);	//	기댓값 : 13
		System.out.println(a);	//	기댓값 : 14

//		&& 모두 참이라면 true -> '단축구문'
		a = 10;
		System.out.println(a > 15 && a > 10);	//	기댓값 : false
		System.out.println(a > 20 && ++a > 10);	//	&&의 경우 : 앞의 결과가 거짓이면 뒤의 문장을 실행하지 않음
		System.out.println("a의 결과 : " + a);

		System.out.println(a < 20 && ++a > 10);	//	&&의 경우 : 앞의 결과가 참이면 뒤의 문장을 실행
		System.out.println("a의 결과 : " + a);

		System.out.println(a > 20 & ++a > 10);	// &의 경우 : 앞의 결과가 거짓이어도 뒤의 문장을 실행
		System.out.println("a의 결과 : " + a);
		
//		정리
//		&, && 는 모두 참이면 true
//		&는 무조건 모두 수행
//		&&는 단축구문. 앞의 결과가 거짓이라면 뒤의 문장을 수행하지 않음.

//		|, || 는 하나라도 참이면 true
//		|는 무조건 모두 수행
//		||는 단축구문. 앞의 결과가 참이라면 뒤의 문장을 수행하지 않음.
		a = 10;
		System.out.println(a < 20 || ++a > 10);
		System.out.println("a의 결과 : " + a);

		System.out.println(a > 20 | ++a > 10);
		System.out.println("a의 결과 : " + a);

	}
	
	private static void method5() {
//		read, write, execute
		int read = 0b100;	// 바이너리 값(이진수)
		int write = 0b010;
//		int exec = 0b001;

		System.out.println(Integer.toBinaryString(read | write));
		System.out.println(Integer.toBinaryString(read & write));

	}
	
	private static void method6() {
		int a = 10;
		a++;
		++a;
		a += 1;
		System.out.println(a);

	}
	private static void method7() {
		int score = 99;
		String message;
		if (score >= 90) {
			message = "합격";
		}else {
			message = "불합격";
		}
		System.out.println(message);
		
		message = score >= 90 ? "합격" : "불합격";
		System.out.println(message);

	}

}
