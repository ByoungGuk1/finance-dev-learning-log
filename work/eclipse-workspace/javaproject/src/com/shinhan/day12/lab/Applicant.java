package com.shinhan.day12.lab;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 1. 오후 2:48:01 설명 : Applicant
 */
@AllArgsConstructor
@ToString
public class Applicant<T> {
	T kind;
}
