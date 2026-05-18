package com.shinhan.day03;

import java.util.Scanner;

public class LAB2 {

	public static void main(String[] args) {
		method1();
		method2();
		method3();
		method4();
		method5();	//	문제 다시 이해해보기
		method6();	//	단축 평가에 대해서 더 고민해보기
		method7();	//	{numDay() 메서드} switch 문 사용 방법에 대해서 고민해보기 -> break 를 쓰지 않고 화살표 함수를 통해서 작성
		method8();

	}

	private static void method1() {
//		1보다 크고 10보다 작은 정수를 입력 받아서 아래와 같은 실행 결과가 나타나도록 
//		Prob1 클래스의 main 메소드를 완성하십시오. 입력 받은 숫자에 해당하는 구구단을 완성시키는 프로그램입니다. 
//		2보다 작거나 9보다 큰 정수가 입력될 경우에는 “잘못된 숫자가 입력되었습니다.”라는 경고 메시지를 출력하고
//		프로그램을 종료합니다.

		Scanner sc = new Scanner(System.in);
		System.out.print("1보다 크고 10보다 작은 정수를 입력 : ");
		
		int inputNum = sc.nextInt();
		
		if(inputNum < 2 || inputNum > 9) {
			System.out.println("잘못된 숫자가 입력되었습니다.");
			sc.close();
			return;
		}
		
		for (int i = 1; i < 10; i++) {
			System.out.println(inputNum + " * " + i + " = " + inputNum*i);
		}
		
		sc.close();
	}

	private static void method2() {
		System.out.print("2~100 사이의 숫자를 입력 : ");
		Scanner sc = new Scanner(System.in);
		
		int inputNumber = sc.nextInt();
		boolean isTrue = true;
		
		if(inputNumber < 2 || inputNumber > 100) {
			System.out.println("입력 값을 다시 확인해주세요");
			sc.close();
			return;
		}
		
		for (int i = 2; i < inputNumber / 2; i++) {
			isTrue = inputNumber % i == 0 ? false : true;
			if(isTrue == false) {
				break;
			}
		}

		if(isTrue) {
			System.out.println("소수입니다");
		} else {
			System.out.println("소수가 아닙니다.");
		}
		
		sc.close();
	}
	
	private static void method3() {
		Scanner sc = new Scanner(System.in);
		System.out.println("2 ~ 9 사이의 정수를 입력해주세요");
		
		int result = 1;
		int inputNumber = sc.nextInt();
		
		if(inputNumber < 2 || inputNumber > 9) {
			System.out.println("잘못된 숫자가 입력되었습니다.");
			sc.close();
			return;
		}
		
		for (int i = 1; i <= inputNumber; i++) {
			result *= i;
			System.out.println(i + "! = " + result);
		}
		
		sc.close();
		
	}

	private static void method4() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("임의의 정수를 입력해주세요");

		int sum = 0;
		int count = 0;
		int MAX_VALUE = 1000;
		int inputNumber = sc.nextInt();
		
		for (int i = inputNumber; i <= MAX_VALUE; i += inputNumber) {
			count++;
			sum += i;
		}
		
		System.out.println("입력된 값 : " + inputNumber);
		System.out.println("입력된 정수의 배수 개수 : " + count);
		System.out.println("배수들의 합 : " + sum);
		
		sc.close();
	}

	private static void method5() {	//check
//		아래의 규칙으로 증가하는 수열에서 15번째까지의 합을 계산하십시오

		int endNumber = 15;
		int curNum = 1;
		int sum = 0;
		int diff = 0;
		
		for (int i = 0; i < endNumber; i++) {
			curNum += diff++;
			sum += curNum;
			System.out.print(curNum);
			if(i < endNumber - 1) {
				System.out.print(" + ");
			}
		}
		System.out.println(" = " + sum);
	}

	private static void method6() {
//		주어진 년도의 월에 해당하는 말일을 계산하는 프로그램을 완성하시오. 윤년인 경우에는 다음과 같은 조건에 의해 처리합니다.
//
//		[윤년의 조건]
//		1)	4의 배수인 해는 윤년.
//		2)	4의 배수이면서 100의 배수인 해는 윤년이 아님.
//		3)	100의 배수이면서 400의 배수인 해는 윤년.
		
		Scanner sc = new Scanner(System.in);
		System.out.println("년도를 입력해주세요");
		int inputNumber = sc.nextInt();
		int lastDay = 28;
		
		lastDay = inputNumber % 400 == 0 || (inputNumber % 4 == 0 && inputNumber % 100 != 0) ? 29 : 28;
		
		System.out.printf("%d년도의 2월 말일은 %d입니다.", inputNumber, lastDay);
		
		sc.close();
		
	}

	private static void method7() {
		int year = 2000;
		int month = 12;
		int lastDay = numDay(year, month);
		System.out.printf("%d년도의 %d의 말일은 %d입니다.\n", year, month, lastDay);
	}
	
	private static int numDay(int year, int month) {
		int returnValue = 0;
		
		switch (month) {
		case 1,3,5,7,8,10,12->{returnValue = 31;}
		case 4,6,9,11->{returnValue = 30;}
		case 2->{returnValue = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0 ? 29 : 28;}
		default->{System.err.println("존재하지 않는 월입니다.");}
		}
		
		return returnValue;
	}
	
	private static void method8() {
		int num1 = 250;
		int num2 = 30;
		int result = gcd(num1, num2);
		String f_message = "%d와 %d의 최대공약수는 %d입니다.";
		System.out.printf(f_message,num1,num2,result);
	}

	private static int gcd(int num1, int num2) {
		int result = -1;
		int largeNumber = 0; 
		int number = 0;
		
		largeNumber = num1 > num2 ? num1 : num2; 
		number = num1 > num2 ? num2 : num1;
		
		for(int i = number; i > 0; i--) {
			if(largeNumber % i == 0 && number % i == 0) {
				result = i;
				break;
			}
		}
		
		return result;
	}
}
