package com.shinhan.day10.lab;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 5. 28. 오후 4:40:53 설명 : DiceGame
 */
class Dice {
	int size;

	Dice(int size) {
		this.size = size;
	}

	int play() {
		int number = (int) (Math.random() * size) + 1;
		return number;
	}
}

public class DiceGame {
	public static void main(String args[]) {
		DiceGame game = new DiceGame();

		int result1 = game.countSameEye(10);
		System.out.println("면의 개수가 8개인 주사위 2개를  던져서 같은 눈이 나온 횟수 : " + result1);

		int result2 = game.countSameEye(-10);
		System.out.println("면의 개수가 8개인 주사위 2개를  던져서 같은 눈이 나온 횟수 : " + result2);

	}

	int countSameEye(int n) {
		try {
			if (n <= 0) {
				throw new IllegalArgumentException("매개변수 값이 잘못됐습니다.");
			}
		} catch (IllegalArgumentException e) {
			System.err.println("IllegalArgumentException: " + e.getMessage());
			return -1;
		}
		int result = 0;
		Dice dice = new Dice(8);
		for (int i = 0; i < n; i++) {
			int num1 = 0;
			int num2 = 0;

			num1 = dice.play();
			num2 = dice.play();

			System.out.println("==" + (i + 1) + "회 주사위 결과==");
			System.out.println("1번 주사위: " + num1);
			System.out.println("2번 주사위: " + num2);

			result = num1 == num2 ? ++result : result;
		}
		return result;
	}
}
