package com.shinhan.day11;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 5. 29. 오전 9:10:51 설명 : ExceptionExample
 */
class CustomException extends Exception {
	public CustomException(String message) {
		super(message);
	}
}

class CustomRuntimeException extends RuntimeException {
	public CustomRuntimeException(String message) {
		super(message);
	}
}

public class ExceptionExample {
	public static void main(String[] args) throws Exception {
		f1(10, 1);
		f2(100, 0);
		f3(30, 0);
		f4();
		f5();
		System.out.println("최종 종료");
	}

	private static void f5() {
		class NotExistIDException extends Exception {
			public NotExistIDException() {
			}

			public NotExistIDException(String message) {
				super(message);
			}
		}
		class WrongPasswordException extends Exception {
			public WrongPasswordException() {
			}

			public WrongPasswordException(String message) {
				super(message);
			}
		}
		class LoginExample {
			public void main(String[] args) {
				try {
					login("white", "12345");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

				try {
					login("blue", "54321");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}

			public void login(String id, String password) throws NotExistIDException, WrongPasswordException {
				// id가 blue가 아니라면 NotExistIDException을 발생시킴
				if (!id.equals("blue")) {
					throw new NotExistIDException("아이디가 존재하지 않습니다.");
				}
				// password가 12345가 아니라면 WrongPasswordException을 발생시킴
				if (!password.equals("12345")) {
					throw new WrongPasswordException("패스워드가 틀립니다.");
				}
			}
		}
		new LoginExample().main(null);
	}

	private static void f4() {
		String[] strArray = { "10", "2a" };
		int value = 0;
		for (int i = 0; i <= 2; i++) {
			try {
				value = Integer.parseInt(strArray[i]);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("인덱스를 초과했음");
			} catch (NumberFormatException e) {
				System.out.println("숫자로 변환할 수 없음");
			} finally {
				System.out.println(value);
			}
		}
	}

	private static void f3(int a, int b) throws CustomException {
//		1. non-checked Exception : 컴파일러가 확인하지 않음
//		2. checked Exception : 컴파일러가 확인
//		Exception > RuntimeException   IllegalArgumentException
		if (b <= 10) {
			throw new CustomRuntimeException("b의 매개변수 확인 필요");
		}
		if (a <= 20) {
			throw new CustomException("a의 매개변수 확인 필요");
		}
		System.out.println(a + b);
	}

	private static void f2(int a, int b) throws ClassNotFoundException, FileNotFoundException {
//	RuntimeException은 자동으로 throws가 된다.
//	자동으로 메서드에 throws 로 exception을 던진다
		int result = a / b;
		System.out.println(result);
		String s = null;
		System.out.println(s.length());

		Class.forName("com.shinhan.day10.Button");
		FileReader fr = new FileReader("aa.txt");
	}

	private static void f1(int a, int b) {
//		비즈니스 로직과 오류 처리 로직이 분리되는 것이 좋다.
		String s = null;
		try {
			// Exception이 발생하면 JVM이 new ArithmeticException() 진행
			int result = a / b;
			System.out.println(result);
			System.out.println(s.length());
		} catch (ArithmeticException e) {
			System.err.println("ExceptionExample.f1() : " + e.getMessage());
			return; // catch문에서 return을 하더라도 finally는 반드시 수행
		} catch (Exception e) {
			System.err.println(e.getClass().getSimpleName() + " : " + e.getMessage());
		} finally {
			System.out.println("finally 수행");
		}
		System.out.println("f1() 종료");
	}
}
