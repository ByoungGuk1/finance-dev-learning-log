package com.shinhan.day04;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;

//	enum : 한정된 값들의 모임, 상수들의 묶음
//	상수 : 한번 값을 할당하면 변경이 불가능하다.

enum Week{	// 여기서 작성하면 같은 패키지에서만 접근 가능
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

public class EnumExample {
//	static 이므로 class load 시, method 영역으로 들어간다
//	final 이므로 변경 불가
	static final String SUCCESS = "성공";	//	상수로 사용하기
	static final String SUBJECT = "자바";	// static final 사용
	static final int MAX_VALUE = 90;
	static final String SUNDAY = "일요일";

	public static void main(String[] args) {
		f1();
		f2();
		f3();
		f4();
	}
	
	private static void f1() {
		int input = 95;
		if(input > MAX_VALUE) {
			System.out.println(SUCCESS);
		}
	}
	
	private static void f2() {
		Week[] arr = Week.values();
		for(Week day : arr) {
			System.out.println(day + "---" + day.name() + "---" + day.ordinal());
		}
		Week today = Week.TUESDAY;
//		today = "TUESDAY";	// 사용 불가, 컴파일 오류
		System.out.println(today);
	}
	
	private static void f3() {
		Calendar cal = Calendar.getInstance();
//		System.out.println(cal);
		int week = cal.get(Calendar.DAY_OF_WEEK);
		System.out.println(week);	//	1(s), 2(m), 3(t), 4(w), 5(t), 6(f)
		
		Week today = null;
		switch(week) {
		case 1 -> today = Week.SUNDAY;
		case 2 -> today = Week.MONDAY;
		case 3 -> today = Week.TUESDAY;
		case 4 -> today = Week.WEDNESDAY;
		case 5 -> today = Week.THURSDAY;
		case 6 -> today = Week.FRIDAY;
		case 7 -> today = Week.SATURDAY;
		}
		System.out.println(today);
		
		String today2 = null;
		if(week == 1) {
			today2 = SUNDAY;
		}
		System.out.println(today2);
		
	}
	
	private static void f4() {
		LocalDate localDate = LocalDate.now();
		System.out.println(localDate);
		System.out.println(localDate.getDayOfWeek());
		DayOfWeek dayOfWeek = localDate.getDayOfWeek();
		System.out.println(dayOfWeek);
		
//		LocalDateTime ldt = LocalDateTime.now();
//		System.out.println(ldt);
		
		
	}
}
