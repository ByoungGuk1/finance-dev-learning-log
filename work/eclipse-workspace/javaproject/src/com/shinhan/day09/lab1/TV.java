package com.shinhan.day09.lab1;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 27. 오전 11:47:00
 * 설명			: TV
 */
public class TV implements Volume {
	private static final int MIN_VOLUME_VALUE = 0;
	private int volumeValue;
	
	public TV() {;}
	public TV(int volumeValue) {
		this.volumeValue = volumeValue;
	}

	@Override
	public void volumeUp(int amount) {
		volumeValue += amount;
		System.out.println(getClass().getSimpleName() + "볼륨 올립니다." + volumeValue);
	}

	@Override
	public void volumeDown(int amount) {
		volumeValue = Math.max(volumeValue-amount, MIN_VOLUME_VALUE);
		System.out.println(getClass().getSimpleName() + "볼륨 내립니다." + volumeValue);
	}

}
