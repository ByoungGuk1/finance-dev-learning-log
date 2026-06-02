package com.shinhan.day13;

import lombok.AllArgsConstructor;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 2. 오전 11:44:19 설명 : BathRoomThread
 */
@AllArgsConstructor
public class BathRoomThread extends Thread {
	BathRoom bathRoom;
	String userName;

	@Override
	public void run() {
		bathRoom.use(userName);
	}
}
