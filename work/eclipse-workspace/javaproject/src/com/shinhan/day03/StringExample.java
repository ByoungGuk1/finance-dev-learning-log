package com.shinhan.day03;

import java.util.Arrays;

public class StringExample {

	public static void main(String[] args) {
		call1();
		call2();
		call3();
		call4();
		call5();
		call6();
		call7();
		call8();	//	stream
		call9();
		call10();	//	2차원 배열
		call11();

	}

	private static void call11() {
		String[] arr1 = {"자바", "오라클", "스프링"};
		int[][] arr2 = {{10,20,30},{20,40},{1,2,3,4,5}};
		
//		`주소`의 복사
		String[] arr3 = arr1;
		arr1[0] = "JAVA는 객체지향 언어이다.";
		System.out.println(Arrays.toString(arr1));
		System.out.println(Arrays.toString(arr3));
		
		int[][] arr4 = arr2;
		arr2[0][0] = 99;
		for(int[] arr : arr2) {
			System.out.println(Arrays.toString(arr));
		}
		for(int[] arr : arr4) {
			System.out.println(Arrays.toString(arr));
		}
		
//		`값`의 복사	//	사용 이유 -> 크기를 확장 시키기 위해
		String[] arr5 = new String[arr1.length + 2];
		System.arraycopy(arr1, 0, arr5, 0, arr1.length);
		arr1[1] = "오라클XE 버전은 HR계정을 제공한다.";
		System.out.println("원본-\t\t" + Arrays.toString(arr1));
		System.out.println("주소 복제본-\t" + Arrays.toString(arr3));
		System.out.println("사본-\t\t" + Arrays.toString(arr5));
		
		int[][] arr6 = new int[arr2.length][arr2[0].length + 2];
		for(int[] arr : arr6) {
			System.out.println(Arrays.toString(arr));
		}
		System.arraycopy(arr2[0], 0, arr6[0], 0, arr2[0].length);
		for(int[] arr : arr6) {
			System.out.println(Arrays.toString(arr));
		}
	}

	private static void call10() {
//		2차원 배열, 배열의 배열 / 배열의 행의 개수는 생성시 필수, 열의 개수는 필수가 아니다
		int[][] intArr;	// 가장 선호
//		int[] arr2[];
//		int arr3[][];

		intArr = new int[3][4];	//	{{0,0,0,0},{0,0,0,0},{0,0,0,0,}}
		for(int[] arr : intArr) {
			System.out.println(Arrays.toString(arr));
		}

		intArr = new int[3][];	//	참조 배열의 크기는 바로 정하지 않아도 괜찮다 / {null, null, null}
		for(int[] arr : intArr) {
			System.out.println(Arrays.toString(arr));
		}
		
		intArr = new int[][] {{10,20,30},{20,40},{1,2,3,4,5}};
		for(int[] arr : intArr) {
			System.out.println(Arrays.toString(arr));
		}
		System.out.println(intArr[1][1]);
	}

	private static void call9() {
		int score = 100;
		System.out.println("before : " + score);
		method2(score);	//	값의 전달 (복사)
		System.out.println("after : " + score);
	}

	private static void method2(int score) {
		score = 200;
	}

	private static void call8() {
		String[] strArr = new String[] {"자바","오라클","스프링"};
		System.out.println("before : " + Arrays.toString(strArr));
		method(strArr);	//	주소의 전달
//		System.out.println("after : " + Arrays.toString(strArr));
		
//		---외부 반복자---
		System.out.println("외부 반복자 : 일반 for 문");
		for (int i = 0; i < strArr.length; i++) {
			System.out.print(strArr[i]);
			if(i < strArr.length -1) {
				System.out.print(", ");
			}
		}
		System.out.println();

		System.out.println("외부 반복자 : 향상된 for 문");
		for (String data : strArr) {
			System.out.print(data);
			if(data != strArr[strArr.length - 1]) {
				System.out.print(", ");
			}
		}
		System.out.println();
		
//		---내부 반복자 (stream)---
		System.out.println("내부 반복자 (stream)");
		Arrays.stream(strArr).forEach(System.out::println);
		Arrays.stream(strArr).forEach(data->System.out.println(data));
	}

	private static void method(String[] strArr) {
		strArr[0] = "신한DS 금융소프트웨어 아카데미";
	}

	private static void call7() {
		String[] stringArr1 = new String[4];	// 자동 초기화
		System.out.println(Arrays.toString(stringArr1));
		
		String[] stringArr2 = new String[] {"자바","오라클","스프링"};
		System.out.println(Arrays.toString(stringArr2));
		stringArr2[0] = "JAVA";
		System.out.println(Arrays.toString(stringArr2));
	}

