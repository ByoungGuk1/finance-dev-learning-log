package com.shinhan.day13;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 2. 오후 12:23:12 설명 : ThreadExample5
 */
public class ThreadExample5 {
	public static void main(String[] args) {
		WorkThread t1 = new WorkThread("1번 쓰레드");
		WorkThread t2 = new WorkThread("2번 쓰레드");

		t1.start();
		t2.start();

		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t1.work = false;

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t1.work = true;
	}
}
