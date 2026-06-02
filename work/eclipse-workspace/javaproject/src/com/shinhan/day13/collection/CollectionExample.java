package com.shinhan.day13.collection;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 2. 오후 3:20:27 설명 : CollectionExample
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
class Board {
	private String title;
	private String contents;
	private String writer;
}

//	Collection
//		List : 순서가 있다, 중복이 가능
//			구현 class : ArrayList, Vector, LinkedList
//		Set : 순서가 없다, 중복 불가능
//			구현 Class : HashSet, TreeSet(비교하고 add -> 순서 존재)
//		Map : key 와 value (Entry) 형태로 저장 / key -> 중복 불가능 (Set)
//			구현 Class : HashMap, HashTable, TreeMap
public class CollectionExample {
	public static void main(String[] args) {
//		List (single thread)
//			ArrayList
		f1();
//			Vector -> 다중 스레드 상황에 안정적(내부적으로 synchronized 적용)
		f2();
//			LinkedList
		f3();

//		List (multi thread)
		f4();

//		삽입 위치에 따른 속도 비교
		f5();

//		Set
//		HashSet
		f6();
		f7();
//			읽는 방법
//				1. 향상된 for문
//				2. 반복자
		f8();
//		map (HashMap)
		f9();
//		Hashtable <- 멀티쓰레드 환경에서 이점을 가진다.
		fA();

//		Properties
		f10();
	}

	private static <T> void printList(List<T> list) {
		System.out.println("---List---");
		System.out.println("size : " + list.size());
		System.out.print("[");
		for (T data : list) {
			System.out.print(data.toString() + ", ");
		}
		System.out.println("]");
	}

	private static void f1() {
//		1. List
//			1-1. ArrayList
		ArrayList<String> data = new ArrayList<>();
		data.add("1번 자료");
		data.add("2번 자료");
		data.add("3번 자료");
		data.add("1번 자료");
		printList(data);
	}

	private static void f2() {
//		1. List
//			1-2. Vector
		Vector<String> data = new Vector<>();
		data.add("1번 자료");
		data.add("2번 자료");
		data.add("3번 자료");
		data.add("1번 자료");
		printList(data);
	}

	private static void f3() {
//		1. List
//			1-2. LinkedList
		LinkedList<String> data = new LinkedList<>();
		data.add("1번 자료");
		data.add("2번 자료");
		data.add("3번 자료");
		data.add("1번 자료");
		printList(data);
	}

