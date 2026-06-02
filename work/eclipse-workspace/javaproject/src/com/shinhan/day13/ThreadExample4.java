package com.shinhan.day13;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 2. 오전 11:47:19 설명 : ThreadExample4
 */
public class ThreadExample4 {
	public static void main(String[] args) {
		BathRoom room = new BathRoom();

		BathRoomThread t1 = new BathRoomThread(room, "홍길동");
		BathRoomThread t2 = new BathRoomThread(room, "이순신");
		BathRoomThread t3 = new BathRoomThread(room, "장보고");
		BathRoomThread t4 = new BathRoomThread(room, "?");

		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}
