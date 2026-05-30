package com.shinhan.day11.lab8;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 5. 29. 오후 5:04:17 설명 : StringUtil
 */
// Q3
public class StringUtil {
	public static String concatenate(String[] str) {
		StringBuffer sb = new StringBuffer();
		// 문자열을 결합하여 리턴하는 메소드 구현
		for (String st : str) {
			sb.append(st);
		}
		return sb.toString();
	}

	public static void main(String args[]) {
		String[] strArr = { "SuperMan", "BatMan", "SpiderMan" };
		String resultStr = concatenate(strArr);
		System.out.println("결과 문자열 : " + resultStr);
	}
}
