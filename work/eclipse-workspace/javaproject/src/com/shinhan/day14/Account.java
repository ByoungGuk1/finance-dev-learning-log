package com.shinhan.day14;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 4. 오전 10:30:42 설명 : Account
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "accountNo")
public class Account implements Comparable<Account> {
	private String accountNo; // 계좌번호
	private String ownerName; // 예금주 이름
	private int balance; // 잔액

	void deposit(int amount) {
		balance += amount;
	}

	int withdraw(int amount) {
		if (balance < amount)
			return 0;
		balance -= amount;
		return amount;
	}

	@Override
	public int compareTo(Account o) {
//		1) 잔고 오름차순 asc
		int result = 0;
		result = balance - o.getBalance();
//		2) 이름순
		if (result == 0) {
			result = ownerName.compareTo(o.getOwnerName());
		}
//		3) 계좌번호 순 내림차순(큰 값 - 작은 값) desc
		if (result == 0) {
			result = Integer.valueOf(o.getAccountNo()) - Integer.valueOf(accountNo);
		}
		return result;
	}
}
