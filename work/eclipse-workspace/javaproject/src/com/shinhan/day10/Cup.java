package com.shinhan.day10;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 5. 28. 오후 12:09:19 설명 : Cup
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Cup {
	private String model;
	private String size;
	private int price;

}
