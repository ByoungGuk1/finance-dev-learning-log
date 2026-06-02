package com.shinhan.day12.review;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 2. 오전 9:03:38 설명 : Review
 */
// 제네릭 : 결정되지 않은 타입, 사용 시 결정
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
class Box<T> {
	T Data;
}

class BoxUtil {
//	method arg 에서 사용
	public static <T> void print1(Box<T> box) {
		System.out.println(box.getData());
	}

	public static void print2(Box<?> box) {
		System.out.println(box.getData());
	}

//	상한선을 제한
//	Number 포함 가능
	public static void print3(Box<? extends Number> box) {
		System.out.println(box.getData());
	}

//	하한선을 제한
//	Number 포함 가능
	public static void print4(Box<? super Number> box) { // 왜 오버로딩이 되지 않을까
		System.out.println(box.getData());
	}

//	method의 선언부
//	public static <? extends Number> T print5(Box<T> box) {}	// 선언부에서 ? 는 사용 불가
	public static <T extends Number> T print6(Box<T> box) {
		T value = box.getData();
		long v1 = value.longValue();
		System.out.println(v1);
		return value;
	}

//	인터페이스도 가능하다
	public static <T extends Comparable<T>> T print7(Box<T> box1, Box<T> box2) {
		T value1 = box1.getData();
		T value2 = box2.getData();
		T result = value1.compareTo(value2) >= 0 ? value1 : value2;
		return result;
	}
}

class Container<T> {
	T data;

	public T get() {
		return data;
	}

	public void set(T data) {
		this.data = data;
	}
}

@Getter
class Container1<K, V> {
	K key;
	V value;

	public void set(K key, V value) {
		this.key = key;
		this.value = value;
	}
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
class Pair<K, V> {
	K key;
	V value;
}

class ChildPair<K, V> extends Pair<K, V> {
	public ChildPair(K key, V value) {
		super(key, value);
	}
}

@AllArgsConstructor
class OtherPair<K, V> {
	K key;
	V value;
}

class Util {
	public static <K, V> V getValue(Pair<K, V> pair, K name) {
		return pair.key == name ? pair.value : null;
	}

	public static <P extends Pair<K, V>, K, V> V getValue1(P pair, K name) {
		return pair.key == name ? pair.value : null;
	}
}

public class Review {
	public static void main(String[] args) {
//		f1();
//		f2();
//		f3();
		f4();
	}

	private static void f4() {
		Pair<String, Integer> pair = new Pair<>("홍길동", 35);
		Integer age = Util.getValue(pair, "홍길동");
		System.out.println(age);

		ChildPair<String, Integer> childPair = new ChildPair<>("홍삼원", 20);
		Integer childAge = Util.getValue(childPair, "홍삼순");
		System.out.println(childAge);

//		OtherPair<String, Integer> otherPair = new OtherPair<>("홍삼원", 20);
//		// OtherPair는 Pair를 상속하지 않으므로 컴파일 에러가 발생
//		int otherAge = Util.getValue(otherPair, "홍삼원");
//		System.out.println(otherAge);
	}

	private static void f3() {
		Container1<String, String> container1 = new Container1<String, String>();
		container1.set("홍길동", "도적");
		String name1 = container1.getKey();
		String job = container1.getValue();
		Container1<String, Integer> container2 = new Container1<String, Integer>();
		container2.set("홍길동", 35);
		String name2 = container2.getKey();
		int age = container2.getValue();
		System.out.println(name1 + " " + job);
		System.out.println(name2 + " " + age);
	}

	private static void f2() {
		Container<String> container1 = new Container<String>();
		container1.set("홍길동");
		String str = container1.get();
		Container<Integer> container2 = new Container<Integer>();
		container2.set(6);
		int value = container2.get();
		System.out.println(str);
		System.out.println(value);
	}

	private static void f1() {
		Box<Long> box1 = new Box<>();
		box1.setData(100L);
		System.out.println(box1.getData());

		Box<Number> box2 = new Box<>(100);

		BoxUtil.print1(box1);
		BoxUtil.print2(box1);
		BoxUtil.print3(box2);
		BoxUtil.print4(box2);

		long boxResult = BoxUtil.print6(box1);
		System.out.println(boxResult);

		Box<Long> box3 = new Box<>();
		Long result = BoxUtil.print7(box1, box3);
		System.out.println(result);
	}
}
