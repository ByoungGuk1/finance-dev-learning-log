package com.shinhan.day07;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 22. 오후 12:24:33
 * 설명			: BonusPointAccount
 */
public class BonusPointAccount extends Account{
	int bonusPoint;

	public BonusPointAccount(String accNo, String owner, int balance, int bonusPoint) {
		super(accNo, owner, balance);
		this.bonusPoint = bonusPoint;
	}

	@Override
	public void deposit(int amount) {
		super.deposit(amount);	//	부모의 메서드를 호출
		bonusPoint += (int)(amount * 0.001);
	}

	@Override
	public String toString() {
		return "BonusPointAccount [bonusPoint=" + bonusPoint + ", " + super.toString() + "]";
	}

}
