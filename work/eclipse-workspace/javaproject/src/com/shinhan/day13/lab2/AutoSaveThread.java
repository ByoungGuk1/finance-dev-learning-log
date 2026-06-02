package com.shinhan.day13.lab2;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 2. 오후 2:16:29 설명 : AutoSaveThread
 */
public class AutoSaveThread extends Thread {
	public void save() {
		System.out.println("자동저장 처리");
	}

	@Override
	public void run() {
		while (true) {
			try {
				save();
				sleep(1);
			} catch (InterruptedException e) {
				break;
			} finally {
				System.out.println("자원 종료");
			}
		}
	}
}
