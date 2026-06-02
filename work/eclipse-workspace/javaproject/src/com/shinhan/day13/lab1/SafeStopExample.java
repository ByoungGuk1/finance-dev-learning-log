package com.shinhan.day13.lab1;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 2. 오후 12:48:06 설명 : SafeStopExample
 */
public class SafeStopExample {
	public static void main(String[] args) {
		PrintThread t1 = new PrintThread();
		t1.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

//		t1.setStop(true);
		t1.interrupt();
	}
}
