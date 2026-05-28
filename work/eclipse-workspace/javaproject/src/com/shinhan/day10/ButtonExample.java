package com.shinhan.day10;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 5. 28. 오전 10:08:34 설명 : ButtonExample
 */
interface MyInterface2 {
	int add(int num1, int num2);
}

//	구현체 만들기
class MyInterface2Impl1 implements MyInterface2 {
	@Override
	public int add(int num1, int num2) {
		return num1 + num2;
	}
}

class MyInterface2Impl2 implements MyInterface2 {
	@Override
	public int add(int num1, int num2) {
		return num1 + num2 + 1;
	}
}

public class ButtonExample {
//	public static void main(String[] args) {
////		1. 구현체 사용
//		MyInterface2 a = new MyInterface2Impl1();
//		MyInterface2 b = new MyInterface2Impl2();
//		action(a);
//		action(b);
//
////		2. 익명 클래스 사용
//		action(new MyInterface2() {
//			@Override
//			public int add(int num1, int num2) {
//				return num1 + num2 + 2;
//			}
//		});
//
////		3. 람다식 사용
//		action((num1, num2) -> num1 + num2 + 3);
//	}
//
//	private static void action(MyInterface2 a) {
//		System.out.println(a.add(10, 20));
//	}

	public static void main(String[] args) {
		// 구현체 1
		class OkButtonImpl implements Button.ClickListener {
			@Override
			public void onClick() {
				System.out.println("-----------");
				System.out.println("Ok 버튼 클릭됨");
				System.out.println("-----------");
			}
		}

		// 구현체 2
		class CancleButtonImpl implements Button.ClickListener {
			@Override
			public void onClick() {
				System.out.println("---------------");
				System.out.println("Cancle 버튼 클릭됨");
				System.out.println("---------------");
			}
		}

//		1. 클래스를 구현해서 사용하는 방법
		call(new OkButtonImpl());
		call(new CancleButtonImpl());

//		2-1. 익명클래스를 사용해서 사용하는 방법
		call(new Button.ClickListener() {
			@Override
			public void onClick() {
				System.out.println("버튼이 클릭됨");
			}
		});
		call(new Button.ClickListener() {
			@Override
			public void onClick() {
				System.out.println("익명 구현 클래스 사용");
			}
		});
//		2-2. 람다식 사용 => 인터페이스에 메서드가 오직 한개만 있는 경우에만 사용가능
		call(() -> System.out.println("람다식 사용해서 버튼 클릭"));

	}

	static void call(Button.ClickListener listener) {
		Button button1 = new Button();

		button1.setClickListener(listener);
		button1.buttonClick();
	}
}
