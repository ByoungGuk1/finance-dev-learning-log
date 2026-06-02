package com.shinhan.day11.review;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import com.shinhan.day05.lab6.Student;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 1. 오전 9:10:14 설명 : Review
 */
public class Review {
	public static void main(String[] args) {
//		f1();
//		f7();
//		f14();
//		f15();
//		f16();
//		f17();
//		f18();
		f19();
	}

	private static void f1() {
		Object obj = new Object();
		System.out.println(obj);
		System.out.println(obj.toString());
	}

	private static void f7() {
		String str = "아이디,이름,패스워드";

		StringTokenizer st = new StringTokenizer(str, ",");
		while (st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}

		String[] strArr = str.split(",");
		for (String stri : strArr) {
			System.out.println(stri);
		}
	}

	private static void f14() {
//		double rnd = Math.random();
//		int randomInt = (int) (rnd * 10 + 1);
//		System.out.println(randomInt);

//		int[] lotto = new int[6];
//		int i = 0;
//		while (lotto[i] == 0) {
//			int number = (int) (Math.random() + 1) * 45;
//			for (int j = 0; j <= i; j++) {
//				if (lotto[j] == number)
//					break;
//				if (j == i)
//					lotto[i++] = number;
//			}
//		}
//		System.out.println(Arrays.toString(lotto));

		HashSet<Integer> hashInt = new HashSet<>();

		while (hashInt.size() < 6) {
			int number = ((int) (Math.random() * 10)) * 45;
			hashInt.add(number);
		}
		System.out.println(hashInt);

		Random r = new Random();
		System.out.println(r.nextInt(1)); // 0보다 크고 n보다 작은 정수 => n 미포함
	}

	private static void f15() {
//		 올해 12월 31일까지 몇 일이 남았는지를 구하는 코드를 작성해보세요
		LocalDate ldt = LocalDate.now();

		System.out.println(ldt.getYear());

		LocalDate target = LocalDate.of(ldt.getYear(), 12, 31);
		long day = ChronoUnit.DAYS.between(ldt, target);
		System.out.println(day + "일 남음");
	}

	private static void f16() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 E요일 HH시 mm분");
		String dateF = sdf.format(date);
		System.out.println(dateF);
	}

	private static void f17() {
		class PatternMatcherExample {
			public static void main(String[] args) {
				String id = "5Angel1004";
				String regExp = "{8,12}\\w+";
				boolean isMatch = Pattern.matches(id, regExp);
				if (isMatch) {
					System.out.println("ID로 사용할 수 있습니다.");
				} else {
					System.out.println("ID로 사용할 수 없습니다.");
				}
			}
		}
		PatternMatcherExample.main(null);
	}

	private static void f18() {
		Class<?> a = Student.class;

		a.getResource("Review.java"); // url로 탐지 resource경로에서 참조하므로 .java가 아닌 .class로 탐색

		Method[] aMethods = a.getDeclaredMethods();

		for (Method method : aMethods) {
			System.out.println(method.getName());
			if (method.getName().equals("getAge")) {
//				method.invoke(a, aMethods);
			}
		}
	}

	private static void f19() {

	}
}
