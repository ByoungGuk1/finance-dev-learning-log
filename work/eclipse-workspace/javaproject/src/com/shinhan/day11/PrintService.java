package com.shinhan.day11;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 5. 29. 오후 3:19:36 설명 : PrintService
 */
public class PrintService {
	@PrintAnnotation("*")
	public void method1() {
		System.out.println("method1() 실행");
	}

	@PrintAnnotation(value = "#", number = 20)
	public void method2() {
		System.out.println("method2() 실행");
	}

	@PrintAnnotation(number = 30)
	public void method3() {
		System.out.println("method3() 실행");
	}
}
