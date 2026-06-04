package com.shinhan.day14;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 4. 오전 9:41:57 설명 : Student
 */
@AllArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode(of = "number") // 학번만
//@EqualsAndHashCode(exclude = "score") // 점수 빼고
public class Student implements Comparable<Student> {
	private String number;
	private String name;
	private int score;

	@Override
	public int compareTo(Student o) {
//		만일 비교값이 같으면 TreeSet에 들어가지 않음
//		학번순으로 Ascending(작은 값 -> 큰 값) => 나 - 비교 객체 = asc
//		return Integer.valueOf(this.number) - Integer.valueOf(o.getNumber());
		int numberResult = Integer.valueOf(this.number) - Integer.valueOf(o.getNumber());
		int nameResult = 0;
		int scoreResult = 0;
//		학번이 동일하다면
		if (numberResult == 0) {
			nameResult = name.compareTo(o.getName());
		}
//		이름까지 동일하다면 점수 비교
		if (nameResult == 0) {
			scoreResult = o.getScore() - score;
		}
		return numberResult == 0 ? nameResult == 0 ? scoreResult : nameResult : numberResult;
	}
}
