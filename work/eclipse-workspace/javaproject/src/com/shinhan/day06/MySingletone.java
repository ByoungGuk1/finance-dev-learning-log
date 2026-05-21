package com.shinhan.day06;

/**
 * 작성자	: 송병국
 * 작성일	: 2026. 5. 21.
 * 설명	: MySingletone
 */
//	Singletone : 객체를 1회만 생성하는 class
//	서블릿이 싱글톤 패턴
public class MySingletone {
	static 	MySingletone my;
	
	private MySingletone() {
		super();
	}
	
	public static MySingletone getInstance() {
		if(my == null) {
			my = new MySingletone();			
		}
		return my;
	}
}
