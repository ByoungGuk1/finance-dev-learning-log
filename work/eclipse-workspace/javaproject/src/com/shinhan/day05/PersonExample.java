package com.shinhan.day05;

public class PersonExample {
	public static void main(String[] args) {
		Person p1 = new Person();
		Person p2 = new Person(20, "김길동");
		Person p3 = new Person(30, "박길동");
		
		p1.selfIntroduce();
		p2.selfIntroduce();
		p3.selfIntroduce();
		
		System.out.println(Person.getNumberOfPerson());
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
	}
}
