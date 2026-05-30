package com.shinhan.day11.lab8;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 5. 29. 오후 5:07:48 설명 : Money
 */
public class Money {
	int amount;

	public Money(int amount) {
		this.amount = amount;
	}

	public Money add(Money money) {
		// 더하기 구현
		return new Money(this.amount + money.amount);
	}

	public Money minus(Money money) {
		// 빼기 구현
		return new Money(this.amount - money.amount);
	}

	public Money multiply(Money money) {
		// 곱하기 구현
		return new Money(this.amount * money.amount);
	}

	public Money divide(Money money) {
		// 나누기 구현
		return new Money(this.amount / money.amount);
	}

	public boolean equals(Object object) {
		if (!(object instanceof Money moneyObject))
			return false;
		return this.amount == moneyObject.amount;
	}

	public static void main(String[] args) {
		Money five = new Money(5);
		Money two = new Money(2);
		Money three = new Money(3);
		Money ten = new Money(10);

		if (five.equals(two.add(three)) && three.equals(five.minus(two)) && ten.equals(five.multiply(two))
				&& two.equals(ten.divide(five))) {
			System.out.println("Money Class 구현을 완료 하였습니다.");
		}
	}
}