	private static void f4() {
		List<Board> data = new Vector<>();

		Thread t1 = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					data.add(new Board("제목" + i, "" + i, "tester" + (i % 10)));
				}
			}
		};

		Thread t2 = new Thread() {
			@Override
			public void run() {
				for (int i = 1000; i < 2000; i++) {
					data.add(new Board("제목" + i, "" + i, "tester" + (i % 10 + 10)));
				}
			}
		};

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			System.err.println("CollectionExample.f4() : " + e.getMessage());
		}
		System.out.println("data.size : " + data.size());
	}

	private static void f5() {
		List<String> data1 = new ArrayList<>();
		List<String> data2 = new LinkedList<>();

		long startTime;
		long endTime;

//		ArrayList => 뒤로 밀어내면서 시간이 지체된다.
		startTime = System.nanoTime();
		for (int i = 0; i < 100_000; i++) {
			data1.add(0, String.valueOf(i));
		}
		endTime = System.nanoTime();
		System.out.println("ArrayList runtime : " + (endTime - startTime) + "ns");

//		LinkedList => 주소 저장때문에 공간을 좀 더 차지함
		startTime = System.nanoTime();
		for (int i = 0; i < 100_000; i++) {
			data2.add(0, String.valueOf(i));
		}
		endTime = System.nanoTime();
		System.out.println("LinkedList runtime : " + (endTime - startTime) + "ns");
	}

	private static <T> void printSet(Set<T> datas) {
//		중복 X, 순서 X
		System.out.println("---Set---");
		System.out.println("size : " + datas.size());
		System.out.print("[");
		for (T data : datas) {
			System.out.print(data.toString() + " ");
		}
		System.out.println("]");
	}

	private static void f6() {
//		1. Set
//			1-1. HashSet
		HashSet<String> data = new HashSet<>();
		data.add("1번 자료");
		data.add("2번 자료");
		data.add("3번 자료");
		data.add("1번 자료");
		printSet(data);
	}

	private static void f7() {
		HashSet<Board> data = new HashSet<>();
		for (int i = 0; i < 5; i++) {
			data.add(new Board("1", "2", "3"));
		}
		data.add(new Board("2", "2", "3"));
		data.add(new Board("3", "2", "3"));
		printSet(data);
	}

	private static void f8() {
		HashSet<Board> data = new HashSet<>();
		for (int i = 0; i < 5; i++) {
			data.add(new Board("1", "2", "3"));
		}
		data.add(new Board("2", "2", "3"));
		data.add(new Board("3", "2", "3"));
//		읽는 방법
//			1. 향상된 for문
		System.out.println("향상된 for 문");
		for (Board b : data) {
			System.out.println(b);
		}
//			2. 반복자
		System.out.println("반복자 사용");
		Iterator<Board> it = data.iterator();
		while (it.hasNext()) {
			Board b = it.next();
			System.out.println(b);
		}
	}

	private static void f9() {
//		Map, dict-python, Object-JS
//		Map은 key와 value 한 상의 Entry로 구성
//		키가 동일하다면 덮어쓰기 적용
		Map<String, Integer> data = new HashMap<>();

		data.put("홍길동", 100);
		data.put("김길동", 10);
		data.put("이길동", 90);
		data.put("박길동", 80);
		data.put("최길동", 70);

		System.out.println(data.get("홍길동"));

		Set<String> keys = data.keySet();
		for (String key : keys) {
			System.out.println(key);
		}

		Set<Entry<String, Integer>> entries = data.entrySet();
		for (Entry<String, Integer> entry : entries) {
			String key = entry.getKey();
			Integer value = entry.getValue();
			System.out.println("Key : " + key + "\tValue : " + value);
		}
	}

	private static void fA() {
//		Map, dict-python, Object-JS
//		Map은 key와 value 한 상의 Entry로 구성
//		키가 동일하다면 덮어쓰기 적용
//		Map - interface <-- 구현 클래스 : HashMap, Hashtable
		Map<String, Integer> data = new Hashtable<>();

		data.put("홍길동", 100);
		data.put("김길동", 10);
		data.put("이길동", 90);
		data.put("박길동", 80);
		data.put("최길동", 70);

		System.out.println(data.get("홍길동"));

		Set<String> keys = data.keySet();
		for (String key : keys) {
			System.out.println(key);
		}

		Set<Entry<String, Integer>> entries = data.entrySet();
		for (Entry<String, Integer> entry : entries) {
			String key = entry.getKey();
			Integer value = entry.getValue();
			System.out.println("Key : " + key + "\tValue : " + value);
		}
	}

	private static void f10() {
//		키 = 값의 쌍이 문자로 구성
//		키와 값을 String 타입으로 제한한 컬렉션
		Properties properties = new Properties();
		InputStream is = CollectionExample.class.getResourceAsStream("oracle.properties");
		try {
			properties.load(is);
		} catch (IOException e) {
			System.err.println("err CollectionExample.f10() : " + e.getMessage());
		}
		String uid = properties.getProperty("userid");
		String password = properties.getProperty("password");
		String url = properties.getProperty("url");
		String driver = properties.getProperty("driver");
		String db = properties.getProperty("db");

		System.out.println(uid);
		System.out.println(password);
		System.out.println(url);
		System.out.println(driver);
		System.out.println(db);
	}
}
