package com.one.q1;

import java.util.Arrays;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 1. 오후 5:30:48 설명 : Auction
 */
public class Auction {
	public static void main(String[] args) {
		int n = 4;
		int m = 4;
		int[] amounts = { 1_000_000, 490_000, 700_000, 290_000 };

		int[] result = new int[n];

		Person[] persons = new Person[m];

		for (int i = 0; i < persons.length; i++) {
			persons[i] = new Person(i + 1 + "번 참가자", amounts[i]);
		}

		Product[] products = new Product[n];
		for (int i = 0; i < products.length; i++) {
			products[i] = new Product(i + 1 + "번 물품");
		}

		for (Product product : products) {
			Person firstMember = new Person();
			Person secondMember = new Person();
			for (Person person : persons) {
				if (firstMember.getAmount() <= person.getAmount()) {
					secondMember = firstMember;
					firstMember = person;
				} else if (secondMember.getAmount() <= person.getAmount()) {
					secondMember = person;
				}
			}
			if (firstMember.getAmount() == 0 && secondMember.getAmount() == 0) {
				product.setProductPrice(0);
			} else {
				if (firstMember.getAmount() == secondMember.getAmount()) {
					product.setProductPrice(firstMember.getAmount());
				} else {
					product.setProductPrice(secondMember.getAmount() + 10_000);
				}
			}
			firstMember.trade(product);
			System.out.println(firstMember.getName() + "참가자가 " + product.getProductPrice() + "원에 "
					+ product.getProductName() + "을 낙찰받았습니다.");

			for (Person person : persons) {
				System.out.println(person);
			}
		}

		for (int i = 0; i < products.length; i++) {
			Product tmp = products[i];
			for (int j = i; j < products.length; j++) {
				if (tmp.getProductPrice() > products[j].getProductPrice()) {
					products[i] = products[j];
					products[j] = tmp;
				}
			}
		}
		for (int i = 0; i < result.length; i++) {
			result[i] = products[i].getProductPrice();
		}
		System.out.println(Arrays.toString(result));
	}
}
