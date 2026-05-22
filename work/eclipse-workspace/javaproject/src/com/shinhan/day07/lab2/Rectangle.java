package com.shinhan.day07.lab2;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 22. 오후 4:35:31
 * 설명			: Rectangle
 */
//public abstract class Shape {
//	private String color;
//	private String type;
//}

//s[1] = new Rectangle("RED", 5, 7);
public class Rectangle extends Shape {
	private double length;
	private double width;
	
		public Rectangle(String color, double length, double width) {
		super(color, "Rectangle");
		this.length = length;
		this.width = width;
	}

	@Override
	public double calculateArea() {
		return length * width;
	}

	@Override
	public double calculatePerimeter() {
		return (length + width) * 2;
	}

}
