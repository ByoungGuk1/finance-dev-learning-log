package com.shinhan.day13;

import lombok.Setter;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 2. 오전 10:48:10 설명 : User2Thread
 */
@Setter
public class User2Thread extends Thread {
//	공유자원
	private Calculator calculator;

	public User2Thread() {
		super("User2Thread");
	}

	@Override
	public void run() {
		calculator.setMemory(50);
	}
}
