package com.shinhan.day09.lab1;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 5. 27. 오전 11:34:50 설명 : Speaker
 */
public class Speaker implements Volume {
	private static final int MAX_VOLUME_VALUE = 100;
	private static final int MIN_VOLUME_VALUE = 0;

	private int volumeValue;

	public Speaker() {
		volumeValue = 20;
	}

	public Speaker(int volumeValue) {
		if (volumeValue >= MIN_VOLUME_VALUE && volumeValue <= MAX_VOLUME_VALUE) {
			this.volumeValue = volumeValue;
		} else {
			this.volumeValue = 20;
		}
	}

	@Override
	public void volumeUp(int amount) {
//		if(volumeValue + amount > MAX_VOLUME_VALUE) {
//			volumeValue = MAX_VOLUME_VALUE;
//			return;
//		}
//		volumeValue += amount;
		volumeValue = Math.min(volumeValue + amount, MAX_VOLUME_VALUE);
		System.out.println(getClass().getSimpleName() + "볼륨 올립니다." + volumeValue);
	}

	@Override
	public void volumeDown(int amount) {
//		if(volumeValue - amount < MIN_VOLUME_VALUE) {
//			volumeValue = MIN_VOLUME_VALUE;
//			return;
//		}
//		volumeValue -= amount;
		volumeValue = Math.max(volumeValue - amount, MIN_VOLUME_VALUE);
		System.out.println(getClass().getSimpleName() + "볼륨 내립니다." + volumeValue);
	}

}
