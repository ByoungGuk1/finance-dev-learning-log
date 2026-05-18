package com.shinhan.day03;

import java.util.Scanner;

// 자바가 제공하는 class들은 패키지로 묶임
// 패키지는 모듈로 묶임
// Library > module > package > class
// java.base모듈은 추가 설정 없이 그냥 사용 가능
// ex) java.base.java.lang 패키지는 import 없이 사용 가능

public class LAB1 {

	public static void main(String[] args) {
		f_Q1();
		f_Q2();
		f_Q3();
		f_Q4();
		f_Q5();

	}

	private static void f_Q1() {
//		표준입력(키보드)
//		간단하게 입력을 읽기 위해서
		Scanner sc = new Scanner(System.in);

		System.out.print("초기값 마지막값 증가분 입력 >> ");
//		next() 문자열 , nextInt() 숫자, nextLine() 문자열
		int start = sc.nextInt();
		int end = sc.nextInt();
		int step = sc.nextInt();

		int sum = 0;

		for (int i = start; i <= end; i += step) {
			sum += i;
		}

		if(sum > 1000) {
			sum += 2000;
		}

		System.out.println("sum = " + sum);

		sc.close();

	}

	private static void f_Q2() {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();

		System.out.println(System.identityHashCode(input));

		input = input.toUpperCase();

		System.out.println(System.identityHashCode(input));

		for (int i = 0; i < input.length(); i++) {
			System.out.println(input.substring(0, i + 1));
		}

		sc.close();
	}

	private static void f_Q3() {
		Scanner sc = new Scanner(System.in);

		System.out.print("마지막 값 입력 >> ");
		int n = sc.nextInt();

		int sum = 0;

		int start = n % 2 == 0 ? 2 : 1;

		for(int i = start; i <= n; i += 2) {
			sum += i;
			if( i == n ) {
				System.out.println(i);
				break;
			}
			System.out.print(i + " + ");
		}

		System.out.println("sum = " + sum);

		sc.close();

	}

	private static void f_Q4() {
		hokeyGraphics('$',4,false);
	}

	private static void hokeyGraphics(char ch, int i, boolean bool) {
//		isRect == true : size x size 크기의 정사각형 출력
//		isRect == false : 밑변이 size인 우상향 직각삼각형 출력

		boolean isRect = bool;
		int size = i;
		
		for(int row = 1; row <= size; row++) {
			int last = isRect ? size : row;
			for(int col = 1; col <= last; col++) {
				System.out.print(ch);
			}
			System.out.println();
		}

	}

	private static void f_Q5() {
		Scanner sc = new Scanner(System.in);

		System.out.println("직각 역삼각형을 출력할 줄 수 : ");
		int number = sc.nextInt();

		for (int i = 0; i < number; i++) {
			for (int j = number - i; j > 0; j--) {
				System.out.print("*");
			}
			System.out.println();
		}

		sc.close();
	}

}
