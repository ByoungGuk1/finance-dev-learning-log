package com.shinhan.day11;

import java.util.ArrayList;
import java.util.HashSet;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 5. 29. 오전 10:14:44 설명 : ObjectExample
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@EqualsAndHashCode(of = { "model" })
//@EqualsAndHashCode(exclude = { "price" })
@EqualsAndHashCode
class Car {
	String model;
	int price;
}

public class ObjectExample {
	public static void main(String[] args) {
//		f1();
//		f2();
//		f3();
//		f4();
//		f5();
//		f6();
		f7();
	}

	private static void f1() {
		Object obj1 = new Object();
		Object obj2 = new Object();

		System.out.println(obj1);
		System.out.println(obj1.toString());
		System.out.println(obj1.hashCode());
		System.out.println();
		System.out.println(obj2);
		System.out.println(obj2.toString());
		System.out.println(obj2.hashCode());
		System.out.println();
		System.out.println(obj1.equals(obj2));
	}

	private static void f2() {
		String obj1 = new String("abc");
		String obj2 = new String("abc");

		System.out.println(obj1);
		System.out.println(obj1.toString());
		System.out.println(obj1.hashCode());
		System.out.println();
		System.out.println(obj2);
		System.out.println(obj2.toString());
		System.out.println(obj2.hashCode());
		System.out.println();
		System.out.println(obj1.equals(obj2));
	}

	private static void f3() {
		Car obj1 = new Car("abc", 100);
		Car obj2 = new Car("abc", 200);

		System.out.println(obj1);
		System.out.println(obj1.hashCode());
		System.out.println();
		System.out.println(obj2);
		System.out.println(obj2.hashCode());
		System.out.println();
		System.out.println(obj1.equals(obj2));
		System.out.println();
	}

	private static void f4() {
//		자료 구조 중 Collection : List, Set, Map
//			Set : 중복이 안된다, 순서가 없다 -> key로 사용
//				hashcode(), equals()를 이용해서 중복 체크
//			List : 중복 가능, 순서 있다
//		<> => 제네릭 문법
		HashSet<String> data = new HashSet<>();
		data.add("월요일");
		data.add("월요일");
		data.add("토요일");
		data.add("토요일");

		System.out.println(data.size());
		for (String s : data) {
			System.out.println(s);
		}

		ArrayList<String> arrList = new ArrayList<>();
		arrList.add("월요일");
		arrList.add("월요일");
		arrList.add("토요일");
		arrList.add("토요일");

		System.out.println(arrList.size());
		for (String s : arrList) {
			System.out.println(s);
		}

	}

	private static void f5() {
//		Car 클래스 정의 시 equals와 hashcode를 꼭 재정의 해야 Set을 원활하게 사용 가능하다
		HashSet<Car> data = new HashSet<Car>();

		data.add(new Car("ABC", 100));
		data.add(new Car("ABC", 100));
		data.add(new Car("ABC", 200));
		data.add(new Car("ABC", 200));

		System.out.println(data.size());
		for (Car car : data) {
			System.out.println(car);
		}
	}

	private static void f6() {
//		field 는 타입을 따라서 출력
//		method 는 타입을 통해서 보이면 instance를 따라간다. (오버라이딩)
		Object obj1 = new Car("123", 123);
		Object obj2 = new Car("123", 123);

		System.out.println(obj1);
		System.out.println(obj1.hashCode());
		System.out.println();
		System.out.println(obj2);
		System.out.println(obj2.hashCode());
		System.out.println();
		System.out.println(obj1.equals(obj2));
	}

	private static void f7() {
		Person p1 = new Person("김길동", 20);
		Person p2 = new Person("김길동", 20);

		System.out.println(p1);
		System.out.println(p1.hashCode());
		System.out.println();
		System.out.println(p2);
		System.out.println(p2.hashCode());
		System.out.println();
		System.out.println(p1.equals(p2));

		System.out.println(p1.age() + p1.name());
	}
}
