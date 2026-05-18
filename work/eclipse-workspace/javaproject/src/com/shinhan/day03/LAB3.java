package com.shinhan.day03;

import java.util.Scanner;

public class LAB3 {

	public static void main(String[] args) {
		f_Q1();
		f_Q2();

	}

	private static void f_Q1() {
		/*
		 * 자신이 태어난 달을 명령행 매개변수로 받아서 어떤 계절에 태어났는지를 출력하는 printSeason() 메서드를 구현하시오. 
			(단, 반드시 switch 문을 사용해야 하며 1~12월이 아닌 달을 입력했을 경우 
				“1~12 사이의 숫자만 입력하셔야 합니다.” 라는 문자를 출력하도록 해야 한다.
				그리고 주어진 메서드의 시그니쳐는 변경하지 않는다.)
			(봄: 3, 4, 5월,       여름: 6, 7, 8월,       가을: 9, 10, 11월,        겨울 12, 1, 2월)
		 * */
		printSeason();
	}

	private static void printSeason() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("태어난 달 입력 : ");
		int month = sc.nextInt();
		String s = null;
		String f_message = "%s에 태어나셨네요.";
		
		if(!(month >= 1 && month <= 12)) {
			System.err.println("1~12 사이의 숫자만 입력하셔야 합니다.");
			sc.close();
			return;
		}
		
		switch(month) {
		case 3,4,5->{s="봄";}
		case 6,7,8->{s="여름";}
		case 9,10,11->{s="가을";}
		case 12,1,2->{s="겨울";}
		}
		
		System.out.printf(f_message, s);
		
		sc.close();
	}

	private static void f_Q2() {
		/*
		 * 두 개의 숫자를 매개 변수로 받아서 두 숫자 사이의 차를 구하되, 실행 결과가 음수일 경우 양의 정수로 변경하여 리턴하는 abs() 메서드를 구현하시오.
			(단, 주어진 메서드의 시그니쳐는 변경하지 않는다.)
		 */
		int num1 = -15;
		int num2 = -10;
		System.out.println(abs(num1, num2));
	}

	private static int abs(int num1, int num2) {
		int tmp = 0;
		int result = num1 - num2;
		
		if(result < 0) {
			tmp = result * 2;
			result -= tmp;
		}

		return result;
	}
	
}
