package com.shinhan.day12.thread;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 1. 오후 4:15:14 설명 : LowercaseThread
 */
class Parent {

}

//쓰레드를 만드는 방법
//2. Runable interface를 구현하여 Thread를 생성, MultiThread로 사용 가능
public class LowercaseRunableImpl extends Parent implements Runnable {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " thread가 시작");

		for (char ch = 'A'; ch <= 'Z'; ch++) {
			System.out.println(Thread.currentThread().getName() + " - " + ch);
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println();

		System.out.println(Thread.currentThread().getName() + " thread가 종료");
	}
}
