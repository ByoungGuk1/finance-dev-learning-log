package com.shinhan.day12;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 1. 오후 2:36:31 설명 : GenericExample4
 */
public class GenericExample4 {
	public static void main(String[] args) {
		boolean compare_result1 = compare(10, 10);
		System.out.println(compare_result1);

		System.out.println();

		boolean compare_result2 = compare(2.3, 3.1);
		System.out.println(compare_result2);
	}

//	제네릭 타입 제한하기
//	Number를 포함한 상속을 받은 하위 클래스만 올 수 있다.
	private static <T extends Number> boolean compare(T i, T j) {
		boolean result = false;
		System.out.println(i.getClass().getSimpleName());
		result = i.equals(j);

		double d1 = i.doubleValue();
		double d2 = j.doubleValue();
		System.out.println(d1 + d2);

		int num1 = i.intValue();
		int num2 = j.intValue();
		System.out.println(num1 - num2);

		return result;
	}
}
