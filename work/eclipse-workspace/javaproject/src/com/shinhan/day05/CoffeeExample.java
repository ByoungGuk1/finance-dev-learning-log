package com.shinhan.day05;

public class CoffeeExample {
	public static void main(String[] args) {
		Coffee c1 = new Coffee();
		Coffee c2 = new Coffee("아메리카노");
		Coffee c3 = new Coffee("카페라떼", 4000);
		
		c1.setMenuName("말차라떼");
		c1.setPrice(6000);
		
		c2.setPrice(3000);
		
		System.out.println(c3.getMenuName());
		
		c1.print();
		c2.print();
		c3.print();
		
		System.out.printf("주문하신 커피는 %d잔 입니다\n", Coffee.count);
	}
}
