package com.shinhan.day01;

public class VariableExample {

	public static void main(String[] args) {
		method1();
		method2();
		method3();
		method4();
		method5();
		method6();
		method7();
	}

	private static void method1() {
//		자바의 기본 타입 = 목적 : data 저장, 연산, 값 비교에 사용
//		1. 정수 : byte (1 byte), short (2 byte), char (2 byte), int (4 byte), long (8 byte)
//		2. 실수 : float (4byte), double (8byte)
//		3. 논리 : boolean
		
//		정수 리터럴의 기본은 int
		byte v1 = Byte.MAX_VALUE;	//127
//		byte mv1 = -128;
		short v2 = Short.MAX_VALUE;	//32767
//		short mv2 = -32768;
		char v3 = 'a';	//65535;
		int v4 = Integer.MAX_VALUE;	//2147483647
//		int mv4 = -2147483648;
		long v5 = Long.MAX_VALUE;

//		overflow 발생, cycle된다.
		v1++;
		v2++;
		v3++;
		v4++;
		v5++;
		
		System.out.println(v1);
		System.out.println(v2);
		System.out.println(v3);
		System.out.println(v4);
		System.out.println(v5);
	}

	private static void method2() {
//		Wrapper class => 자바의 기본형 + 기능 추가
		
//		기본형 주 사용법
		int a = 100;
		a++;
		System.out.println(a);
		if (a >= 100) {
			System.out.println("100 이상");
		}else {
			System.out.println("100 미만");
		}
		
//		Wrapper class
		/*
		 * byte		->	Byte
		 * short	->	Short
		 * char		->	Character
		 * int		->	Integer
		 * long		->	Long
		 * float	->	Float
		 * double	->	Double
		 * boolean	->	Boolean
		 * */
		
//		Integer a2 = new Integer(200);
		Integer a2 = 200;	//AutoBoxing
		System.out.println(a2.floatValue());
	}
	
	private static void method3() {
//		실수 : float (4byte), double (8byte)
//		실수 리터럴의 기본은 double
		float v1 = 3.14f;
		double v2 = 3.14;
		System.out.println(v1);
		System.out.println(v2);
		System.out.println(0.1 + 0.2);
		System.out.println(0.1 + 0.2 == 0.3); //false
	}

	private static void method4() {
//		논리 : boolean (1byte) -> true, false
		boolean isTrue =  true;
		System.out.println(isTrue);
		isTrue = 10 > 20;
		System.out.println(isTrue);
	}
	
	private static void method5() {
		char v1 = 65;
		char v2 = 0x0041; // 4 * 16 + 1
		
		v1++;
		v2+=2;
		
		System.out.println(v1);
		System.out.println(v2);
	}
	
	private static void method6() {
//		String : 문자열을 사용하기 위한 Java의 Class
//		위치 : JRE System Library > java.base > java.lang > String.class
		String s1 = "자바";
		String s2 = "자바";
		String s3 = new String("자바");	//실행 시점에 생성
		String s4 = new String("자바");
		
		int a = 100, b = 100;
		
		System.out.println(a == b);		//기본형은 값을 비교
		System.out.println(s1 == s2);	//참조형은 주소를 비교
		System.out.println(s3 == s4);
		
		System.out.println(System.identityHashCode(s1));	//객체의 해시값 요청
		System.out.println(System.identityHashCode(s2));
		System.out.println(System.identityHashCode(s3));
		System.out.println(System.identityHashCode(s4));
		
		System.out.println(s1.equals(s4));	//내용 비교
		System.out.println(s3.equals(s4));
	}
	
	private static void method7() {
//		제어 문자
		String subject1 = "이것이 자바다. 저자는 \"신용권\" 선생님입니다.";
		System.out.println(subject1);
		String subject2 = """
				이것이 자바다. 저자는 "신용권" 선생님입니다.
				{ "과목":"자바", "가격":45000, "저자":"신용권"}
				""";
		System.out.println(subject2);
	}
	
}
