package com.shinhan.day12.checkQuest;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 1. 오후 3:44:26 설명 : Q2
 */
class Container<T> {
	T data;

	public void set(T data) {
		this.data = data;
	}

	public T get() {
		return data;
	}
}

public class Q2 {
	public static void main(String[] args) {
		ContainerExample.main(null);
	}

	static class ContainerExample {
		public static void main(String[] args) {
			Container<String> container1 = new Container<String>();
			container1.set("홍길동");
			String str = container1.get();
			Container<Integer> container2 = new Container<Integer>();
			container2.set(6);
			int value = container2.get();

			System.out.println(str);
			System.out.println(value);
		}
	}
}
