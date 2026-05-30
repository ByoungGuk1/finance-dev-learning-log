package com.shinhan.day11.lab4;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 5. 29. 오후 4:17:12 설명 : Prob1
 */
public class Prob1 {
	public static void main(String[] args) {
		StringBuilder result = new StringBuilder();
		String str = "everyday we have is one more than we deserve";
		char[] charArr = str.toCharArray();
		for (char ch : charArr) {
			if (ch == ' ') {
				result.append(' ');
				continue;
			}
			switch (ch) {
			case 'x' -> {
				result.append('a');
			}
			case 'y' -> {
				result.append('b');
			}
			case 'z' -> {
				result.append('c');
			}
			default -> {
				result.append((char) (ch + 3));
			}
			}
		}

		System.out.println(result.toString());
	}
}
