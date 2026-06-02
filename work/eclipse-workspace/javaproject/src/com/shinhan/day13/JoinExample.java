package com.shinhan.day13;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 2. 오후 12:34:01 설명 : JoinExample
 */
public class JoinExample {
	public static void main(String[] args) {
		SumThread sumThread = new SumThread();
		sumThread.start();

		try {
			// 해당 쓰레드가 종료될 때 까지 기다리기
			sumThread.join();
		} catch (InterruptedException e) {

		}

		System.out.println("1 ~ 100의 합 : " + sumThread.getSum());
	}
}
