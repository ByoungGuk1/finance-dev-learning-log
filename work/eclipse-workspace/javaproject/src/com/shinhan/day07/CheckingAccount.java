package com.shinhan.day07;

/**
 * 작성자	: 송병국
 * 작성일	: 2026. 5. 22.
 * 설명	: CheckingAccount
 */

/*
 *	Account를 상속받음
 *		field + method를 사용가능 (단, private의 접근 제한 항목은 제외) + 자식 객체의 내용으로 field와 method를 추가 가능
 *		자식객체를 생성(`new 자식클래스())하면 부모 객체도 생성된다
 *		자식 클래스의 생성자의 첫번째 줄에 super()가 생략되어있다.
 *			=> 부모 객체의 기본 생성자는 `반드시` 필요
 */
public class CheckingAccount extends Account{
	private String cardNo;
	
	public CheckingAccount() {
//		super(); // 지워도 default로 있다.
//		super()의 경우 첫번째 줄에만 들어갈 수 있다.
//		this()호출 시 super()를 같이 사용할 수 없다.
		System.out.println("CheckingAccount default constructor");
	}
	public CheckingAccount(String accNo, String owner, int balance, String cardNo) {
//		명시적으로 부모의 생성자를 호출
		super(accNo, owner, balance);
		this.cardNo = cardNo;
		System.out.println("CheckingAccount argument 4 cunstructor");
	}

	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
	int pay(String cardNo, int amount) {
		if(!cardNo.equals(this.cardNo)) {	//	null 가능성이 있으면 equals 내부로 정리해두면 좋다.
			System.err.println("카드번호 불일치");
			return 0;
		}
		return super.withdraw(amount);
	}
	void f1() {
		System.out.println("===부모 필드 접근===");
		System.out.println(getAccNo());
		System.out.println(owner);			//	protected로 선언한 필드는 자식객체에서 접근 가능
		System.out.println(getBalance());	//	부모 클래스에서 프라이빗으로 선언한 필드는 접근 불가
		deposit(1000);
		int result = withdraw(600);
		System.out.println(result);
	}
	@Override
	public String toString() {
		return super.toString() + ", CheckingAccount(자식 객체) [cardNo=" + cardNo + "]";
	}
	
}
