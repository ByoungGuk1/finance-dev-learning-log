package com.one.q1;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 1. 오후 5:27:59 설명 : Person
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Person {
	private String name;
	private int amount;
	private Product[] products;
	private int productsIndex;

	{
		products = new Product[1];
	}

	public Person(int amount) {
		super();
		this.amount = amount;
	}

	public Person(String name, int amount) {
		super();
		this.name = name;
		this.amount = amount;
	}

	public void trade(Product product) {
		if (this.amount < product.getProductPrice()) {
			System.out.println("잔액부족");
			return;
		}
		this.amount -= product.getProductPrice();
		Product[] newProducts = new Product[productsIndex + 1];
		newProducts[productsIndex++] = product;
		this.products = newProducts;
	}
}
