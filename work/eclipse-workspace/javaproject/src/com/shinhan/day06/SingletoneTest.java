package com.shinhan.day06;

/**
 * 작성자	: 송병국
 * 작성일	: 2026. 5. 21.
 * 설명	: SingletoneTest
 */
public class SingletoneTest {
	public static void main(String[] args) {
//		MySingletone st1 = new MySingletone();	/	생성자가 private라서 접근 불가
//		MySingletone st2 = new MySingletone();
		MySingletone st1 = MySingletone.getInstance();
		MySingletone st2 = MySingletone.getInstance();
		
//		System.out.println(st1.my);
		System.out.println(st1);
		
		System.out.println(st1 == st2);
	}
}
