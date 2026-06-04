package com.shinhan.day14;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 4. 오후 2:03:18 설명 : LamdaExample2
 */
interface Calculable {
	double calc(double a, double b);
}

@FunctionalInterface
interface Accountable {
	Account makeAccount(String accNo, String ownerName, int balance);
}

class CalculableImpl implements Calculable {
	@Override
	public double calc(double a, double b) {
		return a + b;
	}
}

class Person {
	public void action(Calculable calculable) {
		double result = calculable.calc(10, 4);
		System.out.println(result);
	}

	public Account makeAccount(Accountable acc) {
		return acc.makeAccount("1", "name", 10_000);
	}
}

class Computer {
	public static double staticMethod(double a, double b) {
		return (double) a + b;
	}

	public double instanceMethod(double a, double b) {
		return (double) a * b;
	}
}

public class LambdaExample2 {
	String field = "클래스 변수";

	public static void main(String[] args) {
		new LambdaExample2().f1();
		f2();
		f3();

		f4();
	}

	private void f1() {
//		String field = "로컬 변수";

		Calculable v1 = new CalculableImpl();
		Calculable v2 = new Calculable() {
			String field = "구현체 클래스 변수";

			@Override
			public double calc(double a, double b) {
				String field = "구현체 로컬 변수";
				System.out.println(field);
				System.out.println(this.field);
//				로컬변수를 호출하는 방법 ??
//				System.out.println(super);
				System.out.println(LambdaExample2.this.field);
				return a + b;
			}
		};
		Calculable v3 = (a, b) -> a + b;

		System.out.println(v1.calc(1, 2));
		System.out.println(v2.calc(3, 4));
		System.out.println(v3.calc(5, 6));
	}

	private static void f2() {
		double result1 = action((a, b) -> a + b, 10, 20);
		double result2 = action((a, b) -> {
			return a - b > 0 ? a : b;
		}, 10, 20);

		System.out.println(result1);
		System.out.println(result2);

		double simpleResult2_1 = action((a, b) -> Math.max(a, b), 10, 20);
		System.out.println(simpleResult2_1);
		double simpleResult2_2 = action(Math::max, 10, 20);
		System.out.println(simpleResult2_2);
	}

	private static double action(Calculable object, int a, int b) {
		return object.calc(a, b);
	}

	private static void f3() {
		Person p = new Person();
		p.action(Computer::staticMethod);
		Computer c = new Computer();
		p.action(c::instanceMethod);
	}

	private static void f4() {
		Person p = new Person();
		Account acc = p.makeAccount(Account::new);
		System.out.println(acc);
	}
}
