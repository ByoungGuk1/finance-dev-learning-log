package com.shinhan.day07;

import com.shinhan.day07.other.Computer2;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 22. 오후 2:14:29
 * 설명			: InheritanceExample3
 */
public class InheritanceExample3 extends Computer2{
	public void main(String[] args) {
//		같은 패키지
		Computer com1 = new Computer();
		System.out.println(com1.memory1);
		System.out.println(com1.memory2);
		System.out.println(com1.memory3);
//		System.out.println(com1.memory4);	//	private의 경우 다른 클래스에선 불가

//		다른 패키지 + 상속
		InheritanceExample3 com2 = new InheritanceExample3();
		System.out.println(com2.memory1);
		System.out.println(com2.memory2);
//		System.out.println(com2.memory3);	//	생략의 경우 다른 패키지에선 불가
//		System.out.println(com2.memory4);
		
//		다른 패키지
		Computer2 com3 = new Computer2();
		System.out.println(com3.memory1);
//		System.out.println(com3.memory2);	//	protected의 경우 상속을 받지 않은 다른 패키지에선 불가
//		System.out.println(com3.memory3);
//		System.out.println(com3.memory4);
	}

}
