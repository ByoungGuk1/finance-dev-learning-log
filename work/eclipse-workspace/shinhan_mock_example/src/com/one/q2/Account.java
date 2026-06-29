package com.one.q2;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 2. 오전 9:58:36 설명 : account
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
class Member {
	private int amount;
	private int minusMoney;

	public Member(int minusMoney) {
		this.minusMoney = minusMoney;
	}

	public boolean ledger(int money) {
		if (amount + minusMoney + money < 0) {
			return false;
		}
		amount += money;
		return true;
	}
}

public class Account {
	public static void main(String[] args) {
		int m = 5_000;
//		int m = 34151;
		Member mb = new Member(m);
		int[] tradeAmount = { 10000, -13000, -4000, -2000, 6500, -20000 };
//		int[] tradeAmount = { -34152, -40000, -500000 };
		for (int money : tradeAmount) {
			if (!mb.ledger(money)) {
				System.out.println("거절");
			}
			System.out.println(mb.getAmount());
		}
	}
}
