package com.shinhan.day02;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class PrintExample {
//	필드의 경우 자동으로 초기화
//	정수 = 0, 실수 = 0.0, char는 공백, 객체는 null
	static int b;
	static String s;
	static Scanner sc2;
	static char ch;
	
	public static void main(String[] args) throws IOException {
		call1();
		call2();	//원시 형태의 입력 방식 (?)
		call3();
		call4();
		call5();
		call6();
		call7();
		call8();

	}
	
	private static void call1() {
		System.out.print(false);
		System.out.print('A');
		System.out.print("Hello");
		System.out.println("---------------");
		System.out.print("출력 후 줄바꿈\n");
		System.out.println("************");
		System.out.printf("format에 맞추어 출력\n정수 값 : %-5d\n실수 값 : %.2f\n문자 값 : %10s\n", 100, 3.141592,"자바");
		System.out.println("=============");

	}
	
	private static void call2() throws IOException {
		System.out.print("문자를 입력하세요>>>");

		InputStream is = System.in;
		int data = is.read();
		is.close();
		
		System.out.printf("입력 결과 : %s\n", (char)data);

	}

	private static void call3(){
		Scanner sc = new Scanner(System.in);
		System.out.print("이름을 입력하세요>>>");
		String name = sc.nextLine();
		System.out.print("나이를 입력하세요>>>");
		int age = Integer.parseInt(sc.nextLine());
		System.out.printf("반갑습니다. %s님\n나이는 %d\n", name, age);
		sc.close();

	}
	
	private static void call4(){
		Scanner sc = new Scanner(System.in);
		System.out.print("이름을 입력하세요>>>");
		String name = sc.nextLine();
		System.out.print("나이를 입력하세요>>>");
		int age = sc.nextInt();
		System.out.printf("반갑습니다. %s님\n나이는 %d\n", name, age);
		sc.close();

	}

	private static void call5(){
		Scanner sc = new Scanner(System.in);
		System.out.print("이름을 입력하세요>>>");
		String name = sc.next();
		sc.nextLine();
		System.out.print("나이를 입력하세요>>>");
		int age = sc.nextInt();
		System.out.printf("반갑습니다. %s님\n나이는 %d\n", name, age);
		sc.close();

	}

	private static void call6(){
		int a = 10;
		a++;
		System.out.println(a + b);
		System.out.println(s);
		System.out.println(sc2);
		System.out.println("*" + ch + "*");
	}

	private static void call7(){
		int _v1 = 1;
		int $v2 = 2;
		
		System.out.println(_v1);
		System.out.println($v2);
		
		int a = 10;
//		int = 타입, a = 변수명, 10 = 리터럴
		System.out.println(a);
	}
	
	private static void call8(){
		String s = "Hello";
		char result = s.charAt(0);
		System.out.println(result);

		char[] arr = s.toCharArray();
		System.out.println(Arrays.toString(arr));
		
		char ch = ' ';
		System.out.println(ch);
	}

}
