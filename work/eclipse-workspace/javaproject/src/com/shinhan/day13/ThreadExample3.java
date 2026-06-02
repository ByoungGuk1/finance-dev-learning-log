package com.shinhan.day13;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 2. 오전 11:18:27 설명 : ThreadExample3
 */
public class ThreadExample3 {
	public static void main(String[] args) {
		Account acc1 = new Account("111", "이몽룡", 2000);
		Account acc2 = new Account("112", "성춘향", 1000);
		ShareArea shareArea = new ShareArea(acc1, acc2);

		TransferThread t1 = new TransferThread(shareArea);
		PrintThread t2 = new PrintThread(shareArea);

		t1.start();
		t2.start();
	}
}
