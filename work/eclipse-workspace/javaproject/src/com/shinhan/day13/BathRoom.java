package com.shinhan.day13;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 2. 오전 11:42:11 설명 : BathRoom
 */
// 공유 자원
public class BathRoom {
	boolean isFirst = true;

//	Thread 상태 제어 명령어
	/*
	 * interrupt(); notify(); notifyAll(); -> 일시정지 => 실행대기
	 *
	 * yield(); -> 실행 => 실행대기
	 *
	 * sleep(); join(); wait(); -> 실행 => 일시정지
	 */

	public synchronized void use(String name) {
		if (isFirst && name.equals("홍길동")) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		isFirst = false;
		System.out.println(name + " 입장");
		System.out.println(name + " 사용");
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(name + " 퇴장");
		System.out.println("-----------");
		notify();
	}

	public void use2(String name) {
		synchronized (this) {
			System.out.println(name + " 입장");
			System.out.println(name + " 사용");
			System.out.println(name + " 퇴장");
			System.out.println("-----------");
		}
	}
}
