package com.shinhan.day13;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 2. 오전 11:07:28 설명 : Account
 */
@AllArgsConstructor
@Getter
public class Account {
	String accountNo; // 계좌번호
	String ownerName; // 예금주 이름
	int balance; // 잔액

	public void deposit(int amount) {
		balance += amount;
	}

	public int withdraw(int amount) {
		if (balance < amount) {
			return 0;
		}
		balance -= amount;
		return amount;
	}
}
