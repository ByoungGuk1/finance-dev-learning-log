package com.shinhan.day11.lab1;

import lombok.EqualsAndHashCode;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 5. 29. 오후 3:34:56 설명 : CellPhone
 */
@EqualsAndHashCode(of = "model")
public class CellPhone {
	private String model;
	private double battery;

	public CellPhone(String model) {
		this.model = model;
	}

	public void call(int time) {
		if (time < 0) {
			throw new IllegalArgumentException("통화시간입력오류");
		}
		double usedBattery = time * 0.5;
		System.out.println("통화시간 : " + time + "분");
		battery = Math.max(battery - usedBattery, 0);
	}

	public void charge(int time) {
		if (time < 0) {
			throw new IllegalArgumentException("충전시간입력오류");
		}
		double chargedBattery = time * 3;
		System.out.println("충전 시간 : " + time + "분");
		battery = Math.min(chargedBattery + battery, 100);
	}

	public void printBattery() {
		System.out.println("남은 배터리 양 : " + battery);
	}

//	@Override
//	public int hashCode() {
//		return Objects.hash(model);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		CellPhone other = (CellPhone) obj;
//		return Objects.equals(model, other.model);
//	}
}
