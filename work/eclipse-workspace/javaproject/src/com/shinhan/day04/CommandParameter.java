package com.shinhan.day04;
/*
 * cmd > java CommandParameter 100 200
 * */

public class CommandParameter {
	public static void main(String[] args) {
//		매개변수가 들어오면 args = new String[]{ 초기값, ... }
		if(args.length < 2) {
			System.err.println("명령행 매개변수가 2개 이상 필요합니다.");
			return;
		}
		System.out.println(args[0]);
		System.out.println(args[1]);
//		들어온 값으로 사칙연산 수행하기
		try {
			int num1 = Integer.parseInt(args[0]);
			int num2 = Integer.parseInt(args[1]);

			System.out.println("덧셈 : " + (num1 + num2));
			System.out.println("뺄셈 : " + (num1 - num2));
			System.out.println("곱셈 : " + num1 * num2);
			System.out.println("몫 : " + num1 / num2);
			System.out.println("나머지 : " + num1 % num2);
		} catch (NumberFormatException e) {
			System.err.println("정수를 사용해주세요");
			System.err.println(e.getMessage());
		}
	}
}
