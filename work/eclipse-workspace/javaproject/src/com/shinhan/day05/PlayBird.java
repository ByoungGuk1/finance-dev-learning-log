package com.shinhan.day05;

public class PlayBird {
	public static void main(String[] args) {
		Duck d1 = new Duck("꽥꽥이", 2, 15);
		Sparrow s1 = new Sparrow("짹짹", 2, 10);
		
		d1.fly();
		d1.sing();
//		d1.setName("꽥꽥이1");
		System.out.println(d1 + "\n");
		
		s1.fly();
		s1.sing();
//		s1.setName("짹짹1");
		System.out.println(s1);
	}
}

class Duck {
	String name;
	int legs;
	int length;
	
	public void fly() {
		System.out.println("오리(" + name + ")는 날지 않습니다.");
	}
	public void sing() {
		System.out.println("오리(" + name + ")가  소리 내어 웁니다.");
	}
	
	public Duck() {;}
	public Duck(String name, int legs, int length) {
		this.name = name;
		this.legs = legs;
		this.length = length;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "오리의 이름은 " + name + " 입니다. 다리는 " + legs + "개이고 길이는 " + length + "입니다.";
	}
}

class Sparrow {
	String name;
	int legs;
	int length;
	
	public void fly() {
		System.out.println("참새(" + name + ")가 날아다닙니다.");
	}
	public void sing() {
		System.out.println("참새(" + name + ")가  소리 내어 웁니다.");
	}
	
	public Sparrow() {;}
	public Sparrow(String name, int legs, int length) {
		this.name = name;
		this.legs = legs;
		this.length = length;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "참새의 이름은 " + name + " 입니다. 다리는 " + legs + "개이고 길이는 " + length + "입니다.";
	}
}