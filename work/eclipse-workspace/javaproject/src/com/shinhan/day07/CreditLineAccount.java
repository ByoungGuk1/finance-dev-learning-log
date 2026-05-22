package com.shinhan.day07;

/**
 * 작성자	: 송병국
 * 작성일	: 2026. 5. 22.
 * 설명	: CreditLineAccount
 */
public class CreditLineAccount extends Account{
	int creditLine;
	
	public CreditLineAccount(String accNo, String owner, int balance, int creditLine) {
		super(accNo, owner, balance);
		this.creditLine = creditLine;
	}

//	재정의 : 메서드의 시그니쳐가 같아야한다.
//		접근 지정자는 같거나 더 넓어야한다.
//		pub > pro > default > pri
	@Override
	public int withdraw(int amount) {
		if(getBalance() + creditLine < amount) {
			System.err.println("잔액 부족");
			return 0;
		}
		setBalance((getBalance() - amount));
		return amount;
	}

	@Override
	public String toString() {
		return "CreditLineAccount [creditLine=" + creditLine + ", " + super.toString() + "]";
	}
	
}
