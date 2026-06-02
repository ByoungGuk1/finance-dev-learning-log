package com.shinhan.day13.lab2;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 2. 오후 2:18:53 설명 : DaemonExample
 */
public class DaemonExample {
	public static void main(String[] args) {
		AutoSaveThread t1 = new AutoSaveThread();
		t1.setDaemon(true);
		t1.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("메인 종료");
	}
}
