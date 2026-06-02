package com.shinhan.day13;

import lombok.Setter;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 2. 오전 10:44:44 설명 : User1Thread
 */
@Setter
public class User1Thread extends Thread {
//	공유자원
	private Calculator calculator;

	public User1Thread() {
		super("User1Thread");
	}

	@Override
	public void run() {
		calculator.setMemory(100);
	}
}
