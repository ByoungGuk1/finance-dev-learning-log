package com.shinhan.day09.lab2;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 27. 오후 12:14:31
 * 설명			: RectTriangle
 */
public class RectTriangle extends Shape{
	private double width;
	private double height;
	
	public RectTriangle() {
		super(3);
	}
	public RectTriangle(double width, double height) {
		super(3);
		this.width = width;
		this.height = height;
	}

	@Override
	public double getArea() {
		double result = 0;
		result = width * height / 2;
		return result;
	}
	@Override
	public double getPerimeter() {
		double result = 0;
		result = width + height + Math.sqrt(Math.pow(width, 2)+Math.pow(height, 2));
		return result;
	}
}
