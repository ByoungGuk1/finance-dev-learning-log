package com.shinhan.day09.review;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 5. 28. 오전 9:06:26 설명 : InnerClassExample
 */
// field, constructor, method, inner class, block
class OuterClass {
	int score1 = 88;
	static int score2 = 99;

	void call() {
		String myName = "이름";
		class InnerClassC {
			int score1 = 1;
			static int score2 = 2;
//			myName = "";

			void f1() {
				System.out.println(myName);
//				메서드 내부에 있는 class > LocalClass
				int score1 = 100;
				int score2 = 200;
				System.out.println(getClass().getSimpleName() + " > instance method > local var (score1): " + score1);
				System.out.println(
						getClass().getSimpleName() + " > instance method > instance field (score1): " + this.score1);
				System.out.println(getClass().getSimpleName()
						+ " > instance method > OuterClass > instance field (score1): " + OuterClass.this.score1);
				System.out.println(getClass().getSimpleName() + " > instance method > local var (score2): " + score2);
				System.out.println(getClass().getSimpleName() + " > instance method > static field (score2): "
						+ InnerClassA.score2);
				System.out.println(getClass().getSimpleName()
						+ " > instance method > OuterClass > static field (score2): " + OuterClass.score2);
			}

			static void f2() {
				System.out.println("static method > static field (score2): " + score2);
			}
		}
		InnerClassC cc = new InnerClassC();
		cc.f1();
		InnerClassC.f2();
	}

	void f1() {
		int score1 = 100;
		int score2 = 200;
		System.out.println(getClass().getSimpleName() + " > instance method > local var (score1): " + score1);
		System.out.println(getClass().getSimpleName() + " > instance method > instance field (score1): " + this.score1);
		System.out.println(getClass().getSimpleName() + " > instance method > local var (score2): " + score2);
		System.out.println(
				getClass().getSimpleName() + " > instance method > static field (score2): " + OuterClass.score2);
	}

	static void f2() {
//		System.out.println("instance field (score1): " + score1);	//	static method 에서 instance var 접근 불가
		System.out.println("static method > static field (score2): " + score2);
	}

	class InnerClassA {
		int score1 = 1;
		static int score2 = 2;

		void f1() {
			int score1 = 100;
			int score2 = 200;
			System.out.println(getClass().getSimpleName() + " > instance method > local var (score1): " + score1);
			System.out.println(
					getClass().getSimpleName() + " > instance method > instance field (score1): " + this.score1);
			System.out.println(getClass().getSimpleName()
					+ " > instance method > OuterClass > instance field (score1): " + OuterClass.this.score1);
			System.out.println(getClass().getSimpleName() + " > instance method > local var (score2): " + score2);
			System.out.println(
					getClass().getSimpleName() + " > instance method > static field (score2): " + InnerClassA.score2);
			System.out.println(getClass().getSimpleName() + " > instance method > OuterClass > static field (score2): "
					+ OuterClass.score2);
		}

		static void f2() {
			System.out.println("static method > static field (score2): " + score2);
		}
	}

	static class InnerClassB {
		int score1 = 1;
		static int score2 = 2;

		void f1() {
			int score1 = 100;
			int score2 = 200;
			System.out.println(getClass().getSimpleName() + " > instance method > local var (score1): " + score1);
			System.out.println(
					getClass().getSimpleName() + " > instance method > instance field (score1): " + this.score1);
			System.out.println(getClass().getSimpleName() + " > instance method > local var (score2): " + score2);
			System.out.println(
					getClass().getSimpleName() + " > instance method > static field (score2): " + InnerClassB.score2);
			System.out.println(getClass().getSimpleName() + " > instance method > OuterClass > static field (score2): "
					+ OuterClass.score2);
		}

		static void f2() {
			System.out.println("static method > static field (score2): " + score2);
		}
	}
}

public class InnerClassExample {
	public static void main(String[] args) {
		method1();
		method2();
		method3();
		method4();
	}

	private static void method1() {
		new OuterClass().f1();
		OuterClass.f2();
	}

	// instance inner class method 실행
	private static void method2() {
		new OuterClass().new InnerClassA().f1();
//		OuterClass aa = new OuterClass();
//		OuterClass.InnerClassA bb = aa.new InnerClassA();
//		bb.f1();
		OuterClass.InnerClassA.f2();
	}

//	static inner class
	private static void method3() {
		OuterClass.InnerClassB aa = new OuterClass.InnerClassB();
		aa.f1();
	}

//	LocalClass
	private static void method4() {
		OuterClass aa = new OuterClass();
		aa.call();
	}
}
