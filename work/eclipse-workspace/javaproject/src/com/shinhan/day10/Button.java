package com.shinhan.day10;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 5. 28. 오전 9:43:02 설명 : Button
 */

// interface => 규칙
public class Button {
	private ClickListener clickListener;

	public void setClickListener(ClickListener clickListener) {
		this.clickListener = clickListener;
	}

	public void buttonClick() {
		clickListener.onClick();
	}

	// 내부 interface
	public static interface ClickListener {
//		상수, 추상메서드, default method, static method, private method, private static method
		void onClick();
	}
}
