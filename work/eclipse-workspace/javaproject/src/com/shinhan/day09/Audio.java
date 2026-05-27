package com.shinhan.day09;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 27. 오전 11:18:42
 * 설명			: Audio
 */
public class Audio implements RemoteControl {

	@Override
	public void turnOn() {
		System.out.println(getClass().getSimpleName()+"전원 켬");
	}

	@Override
	public void turnOff() {
		System.out.println(getClass().getSimpleName()+"전원 끔");
	}

}
