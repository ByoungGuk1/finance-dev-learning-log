package com.shinhan.day05;

public class MethodExample {

	public static void main(String[] args) {
		Computer com = new Computer();
		System.out.println(com.add("a", "b"));
		System.out.println(com.add(100, 200));
		System.out.println(com.add(1.2, 1.5));
		
		System.out.println(com.add(1, 2, 3));
		System.out.println(com.add(1, 2, 3, 4));
		
		/*만약에 배열을 그냥 준다면*/
		int[] numbers = {1,2,3,4,5};
		System.out.println("매개변수로 배열 전달");
		System.out.println(com.add(numbers));
		/*실행가능*/	
	}

}
