package com.one.q1;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 1. 오후 5:42:17 설명 : Product
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Product {
	private String productName;
	private int productPrice;

	public Product(String productName) {
		this.productName = productName;
	}
}
