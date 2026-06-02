package com.shinhan.day13;

import lombok.Getter;
import lombok.Setter;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 2. 오후 12:32:13 설명 : SumThread
 */
@Getter
@Setter
public class SumThread extends Thread {
	long sum;

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			sum += (i + 1);
		}
	}
}
