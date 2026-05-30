package com.shinhan.day11.lab6;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 5. 29. 오후 4:40:12 설명 : FindCharacters
 */
public class FindCharacters {
	public static void main(String[] args) {
		FindCharacters fc = new FindCharacters();
		int count = fc.countChar("Boys, be ambitious", 'b');
		System.out.println(count);
	}

	public int countChar(String str, char c) {
		int result = 0;
		char[] charArr = str.toCharArray();
		for (char ch : charArr) {
			result = ch == c ? result + 1 : result;
		}
		return result;
	}
}
