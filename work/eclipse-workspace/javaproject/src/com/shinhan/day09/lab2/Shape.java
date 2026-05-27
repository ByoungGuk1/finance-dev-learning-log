package com.shinhan.day09.lab2;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 27. 오후 12:06:11
 * 설명			: Shape
 */
public abstract class Shape {
	private int numSides;

	public Shape() {;}
	public Shape(int numSides) {
		this.numSides = numSides;
	}
	
	public abstract double getArea();
	public abstract double getPerimeter();
	
	public int getNumSides() {
		return numSides;
	}
}
