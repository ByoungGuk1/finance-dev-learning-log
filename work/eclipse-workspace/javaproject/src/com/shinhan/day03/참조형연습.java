package com.shinhan.day03;

//	자바는 main() 메서드를 실행
//	위에서 아래로 진행
//	프로그램의 흐름이 기본적으로 한개 -> 즉, SingleThread

//	>java 참조형 연습
//	JVM이 class를 메모리에 load -> static은 method 영역으로 들어감 / static 에서 non-static 사용 불가
//	JVM이 검증
//	main 시작

public class 참조형연습 {
	static String model;	//	자동 초기화
	
	public static void main(String[] args) {
		f_main1();
		f_main2();
		f_main3();
		f_main4();
	}
	
	private static void f_main4() {
		String s1 = "자바";
		String s2 = "자바";
		String s3 = new String("자바");
		String s4 = new String("자바");
		
		System.out.println(System.identityHashCode(s1));
		System.out.println(System.identityHashCode(s2));
		System.out.println(System.identityHashCode(s3));
		System.out.println(System.identityHashCode(s4));

//		자바의 String 은 변경 불가
		s1 = s1 + "프로그램";	//	힙에 새로운 주소로 생성됨 -> s1에 주소 할당
		s2 = s2 + "프로그램";
		
		System.out.println(System.identityHashCode(s1));
		System.out.println(System.identityHashCode(s2));
	}

	private static void f_main3() {
//		지역변수는 초기화 없이 사용 불가
		String s1 = returnString();	//	결정된 값이 없다.
		System.out.println(model);
		System.out.println(s1);
		try {
			System.out.println(s1.length());	//	s1이 null 인 경우 -> NullPointerException
		} catch (NullPointerException e) {
			System.err.println("참조형연습.f_main3() > NullPointerException 에러 발생 : " + e.getMessage());
		}
	}
	
	private static String returnString() {
		return null;
	}
	
	private static void f_main2() {
		String s1 = "자바";	//	리터럴은 컴파일 시점에 상수pool에 저장, load 될 때 method 영역에 저장
		String s2 = "자바";	//	이미 존재하면 같은 주소를 사용
		String s3 = new String("자바");	//	heap 영역에 생성
		String s4 = new String("자바");	//	heap 영역에 또 생성
		
//		주소 비교
		System.out.println("주소 비교 : " + (s1 == s2));
		System.out.println("주소 비교 : " + (s3 == s4));
		
//		System.out.println(s1.intern() == s2.intern());	//	확인해보기
//		System.out.println(s3.intern() == s4.intern());
		
		System.out.println(s1.equals(s2));	//	내용 비교
		System.out.println(s3.equals(s4));	//	내용 비교
	}

	private static void f_main1() {
		System.out.println(Thread.currentThread().getName() + " Thread 시작");
//		int a = 100;
//		int b = 200;
//		System.out.println(model);	//	아직 저장되지 않아서 사용 불가
		method1();
		System.out.println(Thread.currentThread().getName() + " Thread 끝");
	}

	private static void method1() {
//		기본형 dataType :	byte < short, char < int < long < float < double
//		boolean
	}

}
