package com.shinhan.day12.checkQuest;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 1. 오후 3:48:34 설명 : Q3
 */
class Container1<K, V> {
	K key;
	V value;

	public void set(K key, V value) {
		this.key = key;
		this.value = value;
	}

	K getKey() {
		return key;
	}

	V getValue() {
		return value;
	}
}

public class Q3 {
	public static void main(String[] args) {
		ContainerExample.main();
	}

	static class ContainerExample {
		public static void main() {
			Container1<String, String> container1 = new Container1<String, String>();
			container1.set("홍길동", "도적");
			String name1 = container1.getKey();
			String job = container1.getValue();

			System.out.println(name1);
			System.out.println(job);

			Container1<String, Integer> container2 = new Container1<String, Integer>();
			container2.set("홍길동", 35);
			String name2 = container2.getKey();
			int age = container2.getValue();

			System.out.println(name2);
			System.out.println(age);
		}
	}
}
