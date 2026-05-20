package com.shinhan.day05;

public class Coffee {
//	field
	public static int count;
	private String menuName;
	private int price;
	
	{
		count++;
	}
	
//	constructor
	public Coffee() {
		super();
	}
	public Coffee(String menuName) {
		super();
		this.menuName = menuName;
	}
	public Coffee(String menuName, int price) {
		super();
		this.menuName = menuName;
		this.price = price;
	}

//	method
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public void print() {
		System.out.printf("주문하신 메뉴는 %s이며, 가격은 %d원입니다.\n", menuName, price);
	}
}
