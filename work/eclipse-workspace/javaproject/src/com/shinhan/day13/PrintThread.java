package com.shinhan.day13;

import lombok.AllArgsConstructor;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 2. 오전 11:14:54 설명 : PrintThread
 */
@AllArgsConstructor
public class PrintThread extends Thread {
	ShareArea shareArea;

	@Override
	public void run() {
//		5번 계좌 잔액의 합계 출력
		for (int i = 0; i < 5; i++) {
			synchronized (shareArea) {
				int amount1 = shareArea.lee.getBalance();
				int amount2 = shareArea.sung.getBalance();
				System.out.println("계좌 잔액의 합계는 " + (amount1 + amount2) + " 입니다.");
			}
			try {
				sleep(12);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
