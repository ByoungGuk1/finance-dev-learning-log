package com.shinhan.day11.lab8;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 5. 29. 오후 4:44:04 설명 : StringComparator
 */
// Q1
public class StringComparator {
	public static void main(String[] args) {
		StringComparator sc = new StringComparator();
		int result1 = sc.compare("Yongari-2001", "DragonWar", true);
		int result2 = sc.compare("Yongari-2001", "DragonWar", false);
		System.out.println(result1);// 12
		System.out.println(result2);// 9
	}

	public int compare(String str1, String str2, boolean isLength) {
		if (isLength) {
			return Math.max(str1.length(), str2.length());
		}
		int count1 = 0;
		int count2 = 0;
		char[] charArr = str1.toCharArray();
		for (char ch : charArr) {
			count1 = ch == 'a' ? count1 + 1 : count1;
		}
		charArr = str2.toCharArray();
		for (char ch : charArr) {
			count2 = ch == 'a' ? count2 + 1 : count2;
		}

		return count1 > count2 ? str1.length() : str2.length();
	}
}
