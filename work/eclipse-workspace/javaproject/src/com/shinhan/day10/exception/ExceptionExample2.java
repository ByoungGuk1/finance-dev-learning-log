package com.shinhan.day10.exception;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 5. 28. 오후 3:29:36 설명 : ExceptionExample2
 */
class ScoreException extends RuntimeException {
	String field;

	ScoreException(String message) {
		super(message);
		this.field = message + "!";
	}

	void f1() {
		System.out.println(field);
	}
}

public class ExceptionExample2 {
	public static void main(String[] args) {
		System.out.println("==main start==");
		f1();
		f2();
		f2_2();
		f3();
		f4(100);
		f5(100);
		System.out.println("==main end==");
	}

	private static void f1() {
//		1. 자동 발생, runtimeException
		int num1 = 10;
		int num2 = 0;
		String num3 = "four";
		int[] arr = { 1, 2, 3, 4, 5 };

		try {
			System.out.println(num1 / num2);
			System.out.println(num1 / Integer.parseInt(num3));
			System.out.println(arr[5]);
			Object obj = new String("자바");
			Integer obj2 = (Integer) obj;
			System.out.println(obj2);
			Class.forName("aa.bb.cc.D");
		} catch (ArithmeticException | NumberFormatException e) { // exception 동시 처리
			System.err.println("1e: " + e.getMessage());
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("2e: " + e.getMessage());
		} catch (ClassCastException e) {
			System.err.println("3e: " + e.getMessage());
		} catch (RuntimeException e) {
			System.err.println("4e: " + e.getMessage() + "::RuntimeE");
		} catch (Exception e) {
			System.err.println("5e: " + e.getMessage() + "::E");
		} finally {
			System.out.println("~~finally~~");
		}
		System.out.println("==f1() end==");
	}

	private static void f2() {
		FileReader fr = null;
		try {
//			해당 파일 열기 -> 루트 경로 해당 프로젝트
			fr = new FileReader("src/com/shinhan/day10/ButtonsExample.java");
			int data;
			while ((data = fr.read()) != -1) {
				System.out.print((char) data);
			}
			System.out.println();
		} catch (FileNotFoundException e) {
			System.err.println("FileNotFoundException: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		} finally {
			try {
				fr.close();
			} catch (IOException | NullPointerException e) {
				System.err.println("close 오류: " + e.getMessage());
			}
		}
	}

	private static void f2_2() {
		try (FileReader fr = new FileReader("src/com/shinhan/day10/ButtonsExample.java")) {
			int data;
			while ((data = fr.read()) != -1) {
				System.out.print((char) data);
			}
			System.out.println();
		} catch (FileNotFoundException e) {
			System.err.println("FileNotFoundException: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		}
	}

	private static void f3() {
		try (FileWriter fw = new FileWriter("aa.txt"); FileWriter fw2 = new FileWriter("aa.txt");) {
			fw.write("---");
			fw2.write("~~~");
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		}
	}

	private static void f4(int score) {
		if (score >= 90) {

		} else {

		}
	}

	private static void f5(int score) {
		try {
//		예외 발생 가능성 있는 비즈니스 로직
			if (score <= 90)
				throw new ScoreException("90보다 크게하기");
		} catch (ScoreException e) {
			System.err.println("ScoreException: " + e.getMessage());
			System.out.println(e.field);
			e.f1();
		}
	}
}
