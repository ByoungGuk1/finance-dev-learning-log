package com.shinhan.day09;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 27. 오전 11:19:42
 * 설명			: RemoteApp
 */
public class RemoteApp {
	public static void main(String[] args) {
		RemoteControl t = makeMachine("TV");
		t.turnOn();
		t.turnOff();
	}
	
//	factory pattern
	private static RemoteControl makeMachine(String machine) {
		if(machine.equals("TV")){
			return new Television();
		}
		return new Audio();
	}
}
