package com.shinhan.day11;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 5. 29. 오전 11:08:56 설명 : ObjectExample2
 */
public class ObjectExample2 {
	public static void main(String[] args) {
		System.out.println("main 시작");
//		f1();
//		f2(100);
//		f3();
//		f4();
//		f5();
//		f6();
//		f7();
		System.out.println("main 종료");
	}

	private static void f1() {
		Member m1 = new Member("id", "홍길동");
		Member m2 = new Member("id2", "홍길동", 20);

		System.out.println(m1);
		System.out.println(m2);
	}

	private static void f2(int num) {
		System.out.println("함수 시작");
		if (num < 100) {
			// 0은 정상 종료를 의미
//			System.exit(0);
			return;
		}

		System.out.println("함수 종료");
	}

	private static void f3() {
		long startTime = System.currentTimeMillis();
		long startTime2 = System.nanoTime();

//		표준 입력 : default = 키보드
		InputStream is = System.in;
//		표준 출력 : default = 모니터
		PrintStream ps = System.out;

		Properties p = System.getProperties();
		for (Object key : p.keySet()) {
			System.out.println("key : " + (String) key + "\nvalue: " + p.getProperty((String) key));
		}
	}

	private static void f4() {
		String s = "이것이 자바다";
		byte[] byteArr = s.getBytes();
		char[] charArr = s.toCharArray();
		System.out.println(Arrays.toString(byteArr));
		System.out.println(byteArr.length);
		System.out.println(Arrays.toString(charArr));
		System.out.println(charArr.length);

//		ms949, EUC-KR : 2byte, UTF-8 : 3byte
		String s1 = null;
		try {
			s1 = new String(byteArr, "EUC_KR");
		} catch (UnsupportedEncodingException e) {
			System.err.println(e.getMessage());
		}
		if (s1 != null) {
			System.out.println(s1);
		}
	}

	private static void f5() {
		String s = "자바";
		String s2 = new String("자바");
		s = s + "프로그램";
		StringBuilder sb = new StringBuilder(s2);
		sb.append("프로그램");
		sb.insert(sb.length(), "!");
		System.out.println(sb);
	}

	private static void f6() {
		String datas = "이름1:1/이름2:2/이름3:3,이름4 4";
		String[] dataArr = datas.split("/");
		for (String data : dataArr) {
			System.out.println(data);
			StringTokenizer st = new StringTokenizer(data, ":");
			while (st.hasMoreTokens()) {
				System.out.print("토큰: " + st.nextToken());
			}
			System.out.println();
		}
	}

	private static void f7() {
		String datas = "이름1:1/이름2:2/이름3:3,이름4 4";
		String[] arr = datas.split(":|/|,| ");
		System.out.println(Arrays.toString(arr));

		StringTokenizer st = new StringTokenizer(datas, ":|/|,| ");
		while (st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
	}
}
