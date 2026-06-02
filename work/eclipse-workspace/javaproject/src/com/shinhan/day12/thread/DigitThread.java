package com.shinhan.day12.thread;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 1. 오후 4:11:37 설명 : DigitThread
 */
// 쓰레드를 만드는 방법
// 1. Thread를 상송 받으면 MultiThread로 사용 가능
//		조건
//			run() override 로 구현
//			start() 로 실행
public class DigitThread extends Thread {
	@Override
	public void run() {
		System.out.println(currentThread().getName() + " thread가 시작");
		for (int i = 0; i < 27; i++) {
			System.out.println(Thread.currentThread().getName() + " - " + i);
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
		System.out.println(currentThread().getName() + " thread가 종료");
	}
}
