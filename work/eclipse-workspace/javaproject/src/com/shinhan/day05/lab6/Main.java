package com.shinhan.day05.lab6;

public class Main {
	public static void main(String[] args) {
		Student st = new Student("홍길동", 20, 200201);
		Teacher te = new Teacher("이순신", 30, "JAVA");
		Employee em = new Employee("장보고", 25, "사원"	);
		
		st.print();
		te.print();
		em.print();
	}
}
