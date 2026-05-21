package com.shinhan.day06;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * 작성자	: 송병국
 * 작성일	: 2026. 5. 21.
 * 설명	: PackageExample
 */
//	패키지 탐색 순서
//	현재 package > java.lang (import 없이 사용) > import 탐색 > 명시적으로 작성한 경로
public class PackageExample {
	public static void main(String[] args) {
		Date d1 = new Date();
		Long date = d1.getTime();
		java.sql.Date d2 = new java.sql.Date(date);
		System.out.println(d2);
		
		Calendar cal = Calendar.getInstance();
		System.out.println(cal);
		
		LocalDateTime dateTime = LocalDateTime.now();
		System.out.println(dateTime);
		
		String[] arr = dateTime.toString().split("T");
		for(String data : arr) {
			System.out.println(data);
		}
	}
}

//class Date{
//	public Date() {
//		System.out.println("만든 클래스");
//	}
//}
//
//class String{
//	String s1;
//
//	public String(String s1) {
//		this.s1 = s1;
//	}	
//}