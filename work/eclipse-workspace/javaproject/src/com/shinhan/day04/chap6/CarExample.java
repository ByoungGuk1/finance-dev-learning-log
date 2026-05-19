package com.shinhan.day04.chap6;

public class CarExample {
	
	public static void main(String[] args) {
		Car c1 = new Car();
		Car c2 = new Car("BCD", 30000);
		
		c1.model = "ABC";
		c1.price = 1000;

		c1.f_info();
		c2.f_info();
	}
}
