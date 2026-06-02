package com.shinhan.day12;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 1. 오후 12:03:06 설명 : Product
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
// 제네릭 : 사용시 타입 결정, 영문자 대문자 (일반적으로)한글자로 사용
//		T,K,V : type, key, value 를 주로 사용
public class Product<K, M> {
	private K kind;
	private M model;
}
