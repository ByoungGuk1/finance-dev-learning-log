package com.shinhan.day13;

import lombok.AllArgsConstructor;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 2. 오전 11:09:26 설명 : ShareArea
 */
@AllArgsConstructor
public class ShareArea {
	Account lee;
	Account sung;

//	여러 Thread 가 동시에 공유 영역을 사용
//	하나의 Thread 가 점유하는 동안 다른 Thread 가 접근하지 못하도록 Lock 걸기 (synchronized)
//	1. 함수 선언부에 Modifier 로 사용
//	2. 공유 영역을 접근하는 부분에 synchronized 블럭을 사용
	public synchronized void transfer() {
		int amount = this.lee.withdraw(100);
		System.out.println("lee 계좌에서 " + amount + " 인출");
		this.sung.deposit(100);
		System.out.println("sung 계좌에 입금");
	}

	public synchronized void print() {
		int amount1 = this.lee.getBalance();
		int amount2 = this.sung.getBalance();
		System.out.println("계좌 잔액의 합계는 " + (amount1 + amount2) + " 입니다.");
	}
}
