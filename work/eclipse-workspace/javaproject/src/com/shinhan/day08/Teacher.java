package com.shinhan.day08;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 26. 오후 3:08:45
 * 설명			: Teacher
 */
public non-sealed class Teacher extends Person {

}

class AA extends Teacher {}	//	non-sealed 로 sealed 상태 해제
//class CC extends Person	{}	//	sealed 상태로 인해 상속 불가