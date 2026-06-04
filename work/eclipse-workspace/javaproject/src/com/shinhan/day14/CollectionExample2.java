package com.shinhan.day14;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 4. 오전 10:34:00 설명 : CollectionExample2
 */
public class CollectionExample2 {
	public static void main(String[] args) {
//		set
		method1();

//		map
		method2();
		method3();

//		예제
		method4();
		method5();

//		배열의 정렬
		method6();
	}

	private static void method1() {
//		Set<Account> datas = new HashSet<>();
		Set<Account> datas = new TreeSet<>();

		for (int i = 0; i < 5; i++) {
			Account newMember = new Account("" + (i + 1), "userName" + i, 10000 - i * 1000);
			datas.add(newMember);
		}

		datas.add(new Account("3", "userName8", 6000));
		datas.add(new Account("2", "userName8", 6000));

		for (Account member : datas) {
			System.out.println(member);
		}
	}

	private static void method2() {
//		Map<String, Integer> datas = new HashMap<>();
//		key 를 기준으로 정렬
		Map<String, Integer> datas = new TreeMap<>();

		datas.put("A", 100);
		datas.put("B", 300);
		datas.put("D", 200);
		datas.put("e", 400);
		datas.put("K", 600);

		for (String key : datas.keySet()) {
			System.out.println(key + " : " + datas.get(key));
		}
	}

	private static void method3() {
//		Map<Account, Integer> datas = new HashMap<>();
//		key 를 기준으로 정렬
		Map<Account, Integer> datas = new TreeMap<>();

		datas.put(new Account("1", "user1", 1000), 100);
		datas.put(new Account("1", "user1", 1000), 100);
		datas.put(new Account("4", "user1", 1000), 100);
		datas.put(new Account("2", "user4", 1000), 100);
		datas.put(new Account("7", "user6", 2000), 100);

		for (Account key : datas.keySet()) {
			System.out.println(key + " : " + datas.get(key));
		}
	}

	private static void method4() {
		Set<Integer> datas = new TreeSet<>();

		datas.add(100);
		datas.add(200);
		datas.add(400);
		datas.add(300);
		datas.add(500);

		for (Integer data : datas) {
			System.out.println(data);
		}

		TreeSet<Integer> treeSet = (TreeSet<Integer>) datas;

		System.out.println("first : " + treeSet.first());
		System.out.println("last : " + treeSet.last());

		System.out.println("lower(250) : " + treeSet.lower(250));
		System.out.println("ceiling(330) : " + treeSet.ceiling(330));

		NavigableSet<Integer> descTreeSet = treeSet.descendingSet();

		for (Integer data : descTreeSet) {
			System.out.println(data);
		}
	}

	private static void method5() {
		Map<String, Integer> mapDatas = new TreeMap<>();

		mapDatas.put("E", 20);
		mapDatas.put("f", 30);
		mapDatas.put("D", 40);
		mapDatas.put("E", 60);
		mapDatas.put("K", 50);

		for (Map.Entry<String, Integer> entry : mapDatas.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}

		System.out.println("---");
		TreeMap<String, Integer> treeMapDatas = (TreeMap<String, Integer>) mapDatas;

		Map.Entry<String, Integer> entry = treeMapDatas.firstEntry();
		System.out.println(entry.getKey() + " : " + entry.getValue());

		NavigableSet<String> descMap = treeMapDatas.descendingKeySet();

		for (String key : descMap) {
			System.out.println(key);
		}
	}

	private static void method6() {
		Integer[] intArr = { 100, 80, 60, 70, 30 };
		String[] strArr = { "b", "c", "a" };
		Account[] accountArr = { new Account("1", "name", 200), new Account("2", "name2", 100),
				new Account("0", "name0", 500), };

		System.out.println("before : " + Arrays.toString(intArr));
		System.out.println("before : " + Arrays.toString(strArr));
		System.out.println("before : " + Arrays.toString(accountArr));

//		default : asc
		System.out.println("===asc===");
		Arrays.sort(intArr);
		System.out.println("asc after : " + Arrays.toString(intArr));
		Arrays.sort(strArr);
		System.out.println("asc after : " + Arrays.toString(strArr));
		Arrays.sort(accountArr);
		System.out.println("asc after : " + Arrays.toString(accountArr));

//		desc
		System.out.println("===desc===");
//		기본 형은 안됨, 참조형 변수로 사용
		Arrays.sort(intArr, new ComparatorImplA());
		System.out.println("desc after : " + Arrays.toString(intArr));
		Arrays.sort(strArr, new ComparatorImplB());
		System.out.println("desc after : " + Arrays.toString(strArr));
		Arrays.sort(accountArr, new Comparator<Account>() {
			@Override
			public int compare(Account o1, Account o2) {
				return o2.getBalance() - o1.getBalance();
			}
		});
		System.out.println("desc after : " + Arrays.toString(accountArr));
	}
}
