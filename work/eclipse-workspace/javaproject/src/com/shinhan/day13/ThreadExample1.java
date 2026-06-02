package com.shinhan.day13;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 2. 오전 10:18:52 설명 : ThreadExample1
 */
// MultiThread : 프로그램의 흐름이 여러 개
// 만들기
// 	1. Thread 상속 후 run() 재정의
class MyThread1 extends Thread {
	public MyThread1(String name) {
		super(name);
	}

	@Override
	public void run() {
		for (int i = 100; i < 110; i++) {
			System.out.print(i + 1 + " ");
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println();
		System.out.println(currentThread().getName() + "쓰레드");
	}
}

//	2. runable 구현 후 run() 재정의
class Parent {
}

class MyRunnableImpl extends Parent implements Runnable {
	@Override
	public void run() {
		for (int i = 200; i < 210; i++) {
			System.out.print(i + 1 + " ");
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println();
		System.out.println(Thread.currentThread().getName() + "쓰레드");
	}
}

public class ThreadExample1 {
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + " 쓰레드 시작");
		f2();
		f1();
		System.out.println(Thread.currentThread().getName() + " 쓰레드 종료");
	}

	private static void f1() {
		for (int i = 0; i < 10; i++) {
			System.out.print(i + 1 + " ");
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println();
		System.out.println(Thread.currentThread().getName() + "쓰레드");
	}

	private static void f2() {
		System.out.println("===f2() 시작===");
		MyThread1 t1 = new MyThread1("100부터 110까지");
		Thread t2 = new Thread(new MyRunnableImpl(), "200부터 210까지");
		Thread t3 = new Thread("300부터 310까지") {
			@Override
			public void run() {
				for (int i = 300; i < 310; i++) {
					System.out.print(i + 1 + " ");
					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println();
				System.out.println(Thread.currentThread().getName() + "쓰레드");
			}
		};
		t1.start();
		t2.start();
		t3.start();
		System.out.println("===f2() 종료===");
	}
}
