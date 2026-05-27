package com.shinhan.day09.lab6;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 27. 오후 3:40:56
 * 설명			: OuterClass
 */
public class OuterClass {
//	1. field
	String message = "외곽 클래스의 필드";
//	2. constructor
//	public OuterClass() {
//		System.out.println("==OuterClass constructor start==");
//		class LocalClassA{	//	로컬 클래스는 static 사용 불가 -> 메모리에 먼저 올라갈 수가 없어서
//			int score = 100;
//			String message = "이너 클래스 중 로컬 클래스의 필드";
//			static int a = 1;
//			void localMethod() {
//				String message = "메서드의 지역변수";
//				System.out.println("[LocalClassA] message: " + message);
//				System.out.println("[LocalClassA] this.message: " + this.message);
//				System.out.println("[LocalClassA] OuterClass.this.message: " + OuterClass.this.message);
//			}
//		}
//		LocalClassA aa = new LocalClassA();
//		System.out.println(aa.score);
//		System.out.println(LocalClassA.a);
//		System.out.println("LocalClassA의 필드 접근 : " + aa.message);
//		System.out.println("LocalClassA.localMethod() -> message: ");
//		System.out.println("LocalClassA.localMethod() -> message: ");
//		aa.localMethod();
//		System.out.println("OuterClass의 필드 접근 : " + message);
//		System.out.println("==OuterClass constructor end==");
//	}
//	3. method
//	Local class 에서 지역변수를 사용하면 -> 지역변수는 무조건 final (!!)
	public void f1() {
		int score = 10;
		System.out.println("==OuterClass.f1() start==");
		class LocalClassB{
			int score = 200;
			void method1() {	//	-> inner local class에서 method의 지역변수 사용 시 지역변수에 자동으로 final 적용
//				score += 10;
//				System.out.println("[innerClass]localClassB > method1() : score : " + score);
			}
		}
		score += 10;
		System.out.println("f1()의 지역변수 score : " + score);
		LocalClassB bb = new LocalClassB();
		System.out.println(bb.score);
		bb.method1();
		System.out.println("==OuterClass.f1() end==");
	}
//	4. block
//	5. inner class
	public class InstanceInnerClass {
		String message = "인스턴스 inner클래스의 필드";
		int instanceA = 100; 
		static int instanceB = 200; 
		void methodA() {
			String message = "인스턴스 inner클래스의 메서드의 지역변수";
			System.out.println(message);
			System.out.println(this.message);
			System.out.println(OuterClass.this.message);
			System.out.println("InstanceInnerClass.methodA() : non-static" + (instanceA + instanceB));
		}
		static void methodB() {
			System.out.println("InstanceInnerClass.methodB() : static"+instanceB);
		}
	}
	public static class StaticInnerClass {
		int instanceC = 10; 
		static int instanceD = 20; 
		void methodC() {
			System.out.println("StaticInnerClass.methodA() : non-static" + (instanceC + instanceD));
		}
		static void methodD() {
			System.out.println("StaticInnerClass.methodB() : static"+instanceD);
		}
	}
}
