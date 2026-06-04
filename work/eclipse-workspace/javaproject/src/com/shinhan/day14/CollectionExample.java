package com.shinhan.day14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 4. 오전 9:26:41 설명 : CollectionExample
 */
public class CollectionExample {
	public static void main(String[] args) {
		f1();

//		TreeSet
		f2();
		f3();
		f4();
		f5();
		f6();
	}

	private static void f1() {
//		자료구조
//		Collection
//			List
//				ArrayList
//				...
//			Set
//				HashSet
//				...
//		Map <= 별도
		List<Integer> data1 = new ArrayList<>();
		List<Integer> data2 = new Vector<>();
		List<Integer> data3 = new LinkedList<>();

		Set<Integer> data4 = new HashSet<>();

		Map<String, Integer> data5 = new HashMap<String, Integer>();
		Map<String, Integer> data6 = new Hashtable<String, Integer>();
		Properties data7 = new Properties();

		data1.add(100);
		data1.get(0);
		data2.add(100);
		data2.get(0);
		data3.add(100);
		data3.get(0);

		data4.add(100);

		data5.put("키", 100);
		data5.get("키");
		data6.put("키", 200);
		data6.get("키");
		data7.put("키", 300);
		data7.get("키");
	}

	private static void f2() {
//		TreeSet
		Set<Integer> datas = new HashSet<>();
		datas.add(100); // Auto Boxing : new Integer(100)
		datas.add(100);
		datas.add(100);
		datas.add(100);
		datas.add(100);

		for (Integer data : datas) {
//			Auto UnBoxing : aa.intValue();
			System.out.println(data);
		}
	}

	private static void f3() {
		Set<Student> datas = new HashSet<>();
		datas.add(new Student("1", "홍길동", 90));
		datas.add(new Student("1", "홍길동", 100)); // 중복체크
		datas.add(new Student("2", "홍길동", 90));
		datas.add(new Student("3", "홍길동", 90));
		datas.add(new Student("4", "홍길동", 90));

		for (Student data : datas) {
			System.out.println(data);
		}
	}

	private static void f4() {
//		HashSet 은 순서가 없다
//		Set<Integer> datas = new HashSet<>();

//		Integer <- compare 상속되어 비교 가능
//		TreeSet 은 순서가 생긴다 => root보다 작으면 왼쪽, 크면 오른쪽
//			=> 완전 이진트리
		Set<Integer> datas = new TreeSet<>();

		datas.add(100);
		datas.add(200);
		datas.add(400);
		datas.add(500);
		datas.add(300);

		for (Integer data : datas) {
			System.out.println(data);
		}
	}

	private static void f5() {
//		HashSet 은 순서가 없다
//		Set<String> datas = new HashSet<>();

//		String <- compare 상속되어 비교 가능
//		TreeSet 은 순서가 생긴다 => root보다 작으면 왼쪽, 크면 오른쪽
//			=> 완전 이진트리
		Set<String> datas = new TreeSet<>();

		datas.add("월");
		datas.add("화");
		datas.add("수");
		datas.add("목");
		datas.add("금");

		for (String data : datas) {
			System.out.println(data);
		}
	}

	private static void f6() {
//		HashSet 은 순서가 없다
//		Set<Student> datas = new HashSet<>();

//		compare 상속되지 않으면 TreeSet 사용 불가
//		TreeSet 은 순서가 생긴다 => root보다 작으면 왼쪽, 크면 오른쪽
//			=> 완전 이진트리
		Set<Student> datas = new TreeSet<>();

		datas.add(new Student("1", "홍길동", 90));
		datas.add(new Student("1", "홍길동", 100)); // 중복체크
		datas.add(new Student("2", "김길동", 90));
		datas.add(new Student("3", "이길동", 70));
		datas.add(new Student("1", "박길동", 80));

		for (Student data : datas) {
			System.out.println(data);
		}
	}
}
