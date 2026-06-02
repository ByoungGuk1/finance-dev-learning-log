package com.shinhan.day13;

import lombok.AllArgsConstructor;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 2. 오전 11:10:51 설명 : TransferThread
 */
@AllArgsConstructor
public class TransferThread extends Thread {
	ShareArea shareArea;

	@Override
	public void run() {
//		12번 계좌 이체
		for (int i = 0; i < 12; i++) {
			shareArea.transfer();
			try {
				sleep(12);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
