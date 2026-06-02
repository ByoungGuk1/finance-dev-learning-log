package com.shinhan.day13;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 2. 오후 12:20:00 설명 : WorkThread
 */
public class WorkThread extends Thread {
	boolean work = true;

	public WorkThread(String name) {
		super(name);
	}

	@Override
	public void run() {
		while (true) {
			if (work) {
				System.out.println(getName() + " 진행 중");
			} else {
				Thread.yield();
			}
		}
	}
}
