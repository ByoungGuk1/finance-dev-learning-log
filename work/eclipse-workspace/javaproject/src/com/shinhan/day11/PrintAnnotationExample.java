package com.shinhan.day11;

import java.lang.reflect.Method;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 5. 29. 오후 3:24:46 설명 : PrintAnnotationExample
 */
public class PrintAnnotationExample {
	public static void main(String[] args) throws Exception {
		Method[] declaredMethods = PrintService.class.getDeclaredMethods();
		for (Method method : declaredMethods) {
			// PrintAnnotation 얻기
			PrintAnnotation printAnnotation = method.getAnnotation(PrintAnnotation.class);
			System.out.println(printAnnotation.number());
			System.out.println(printAnnotation.value());
			// 설정 정보를 이용해서 선 출력
			printLine(printAnnotation);
			// 메소드 호출
			method.invoke(new PrintService());
			// 설정 정보를 이용해서 선 출력
			printLine(printAnnotation);
		}
	}

	public static void printLine(PrintAnnotation printAnnotation) {
		if (printAnnotation == null) {
			return;
		}
		// number 속성값 얻기
		int number = printAnnotation.number();
		for (int i = 0; i < number; i++) {
			// value 속성값 얻기
			String value = printAnnotation.value();
			System.out.print(value);
		}
		System.out.println();
	}
}
