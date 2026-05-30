package com.shinhan.day11;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.shinhan.day07.Account;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 5. 29. 오전 11:48:27 설명 : DateExample
 */
public class DateExample {
	public static void main(String[] args) throws Exception {
//		java.util.Date
		f1();
//		java.sql
		f2();
//		Calendar
		f3();
//		(주로 사용)LocalDate, LocalDateTime, Timespamp
		f4();
//		sql.Date => util.Date
		check();
//		문자열 포맷 적용하기
		f5();
//		날짜와 포맷 사용하기
		f6();

//		정규 표현식
		f7();
		f8();

//		정규 표현식 예제
		f9();

//		리플렉션
		f10();

//		리소스 얻기
		f11();
	}

	private static void f1() {
//		레거시에서 주로 사용
		System.out.println("java.util.Date");
		Date d1 = new Date();
		System.out.println(d1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		System.out.println(sdf.format(d1));
	}

	private static void f2() {
//		레거시에서 주로 사용, DB에 있는 date타입과 연동할 때
		System.out.println("java.sql");
		java.sql.Date day = new java.sql.Date(new Date().getTime());
		System.out.println(day);
	}

	private static void f3() {
		System.out.println("Calendar");
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE));
	}

	private static void f4() {
//		LocalDate, LocalDateTime
		System.out.println("LocalDate");
		LocalDate ld = LocalDate.now();
		System.out.println(ld);
		LocalDate ld2 = LocalDate.of(1999, 3, 23);
		System.out.println(ld2);
		System.out.println("LocalDateTime");
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);

		System.out.println("Timestamp");
		Timestamp t = Timestamp.valueOf(ldt);
		System.out.println(t);
		Timestamp t2 = Timestamp.valueOf("2026-12-25 12:00:00");
		System.out.println(t2);
	}

	private static void check() {
		Date date = java.sql.Date.valueOf("2026-12-25");
		System.out.println(date);
	}

	private static void f5() {
		String[] patterns = { "0", // 정수
				"0.0", // 소수 1자리 강제
				"#.#", // 소수 1자리 (없으면 생략)
				"0000.0000", // 자릿수 0으로 채움
				"#,###", // 천단위 구분
				"#,###.##", // 천단위 + 소수
				"₩#,###", // 원화 기호
				"#.##E0", // 지수 표현
				"0.00%", // 퍼센트
		};

		Double money = 1234567.816;
		for (String pattern : patterns) {
			DecimalFormat df = new DecimalFormat(pattern);
			System.out.println(pattern + "패턴 사용");
			System.out.println(df.format(money));
		}
	}

	private static void f6() {
		Date date = new Date();
		String[] patterns = { "yyyy-MM-dd", "yyyy/MM/dd", "yyyy년 MM월 dd일", "yy.MM.dd", "HH:mm:ss", "hh:mm:ss a",
				"yyyy-MM-dd HH:mm:ss", "yyyy년 MM월 dd일 E요일", "yyyy-MM-dd HH:mm:ss.SSS", //
				"yyyy-MM-dd'T'HH:mm:ss"// ISO 8601
		};
		for (String pattern : patterns) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			String stringDate = sdf.format(date);
			System.out.println(pattern + " 패턴 적용");
			System.out.println(stringDate);
		}
	}

	private static void f7() {
		String phoneNumber = "010-1234-5678";
		String regExp = "[01]{3}-\\d{3,4}-[0-9]{4}";

		boolean result = Pattern.matches(regExp, phoneNumber);
		System.out.println(phoneNumber + "는 조건`" + regExp + "`에 " + result);
	}

	private static void f8() {
		String text = "고객센터010-1234-1234 또는 02-5678-5678 팩스는 031-9000-9111";
		String regExp = "(\\d{2,3})-(\\d{3,4})-(\\d{4})";

		Pattern pattern = Pattern.compile(regExp);
		Matcher matcher = pattern.matcher(text);
		System.out.println("----------");
		while (matcher.find()) {
			System.out.println(matcher.group());
			System.out.println("지역/통신사: " + matcher.group(1));
			System.out.println("중간번호: " + matcher.group(2));
			System.out.println("끝번호: " + matcher.group(3));
			System.out.println("----------");
		}
	}

	private static void f9() {
		String text = "고객센터 bksong121212@gmail.com 또는 2ksong111111@naver.com aaaaaaaa ddddd test@daum.net 010-1234-1234 또는 02-5678-5678 팩스는 031-9000-9111";
		String regExp = "\\b[A-Za-z][A-Za-z0-9._%+-]*@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b";

		Pattern pattern = Pattern.compile(regExp);
		Matcher matcher = pattern.matcher(text);
		System.out.println(matcher);
		System.out.println("----------");
		while (matcher.find()) {
			System.out.println(matcher.group());
			System.out.println(matcher.group(1));
			System.out.println(matcher.group(2));
			System.out.println("----------");
		}
	}

	private static void f10() {
		try {
//		<> : 제네릭
//			<?> : 어떤 타입이든지 가능
			Class<?> cls1 = Account.class;
			Class<?> cls2 = Class.forName("com.shinhan.day07.Account");
			Class<?> cls3 = new Account().getClass();

			System.out.println(cls2.getSimpleName());
			Field[] fields = cls2.getFields();
			for (Field field : fields) {
//				접근지정자에 따라 접근 여부 판단
				System.out.println(field.getName());
				System.out.println(field.getModifiers());
				System.out.println(Modifier.toString(field.getModifiers()));
				System.out.println(field.getType().getSimpleName());
			}

		} catch (ClassNotFoundException e) {
			System.err.println("err");
		}
	}

	private static void f11() throws IOException {
		Class<?> cls1 = DateExample.class;
//		String path = cls1.getResource("profile-icon.png").getPath();
////		경로는 bin이므로 .java가 아닌 컴파일 후인 .class
//		String path = cls1.getResource("Member.class").getPath();
		String path = cls1.getResource("resourceTest.txt").getPath();
		System.out.println(path);
		InputStream is = cls1.getResourceAsStream("resourceTest.txt");
		InputStreamReader isr = new InputStreamReader(is);
		int data;
		while ((data = isr.read()) != -1) {
			System.out.print((char) data);
		}
		System.out.println();
	}
}
