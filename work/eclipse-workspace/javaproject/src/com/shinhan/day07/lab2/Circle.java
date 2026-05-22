package com.shinhan.day07.lab2;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 22. 오후 4:35:24
 * 설명			: Circle
 */
//public abstract class Shape {
//	private String color;
//	private String type;
//}

//s[0] = new Circle("BLUE", 10);
//s[2] = new Circle("GREEN", 8);
public class Circle extends Shape {
	private double radius;
	private double circumference;
	
	public Circle(String color, double radius) {
		super(color, "Circle");
		this.radius = radius;
		circumference = 2 * Math.PI * radius;
	}

	public double getRadius() {
		return radius;
	}

	@Override
	public double calculateArea() {
		return Math.PI * radius * radius;
	}

	@Override
	public double calculatePerimeter() {
		return circumference;
	}

}
