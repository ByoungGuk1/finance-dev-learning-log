package com.shinhan.day15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 5. 오전 9:05:43 설명 : Day14Rreview
 */
@AllArgsConstructor
@ToString
@Getter
class Student implements Comparable<Student> {
	private String name;
	private List<Integer> score;

	@Override
	public int compareTo(Student o) {
		// 0번 인덱스의 값 decs
//		return o.getScore().get(0) - score.get(0);	

		// score의 합 desc
//		return o.getScore().stream().mapToInt(Integer::intValue).sum()
//				- score.stream().mapToInt(Integer::intValue).sum();

//		이름 순 asc
		return name.compareTo(o.getName());
	}
}

public class Day14Review {
	static List<Student> stdList = List.of( //
			new Student("BB", List.of(4, 5, 6)), //
			new Student("AA", List.of(9, 2, 3)), //
			new Student("CC", List.of(7, 8, 9)) //
	);

	static List<Member> memList = new ArrayList<>();
	static {
		memList.add(new Member("A", "남자", 50));
		memList.add(new Member("B", "여자", 60));
		memList.add(new Member("C", "여자", 70));
		memList.add(new Member("D", "남자", 80));
		memList.add(new Member("E", "남자", 90));
	}

	public static void main(String[] args) {
		f1();
		f2();
		f3();
		f4();
		f5();
		f6();
		f7();
		f8();
	}

	private static void f1() {
////		stream의 표현 방법
////		v1 / 익명 구현 클래스 이용
//		stdList.stream() // 내부 반복자, stream은 data의 흐름
//				.forEach(new Consumer<Student>() {
//					@Override
//					public void accept(Student t) {
//						System.out.println(t);
//					}
//				});
//
////		v2 / 람다식 표현
//		stdList.stream() // Original Stream
//				.filter((stdData) -> {
//					return stdData.getName().length() >= 2;
//				}) // 중간 stream
//				.mapToInt((std) -> {
//					return std.getScore();
//				}) // 중간 stream
//				.forEach((data) -> {
//					System.out.println(data);
//				}); // final Stream
//
////		v3 / 참조형 표현
//		stdList.stream() //
//				.filter(stdData -> stdData.getName().length() >= 2) //
//				.mapToInt(Student::getScore) //
//				.forEach(System.out::println);

//		응용
		int stdSum = stdList.stream() // Original Stream
				.filter(stdData -> stdData.getName().length() >= 2) // 중간 stream
				.flatMap((s) -> s.getScore().stream()) // 중간 stream
				.mapToInt(Integer::intValue) //
				.sum();
		System.out.println(stdSum);
	}

	private static void f2() {
		List<String> stdNameList = stdList.stream() //
				.sorted((a, b) -> b.getName().compareTo(a.getName())) //
				.peek(System.out::println) //
				.map(Student::getName) //
				.toList();
		System.out.println(stdNameList);
	}

	private static void f3() {
		int[] intArr = { 2, 4, 6 };

		boolean result1 = Arrays.stream(intArr).allMatch(a -> a % 2 == 0);
		System.out.println("모두 짝수 인가요 : " + result1);

		boolean result2 = Arrays.stream(intArr).anyMatch(a -> a % 2 == 0);
		System.out.println("하나라도 짝수 인가요 : " + result2);

		boolean result3 = Arrays.stream(intArr).noneMatch(a -> a % 2 == 0);
		System.out.println("모두 짝수가 아닌가요 : " + result3);
	}

	private static void f4() {
		int[] intArr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		intArr = new int[2];
//		Optional은 값이 없는 경우 오류
		OptionalDouble opd = Arrays.stream(intArr).average();
		opd.ifPresentOrElse(System.out::println, () -> {
			System.out.println("에러");
		});
	}

	private static void f5() {
		List<Integer> list = new ArrayList<Integer>();
		OptionalDouble opd = list.stream().mapToInt(Integer::valueOf).average();
		System.out.println(opd);

		opd.ifPresent(System.out::println);
	}

	private static void f6() {
		List<Member> manList = memList.stream().filter(m -> m.getGender().equals("남자")).peek(System.out::println)
				.toList();
		manList.stream().forEach(System.out::println);

		Map<String, Integer> newMap = memList.stream().collect(Collectors.toMap(Member::getName, Member::getScore));
		System.out.println(newMap);
	}

	private static void f7() {
		Map<String, List<Member>> groupMember = memList.stream().collect(Collectors.groupingBy(Member::getGender));

		groupMember.keySet().forEach(data -> {
			System.out.println(data);
			groupMember.get(data).stream().forEach(System.out::println);
		});
	}

	private static void f8() {
		Random random = new Random();
		long startTime = 0;
		long endTime = 0;
		double avg;
		List<Integer> scores = new ArrayList<Integer>();
		IntStream.rangeClosed(0, 100000000).forEach(i -> {
			scores.add(random.nextInt(1001));
		});

//		외부 반복자
		startTime = System.nanoTime();
		int sum = 0;
		for (int num : scores) {
			sum += num;
		}
		avg = (double) sum / scores.size();
		System.out.println("계산 결과 avg : " + avg);

		endTime = System.nanoTime();
		System.out.println("일반 for문 소요 시간 : " + (endTime - startTime) + "ns");

//		일반 스트림
		startTime = System.nanoTime();

		avg = scores.stream().mapToInt(Integer::intValue).average().orElse(0);
		System.out.println("계산 결과 avg : " + avg);

		endTime = System.nanoTime();
		System.out.println("일반 스트림 소요 시간 : " + (endTime - startTime) + "ns");

//		병렬처리 스트림
		startTime = System.nanoTime();

		avg = scores.parallelStream().mapToInt(Integer::intValue).average().orElse(0);
		System.out.println("계산 결과 avg : " + avg);

		endTime = System.nanoTime();
		System.out.println("병렬 스트림 소요 시간 : " + (endTime - startTime) + "ns");
	}
}
