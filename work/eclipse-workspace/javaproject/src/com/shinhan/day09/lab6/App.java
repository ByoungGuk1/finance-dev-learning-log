package com.shinhan.day09.lab6;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 27. 오후 4:10:12
 * 설명			: App
 */
public class App {
	public static void main(String[] args) {
		OuterClass v1 = new OuterClass();
		v1.f1();
		
//		instance class -> instance method
//		OuterClass.InstanceInnerClass iic = v1.new InstanceInnerClass();
		OuterClass.InstanceInnerClass iic = new OuterClass().new InstanceInnerClass();
		iic.methodA();
//		instance class -> static method
		OuterClass.InstanceInnerClass.methodB();
		
//		static class -> instance method
		OuterClass.StaticInnerClass sic = new OuterClass.StaticInnerClass();
		sic.methodC();
//		static class -> static method
		OuterClass.StaticInnerClass.methodD();
	}
}
