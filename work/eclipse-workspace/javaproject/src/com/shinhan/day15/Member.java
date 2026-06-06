package com.shinhan.day15;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 5. 오후 12:13:07 설명 : Member
 */

@AllArgsConstructor
@ToString
@Getter
class Member implements Serializable {
	private static final long serialVersionUID = 1L;
	String name;
	String gender;
	int score;
//	직렬화시 무시 -> transient
	transient String password;

	public Member(String name, String gender, int score) {
		super();
		this.name = name;
		this.gender = gender;
		this.score = score;
	}
}
