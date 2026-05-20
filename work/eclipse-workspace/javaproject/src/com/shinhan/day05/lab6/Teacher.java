package com.shinhan.day05.lab6;

public class Teacher {
	private String name;
	private int age;
	private String subject;
	
	public void print() {
		System.out.println("이름 : " + this.name + "\t나이 : " + this.age + "  \t담당과목 : " + this.subject );
	}
	
	public Teacher() {;}
	public Teacher(String name, int age, String subject) {
		this.name = name;
		this.age = age;
		this.subject = subject;
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
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
}
