package com.shinhan.day05;

public class Car /*extends Object*/{	//	상속받으면 부모의 field 와 method 를 사용 가능
//	부모가 물려준 것을 재정의 가능 (Override =덮어쓰기)
//		조건 :
//			1. 이름이 동일
//			2. 리턴타입이 동일
//			3. 매개변수이 동일
//			4. 접근지정자는 같거나 범위가 더 넓어야한다.
//				public > protected > 생략 > private
	private String model;
	private int price;
	
	public Car() {;}
	public Car(String model) {
		this.model = model;
	}
	public Car(int price) {
		this.price = price;
	}
	public Car(String model, int price) {
		this.model = model;
		this.price = price;
	}

	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Car [model=" + model + ", price=" + price + "]";
	}
}
