package com.shinhan.day07.lab2;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 22. 오후 4:31:31
 * 설명			: Shape
 */
//	abstract class : 추상 메서드가 0개 이상 있다
//		추상 클래스는 객체 생성을 할 수 없다.
public abstract class Shape {
	private String color;
	private String type;
	
	public Shape() {}
	
	public Shape(String color, String type)
	{
		this.color = color;
		this.type = type;
	}
	
	public String getColor()
	{
		return color;
	}
	public String getType()
	{
		return type;
	}
	
//	abstract method : 정의는 있지만 구현은 상속받은 자식 클래스에서 구현한다.
//	추상 메서드가 한개라도 있다면, 반드시 추상 클래스이어야 한다.
	public abstract double calculateArea();
	
	public abstract double calculatePerimeter();
	
	public String toString()
	{
		return color + " " + type;
	}
	
}

