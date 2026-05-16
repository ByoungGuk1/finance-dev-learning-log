package com.shinhan.day02;

public class VarialbeExample3 {
//	static 변수
//	필드의 경우 자동으로 초기화
//	정수 = 0, 실수 = 0.0, char는 공백, 객체는 null
	static String model = "ABC모델";
	static int price = 1000;

	public static void main(String[] args) {
		System.out.println("model" + model);
		System.out.println("price" + price);
		method1();
//		System.out.println(v1);	//	error

	}

	public static void method1() {
		System.out.println("method1-model" + model);
		System.out.println("method1-price" + price);

//		지역변수, 함수 호출시 생성되고 함수 종료시 제거된다.
//		block 내의 변수는 block 밖에서 접근 불가
		int v1 = 100;
		System.out.println(v1 + 200);
		if(v1 >= 100){
			char ch = 'A';
			System.out.println("지역변수: " + ch);
		}
//		System.out.println("지역변수: " + ch);	//	error

	}

}
