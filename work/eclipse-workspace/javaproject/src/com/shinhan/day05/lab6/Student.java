package com.shinhan.day05.lab6;

public class Student {
	private String name;
	private int age;
	private int id;
	
	public void print() {
		System.out.println("이름 : " + this.name + "\t나이 : " + this.age + "  \t학번 : " + this.id );
	}
	
	public Student() {;}
	public Student(String name, int age, int id) {
		this.name = name;
		this.age = age;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
