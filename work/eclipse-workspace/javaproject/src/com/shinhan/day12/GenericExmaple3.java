package com.shinhan.day12;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 1. 오후 12:34:45 설명 : GenericExmaple3
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
class Box<K, S> {
	private K kind;
	private S size;
}

// 제네릭 메서드와 제네릭 클래스 차이 구분하기
// 제네릭 메서드
class BankUtil {
	<K, S> Box<String, Integer> boxing(K kind, S size) {
		Box<String, Integer> box = null;
//		box = new Box<>();
//		box.setKind("A");
//		box.setSize(100);
		if (kind instanceof String && size instanceof Integer) {
			box = new Box<>((String) kind, (Integer) size);
		}
		return box;
	}

	public <T> /* 매개변수에서 사용하는 제네릭의 위치는 반환타입 앞에 작성 */void swap(T[] arr, int i, int j) {
		T temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public <T> void printArray(String label, T[] arr) {
		System.out.println("[" + label + "]");
		for (T data : arr) {
			System.out.print(data + " ");
		}
		System.out.println();
	}

	public <T> boolean equals(T obj1, T obj2) {
		return obj1.equals(obj2);
	}

	public <K, V> String wrap(K key, V value) {
		return key + " ==> " + value;
	}
}

class BoxUtil {
	// augument있는 생성자
	public <K, S> Box<K, S> make1(K k, S s) {
		return new Box<K, S>(k, s);
	}

	// 기본생성자, setter
	public <K, S> Box<K, S> make2(K k, S s) {
		Box<K, S> box = new Box<K, S>();
		box.setKind(k);
		box.setSize(s);
		return box;
	}

	// 사용시 타입이 고정. 제네릭 의미 퇴색
	public <K, S> Box<String, Integer> make3(K k, S s) {
		Box<String, Integer> box = new Box<>();
		box.setKind("");
		box.setSize(0);
		return box;
	}
}

public class GenericExmaple3 {
	public static void main(String[] args) {
		BankUtil bu = new BankUtil();
		f1(bu);
		f2();
	}

	private static void f1(BankUtil bu) {
		Box<String, Integer> result = bu.boxing("A", 3);
		System.out.println(result);

		Integer[] arr = { 10, 20, 30, 40, 50 };
		System.out.println("before: " + Arrays.toString(arr));
		bu.swap(arr, 1, 2);
		System.out.println("after: " + Arrays.toString(arr));

		String[] arr2 = { "자바", "웹", "DB" };
		bu.printArray("제목", arr2);

		System.out.println(bu.equals(arr, arr2));
		System.out.println(bu.equals("123", "123"));
		System.out.println(bu.equals(new Product<String, String>("1", "2"), new Product<String, String>("1", "2")));

		String str = bu.wrap("key", 123);
		System.out.println(str);
		str = bu.wrap("color", "blue");
		System.out.println(str);
	}

	private static void f2() {
		BoxUtil boxUtil = new BoxUtil();
		System.out.println(boxUtil.make1("문자열", 1234));
		System.out.println(boxUtil.make2("문자열", 1234));
		System.out.println(boxUtil.make3("문자열", 1234));
	}
}
