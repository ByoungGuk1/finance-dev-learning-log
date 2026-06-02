package com.shinhan.day13;

import lombok.Getter;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 2. 오전 10:39:43 설명 : Calculator
 */
@Getter
public class Calculator {
	private int memory;

	synchronized void setMemory(int memory) {
		this.memory = memory;
		try {
			Thread.sleep(2000); // 2초간 정지
		} catch (InterruptedException e) {
			System.err.println("err <Calculator.setMemory()> : " + e.getMessage());
		}
		System.out.println(Thread.currentThread().getName() + " : " + this.memory);
	}

}
