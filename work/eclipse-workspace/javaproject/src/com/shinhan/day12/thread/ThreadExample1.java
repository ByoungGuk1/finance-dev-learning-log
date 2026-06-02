package com.shinhan.day12.thread;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 1. 오후 4:07:18 설명 : ThreadExample1
 */
public class ThreadExample1 {
	public static void main(String[] args) {
////		멀티 스레드
//		f2();
//
////		싱글 스레드
//		f1();

//		쓰레드 사용시 주의 사항
		f3();
	}

	private static void f1() {
		System.out.println(Thread.currentThread().getName() + " thread가 시작");

		for (int i = 0; i < 27; i++) {
			System.out.println(Thread.currentThread().getName() + " - " + i);
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println();

		for (char ch = 'A'; ch <= 'Z'; ch++) {
			System.out.print(Thread.currentThread().getName() + " - " + ch);
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
		System.out.println(Thread.currentThread().getName() + " thread가 종료");
	}

	private static void f2() {
//		Thread를 상속받아 생성
		DigitThread t1 = new DigitThread();
//		(Runable)인터페이스를 구현하고 Thread로 생성
		LowercaseRunableImpl impl = new LowercaseRunableImpl();
		Thread t2 = new Thread(impl);
//		익명객체로 생성
		Thread t3 = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 3; i++) {
					System.out.print(Thread.currentThread().getName() + " - " + i);
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		};

		t1.setName("상속으로 쓰레드 생성");
		t2.setName("구현체로 만든 후, 객체화");
		t3.setName("익명객체로 정의");

		t1.start();
		t2.start();
		t3.start();
	}

	private static void f3() {
		System.out.println(Thread.currentThread().getName() + " thread가 시작");
		DigitThread t1 = new DigitThread();
		t1.setName("무슨 쓰레드일까요");
		t1.run();
//		멀티 쓰레드 아님! override된 run 메서드 실행
		System.out.println(Thread.currentThread().getName() + " thread가 종료");
	}
}
