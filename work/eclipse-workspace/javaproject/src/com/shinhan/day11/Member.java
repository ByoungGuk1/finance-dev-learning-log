package com.shinhan.day11;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 5. 29. 오전 11:03:42 설명 : Member
 */
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Member {
	private final String id;
	@NonNull
	private String name;
	private int age;
}
