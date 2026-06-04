package com.shinhan.day14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import lombok.ToString;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 4. 오전 11:38:23 설명 : CollectionExample3
 */
@ToString
class Fruit implements Comparable<Fruit> {
	int id;
	String name;
	int price;
	static int ids = 0;

	{
		id = ++ids;
	}

	@Override
	public int compareTo(Fruit o) {
		return price - o.price;
	}

	public Fruit(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}
}

public class CollectionExample3 {
	public static void main(String[] args) {
		method1();
		method2();

//		stack : Class
		method3();
//		Queue : Interface
		method4();

//		ArrayList 의 synchronized
		method5();

		method6();
	}

	private static void method1() {
		Fruit[] fruitArr = { new Fruit("apple", 1000), new Fruit("grape", 2000), new Fruit("melon", 5000),
				new Fruit("banana", 500) };
		System.out.println("---before---");
		for (Fruit fruit : fruitArr) {
			System.out.println(fruit);
		}

//		comparable 구현 필요
		System.out.println("\n---after(asc)---");
		Arrays.sort(fruitArr);
		for (Fruit fruit : fruitArr) {
			System.out.println(fruit);
		}

		System.out.println("\n---after(desc)---");
		Arrays.sort(fruitArr, new Comparator<Fruit>() {
			@Override
			public int compare(Fruit o1, Fruit o2) {
				return o2.price - o1.price;
			}
		});
		for (Fruit fruit : fruitArr) {
			System.out.println(fruit);
		}
	}

	private static void method2() {
		List<Fruit> fruitList = new ArrayList<>();

		fruitList.add(new Fruit("apple", 1000));
		fruitList.add(new Fruit("grape", 2000));
		fruitList.add(new Fruit("melon", 5000));
		fruitList.add(new Fruit("banana", 500));

		System.out.println("---before---");
		for (Fruit fruit : fruitList) {
			System.out.println(fruit);
		}

		fruitList.sort(new Comparator<Fruit>() {
			@Override
			public int compare(Fruit o1, Fruit o2) {
				return o1.price - o2.price;
			}
		});
		System.out.println("\n---after(asc)---");
		for (Fruit fruit : fruitList) {
			System.out.println(fruit);
		}
	}

	private static void method3() {
//		LIFO (Last Input First Out)
		Stack<String> strStack = new Stack<>();

		strStack.push("1번 자료");
		strStack.push("2번 자료");
		strStack.push("3번 자료");
		strStack.push("4번 자료");
		strStack.push("5번 자료");

		while (!strStack.empty()) {
			System.out.println(strStack.pop());
		}
	}

	private static void method4() {
//		FIFO (First Input First Out)
//		Queue<String> strQ = new LinkedList<>();
		LinkedList<String> strQ = new LinkedList<>();

		strQ.offer("1번 자료");
		strQ.offer("2번 자료");
		strQ.offer("3번 자료");
		strQ.offer("4번 자료");
		strQ.offer("5번 자료");

		while (!strQ.isEmpty()) {
			System.out.println(strQ.poll());
		}
	}

	private static void method5() {
//		List<Board> data = new ArrayList<>();

		List<Board> data = Collections.synchronizedList(new ArrayList<>());

		Thread t1 = new Thread() {
			public void run() {
				for (int i = 0; i < 1000; i++) {
					data.add(new Board("제목:" + (i + 1), "내용" + (i + 1), "작성자" + ((i + 1) % 10)));
				}
			}
		};
		Thread t2 = new Thread() {
			public void run() {
				for (int i = 1001; i <= 2000; i++) {
					data.add(new Board("제목:" + i, "내용" + i, "작성자" + i % 10));
				}
			}
		};
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(data.size() + "건");
	}

	private static void method6() {
		List<Integer> list = new ArrayList<>();
		list.add(40);
		list.add(1);

//		값 추가 불가능
//		of, copyof
//		List<Integer> intList = List.of(10, 200, 300, 40);
		List<Integer> intList = List.copyOf(list);
		Integer[] intArr = { 1, 2, 3, 4, 5 };
		intList = Arrays.asList(intArr);

//		intList.add(1);
//		intArr.add(1);

		for (Integer data : intList) {
			System.out.println(data);
		}
	}
}
