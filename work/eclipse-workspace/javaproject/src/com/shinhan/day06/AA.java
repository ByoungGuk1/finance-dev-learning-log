package com.shinhan.day06;

import com.shinhan.day06.other.Friday;

/**
 * 작성자	: 송병국
 * 작성일	: 2026. 5. 21.
 * 설명	: AA
 */
public class AA {
	private String pass = "1234";
	private String pass2;
	
	public AA() {;}
	public AA(String pass2) {
		this.pass2 = pass2;
	}

//	JavaBeans 기술은 getter/setter 필수
//	이름 규칙 지켜서 작성
	public String getPass() {
		return "pw" + pass;
	}
	public void setPass(String pass) {
		this.pass = pass + "!";
	}
	public String getPass2() {
		return "pw2" + pass2;
	}
	public void setPass2(String pass2) {
		this.pass2 = pass2 + "!!";
	}

	void f1() {
		Friday a = new Friday();
//		Friday2	/	public이 없어서 다른 패키지에서 접근 불가
	}
}
