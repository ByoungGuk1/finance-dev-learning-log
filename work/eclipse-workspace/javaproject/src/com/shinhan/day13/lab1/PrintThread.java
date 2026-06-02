package com.shinhan.day13.lab1;

import lombok.Setter;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 2. 오후 12:45:00 설명 : PrintThread
 */
// 쓰레드 안전하게 종료하기
// 1. flag를 두고 while문으로 해결하기
// 2. exception을 던지고 트라이캐치로 종료하기
// 3. 혼합
@Setter
public class PrintThread extends Thread {
	boolean isStop;

//	@Override
//	public void run() {
//		try {
//			while (!isStop) {
//				System.out.println("실행중");
//				sleep(1);
//			}
//		} catch (InterruptedException e) {
//			System.err.println("PrintThread.run() err : " + e.getMessage());
//		} finally {
//			System.out.println("리소스 정리");
//		}
//		System.out.println(getName() + " 종료");
//	}

	@Override
	public void run() {
		while (!isStop) {
			System.out.println("실행중");
			if (Thread.interrupted()) {
				break;
			}
		}
		System.out.println("리소스 정리");
		System.out.println(getName() + " 종료");
	}
}
