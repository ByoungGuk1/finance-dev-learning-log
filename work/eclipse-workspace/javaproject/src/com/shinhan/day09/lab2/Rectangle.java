package com.shinhan.day09.lab2;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 27. 오후 12:08:42
 * 설명			: Rectangle
 */
public class Rectangle extends Shape implements Resizable {
	private double width;
	private double height;
	
	public Rectangle() {
		super(4);
	}
	public Rectangle(double width, double height) {
		super(4);
		this.width = width;
		this.height = height;
	}

	@Override
	public double getArea() {
		double result = 0;
		result = width * height;
		return result;
	}
	@Override
	public double getPerimeter() {
		double result = 0;
		result = (width + height) * 2;
		return result;
	}
	
	@Override
	public void resize(double size) {
		width *= size;
		height *= size;
	}
}
