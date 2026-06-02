package com.shinhan.day12;

import java.util.HashSet;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 1. 오전 11:32:01 설명 : GenericExample
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
class OldBox {
	Object content;
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
class NewBox<T> {
//	타입이 안정적이다.
//	형 변환이 불필요하다.
	T content;
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
class Person {
	String name;
	int age;
}

public class GenericExample {
	public static void main(String[] args) {
//		f1();
//		f2();
		f3();
	}

	private static void f1() {
		OldBox box = new OldBox();
		box.setContent(100);
		int data1 = (Integer) box.getContent();

		box.setContent("신한");
		String data2 = (String) box.getContent();

		box.setContent(new Person("name", 2));
		Person data3 = (Person) box.getContent();

		System.out.println(data1);
		System.out.println(data2);
		System.out.println(data3);
	}

	private static void f2() {
		NewBox<String> box1 = new NewBox<>();
		box1.setContent("문자열");
		String data1 = box1.getContent();
		System.out.println(data1);

		NewBox<Integer> box2 = new NewBox<>();
		box2.setContent(1234);
		int data2 = box2.getContent();
		System.out.println(data2);

		NewBox<Person> box3 = new NewBox<>();
		box3.setContent(new Person("이름", 3));
		Person data3 = box3.getContent();
		System.out.println(data3);
	}

	private static void f3() {
		HashSet oldBox = new HashSet<>();
		oldBox.add(100);
		oldBox.add("문자열");
		oldBox.add(new Person("name", 123));

		for (Object content : oldBox) {
			if (content instanceof Integer i) {
				System.out.println(i);
			}
			if (content instanceof String s) {
				System.out.println(s);
			}
			if (content instanceof Person p) {
				System.out.println(p);
			}
		}

		HashSet<Person> newBox = new HashSet<>();
		newBox.add(new Person("name1", 1));
		newBox.add(new Person("name2", 2));
		newBox.add(new Person("name3", 3));
		for (Person content : newBox) {
			System.out.println(content);
		}
	}
}
