package com.shinhan.day14;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 4. 오후 3:06:34 설명 : StreamExample
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
class Product {
	private int productNo;
	private String name;
	private String company;
	private int price;
}

public class StreamExample {
	public static void main(String[] args) {
//		iterator 사용 => 외부 반복자 사용
		f1();

//		stream 사용 => 내부 반복자 사용 + 병렬처리
		f2();

		f3();

//		intstream
		f4();

		f5();
		f6();

//		stream.map
		f7();
		f8();

		f9();

//		펼치기
		f10();
	}

	private static void f1() {
		List<Integer> arrList = List.of(100, 200, 300);

//		iterator 사용 => 외부 반복자 사용
		for (Integer data : arrList) {
			System.out.println(data + " 가져오기 완료");
		}
	}

	private static void f2() {
		List<Integer> arrList = List.of(100, 200, 300);

//		stream 사용 => 내부 반복자 사용 + 병렬처리
//		arrList.stream().forEach(System.out::println);
		arrList.parallelStream().map(data -> data + " 진행중").forEach(System.out::println);
	}

	private static void f3() {
		int[] intArr = { 1, 2, 3, 4, 5 };
		List<Product> arrList = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Product p = new Product(i + 1, "product" + i, "name", 1000 + (i * 3));
			arrList.add(p);
		}

		arrList.stream().forEach(System.out::println);

		Arrays.stream(intArr).forEach(System.out::println);
	}

	private static void f4() {
		IntStream.rangeClosed(1, 10).forEach(System.out::println);
	}

	private static void f5() {
		URI uri = null;
		try {
			uri = StreamExample.class.getResource("data.txt").toURI();
		} catch (URISyntaxException e) {
			System.err.println("URI Error : " + e.getMessage());
		}
		if (uri == null)
			return;
		Path path = Paths.get(uri);
		try {
			Files.lines(path).forEach(System.out::println);
		} catch (IOException e) {
			System.err.println("ioe : " + e.getMessage());
		}
	}

	private static void f6() {
		Integer[] arr = { 100, 20, 90, 60, 100, 30, 20, 90, 60, 100, 30, 20, 90, 60, 100, 30, 20, 90, 60, 100, 30 };
		Arrays.stream(arr).distinct().sorted(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		}).forEach(System.out::println);

		List<String> strArr = List.of("이름3", "이름2", "이름3", "이름3", "이름1", "이름2", "이름1", "이름2");
		strArr.stream().filter((data) -> data.contains("2") || data.contains("3")).distinct().sorted()
				.map(data -> "이름은 " + data).forEach(System.out::println);
	}

	private static void f7() {
//		map : 들어간 객체의 구조가 변화하여 나온 스트림
		List<Product> productList = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			Product p = new Product(i + 1, "product" + i, "name", 1000 + ((i + 1) * 5));
			productList.add(p);
		}

		productList.stream() // original stream
				.map((data) -> data.getPrice()) // 중간 stream
				.forEach(System.out::println); // final stream
		productList.stream().map((data) -> data.getName() + "님").forEach(System.out::println);
	}

	private static void f8() {
		List<Student> studentList = List.of(new Student("1", "name", 90), new Student("2", "nameA", 80),
				new Student("3", "nameB", 60), new Student("4", "nameC", 100));
		List<Student> nullList = new ArrayList<>();

		System.out.println(studentList.stream().mapToInt(data -> data.getScore()).sum());

		System.out.println(studentList.stream().mapToDouble(data -> data.getScore()).average().orElse(0));

		nullList.stream() // original
				.mapToDouble(data -> data.getScore()) // 중간 스트림
				.average() // 최종
				.ifPresentOrElse(System.out::println, () -> { // 옵셔널 처리
					System.err.println("null");
				});
	}

	private static void f9() {
		int[] intArray = { 1, 2, 3, 4, 5 };
		Arrays.stream(intArray).boxed().mapToDouble(data -> data).forEach(System.out::println);

	}

	private static void f10() {
		List<String> strList = List.of("This is Java", "I'm a best developer");

		strList.stream().flatMap((statement) -> Arrays.stream(statement.split(" ")).map((data) -> data + "."))
				.forEach(System.out::println);
	}
}
