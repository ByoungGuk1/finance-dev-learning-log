package com.shinhan.day10.exception;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 5. 28. 오후 3:11:20 설명 : ExceptionExample
 */
//	Exception:
//		프로그래머의 노력으로 복구가 가능한 오류
//		오류가 발생해도 계속 진행이 가능
//	Error:
//		프로그램이 종료
/*
*	예외가 발생하는 경우
*		1. 자동
*		2. 강제(throw)
*	예외를 처리하는 방법
*		1. try-catch-finally
*		2. 나를 호출한 곳으로 떠넘기기 (throws)
 */
public class ExceptionExample {
	public static void main(String[] args) {
//		1. 자동으로 예외가 발생하는 경우
//			1-1. 컴파일 시 발생하는 경우
//			1-2. 실행 시 발생하는 경우
		System.out.println("==main 시작==");
		f1();
//		처리 : 2-1. try-catch
		try {
			f2();
			System.out.println("1");
		} catch (ClassNotFoundException e) {
			System.err.println("2");
			e.printStackTrace();
		} finally {
			System.out.println("3");
		}
		System.out.println("==main 종료==");
	}

	private static void f1() {
//		1-2. 실행 시 발생하는 경우
		int total = 300;
		int count = 0;
		System.out.println("평균: " + total / count);	//	ArithmeticException
	}
	private static void f2() throws ClassNotFoundException {
		System.out.println("==f2() start==");
//		원인 : 1-1. 컴파일 시 발생하는 경우
//		처리 : 2-2. 나를 호출한 곳으로 떠넘기기
		Class.forName("com.shinhan.day10.Cup");	//	클래스 없으면 오류 나서 컴파일러가 먼저 확인 요청
		System.out.println("==f2() end==");
	}
}
