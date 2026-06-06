package com.shinhan.day15;

import java.util.Comparator;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 5. 오전 10:03:53 설명 : Self
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
class Num {
	int number;
}

public class Self {
	public static void main(String[] args) {
		List<Num> numList = List.of(new Num(1), new Num(51), new Num(12));

		numList.stream().sorted(Comparator.comparingInt(Num::getNumber)).forEach(System.out::println);
	}
}
