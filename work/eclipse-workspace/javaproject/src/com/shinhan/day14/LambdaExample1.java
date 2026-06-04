package com.shinhan.day14;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 4. 오후 12:30:57 설명 : LamdaExample1
 */
//	람다 표현식
//		interface 에 단 1개의 메서드만 있는 경우 사용할 수 있는 함수 형태의 표현식
@FunctionalInterface
interface MyInterface {
	int add(int num1, int num2);
}

@FunctionalInterface
interface MyInterface1 {
	void print(int n);
}

class RunnableImpl implements Runnable {
	@Override
	public void run() {
		System.out.println("Runnable 인터페이스를 구현한 구현체");
	}
}

public class LambdaExample1 {
	public static void main(String[] args) {
		method1();
		method2();
	}

	private static void method1() {
		Thread t1 = new Thread(new RunnableImpl());
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("익명 Class로 구현한 구현체");
			}
		});
		Thread t3 = new Thread(() -> System.out.println("람다식으로 구현한 쓰레드"));

		t1.start();
		t2.start();
		t3.start();
	}

	private static void method2() {
		MyInterface my = (a, b) -> a + b;
		System.out.println(my.add(1, 2));

		MyInterface1 my1 = a -> System.out.println(a);
		my1.print(5);
	}
}