	private static void call6() {
//		선언 + 생성
		char[] chArr = new char[8];
		System.out.println(Arrays.toString(chArr));
		
//		선언 + 생성 + 할당	//	갯수는 한 번만 명시 -> 할당하면서 크기가 정해지므로 []안에 값 주지 않기
		boolean[] boolArr = new boolean[] {true, false, true, true, true, false, true, true};
		System.out.println(Arrays.toString(boolArr));
		
//		선언 + 생성 + 할당
		float[] floatArr1 = new float[] {3.14f, 12.345f, 55.123f};
		System.out.println(Arrays.toString(floatArr1));

//		선언 + 생성 + 할당
		float[] floatArr2 = {3.14f, 12.345f, 55.123f};	//	new 생략 가능
		System.out.println(Arrays.toString(floatArr2));
		
//		선언 + 생성 -> 할당
		float[] floatArr3;
		floatArr3= new float[] {3.14f, 12.345f, 55.123f};	//	선언 후 값을 한번에 할당하는 경우
		System.out.println(Arrays.toString(floatArr3));
	}

	private static void call5() {
//		배열 : 하나의 이름으로 같은 타입의 값을 연속 공간에 저장하는 자료구조
		/*
		 * 스택 영역에 만들어져서 참조
		 * */
//		1. 배열 객체 참조 변수 선언
//		2. 배열 생성
//		3. 배열 사용
		
//		1. 배열 객체 참조 변수 선언
		int[] arr;	//	자바의 경우 앞에 쓰는걸 조금 더 선호
//		int arr2[] = null;
		
//		2. 배열 생성	//	배열의 개수가 필수, 자동으로 값이 초기화.
//		정수는 0, char = ' ', 실수는 0.0, boolean = false, 참조형은 null
		arr = new int[8];
		
//		3. 배열 사용
		arr[1] = 99;
		System.out.println(arr[0]);
		System.out.println(Arrays.toString(arr));
		
//		System.out.println(arr2[0]);	//	초기화 하지 않으면 사용 불가 && 생성되지 않은 배열은 사용 불가
		
	}

	private static void call4() {
		String s1 = "이것이 자바다.";
		System.out.println(s1.indexOf("자바"));
		System.out.println(s1.substring(4, 6));	//	4번 인덱스부터 6번 인덱스까지
		System.out.println(s1.substring(4));	//	4번 인덱스부터 끝까지
		
		String[] stArr = s1.split(" ");
		System.out.println(Arrays.toString(stArr));
		
		String s2 = "커피,짜장면-김밥 비빔밥";
		stArr = s2.split(",|-| ");	//	정규표현식 (|는 OR를 의미)
		System.out.println(Arrays.toString(stArr));
	}

	private static void call3() {	//	StringBuffer 이용
		StringBuffer s1 = new StringBuffer("이것이 자바다.");
//		StringBuilder s1 = new StringBuilder("이것이 자바다.");		//	싱글 스레드라 동일한 기능?
		long startTime = 0L;
		long endTime = 0L;
		
		startTime = System.nanoTime();
		for (int i = 0; i < 1000; i++) {
			s1 = s1.append(i);
		}
		endTime = System.nanoTime();
		
		System.out.println(s1);
		System.out.println("동작 시간(StringBuffer): " + (endTime - startTime) + "ns");
		
		/*
		 * StringBuffer가 더 빠른 이유
		 * 
		 * String -> StringBuffer -> append() -> `toString()` -> 참조값 변경
		 * 
		 * 메모리 측면에서 StringBuffer가 조금 더 좋다.
		 * */
	}

	private static void call2() {	//	String 이용
		String s1 = "이것이 자바다.";
		long startTime = 0L;
		long endTime = 0L;
		
		startTime = System.nanoTime();
		for (int i = 0; i < 1000; i++) {
			s1 = s1 + i;
		}
		endTime = System.nanoTime();
		
		System.out.println(s1);
		System.out.println("동작 시간(String): " + (endTime - startTime) + "ns");
	}

	private static void call1() {
//		String : 문자열 / char : 단일 문자
//		String 의 값은 불변
		String s1 = "이것이 자바다.";
		char ch = s1.charAt(6);
		
		System.out.println(ch);
		System.out.println(s1.indexOf('다'));
		System.out.println(s1.indexOf("자바"));
		System.out.println(s1.length());
		System.out.println(s1.replace("자바", "JAVA"));
		System.out.println(s1);
	}

}
