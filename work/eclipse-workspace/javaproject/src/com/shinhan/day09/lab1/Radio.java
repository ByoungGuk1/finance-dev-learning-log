package com.shinhan.day09.lab1;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 27. 오전 11:40:52
 * 설명			: Radio
 */
public class Radio implements Volume {
	private int volumeValue;

	public Radio() {;}
	public Radio(int volumeValue) {
		this.volumeValue = volumeValue;
	}

	@Override
	public void volumeUp(int amount) {
		volumeValue += amount;
		System.out.println(getClass().getSimpleName() + "볼륨 올립니다." + volumeValue);
	}

	@Override
	public void volumeDown(int amount) {
		volumeValue -= amount;
		System.out.println(getClass().getSimpleName() + "볼륨 내립니다." + volumeValue);
	}

}
