package com.shinhan.day05;

public class Person {
//	class 변수 - 모든 object들이 공유한다.
//	Person 객체가 생성될 때마다 증가
	private static int numberOfPerson;	//	전체 인구수
//	instance변수 - object 생성시마다 만들어진다
	private int age;
	private String name;
	
	{
		name = "Anonymous";
		age = 12;
		numberOfPerson++;
	}
	
	public void selfIntroduce() {
		System.out.printf("내 이름은 %s이며, 나이는 %d살 입니다\n", name, age);
	}
	
	public int getPopulation() {
		return numberOfPerson;
	}
	
	public Person() {;}
	public Person(int age, String name) {
		this.age = age;
		this.name = name;
	}
	
	public static int getNumberOfPerson() {
		return numberOfPerson;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}
}
