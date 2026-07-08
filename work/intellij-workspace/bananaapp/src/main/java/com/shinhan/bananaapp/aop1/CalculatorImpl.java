//package com.shinhan.bananaapp.aop1;
//
//import org.springframework.stereotype.Component;
//
/// / 핵심로직, 주관심사, Target
//@Component
//public class CalculatorImpl implements Calculator {
//    @Override
//    public int add(int a, int b) {
//        System.out.println("[target] a+b = " + (a + b));
//        return a + b;
//    }
//
//    @Override
//    public int add(int a, int b, int c) {
//        System.out.println("[target] a+b = " + (a + b + c));
//        return a + b + c;
//    }
//
//    @Override
//    public int sub(int a, int b) {
//        System.out.println("[target] a+b = " + (a - b));
//        return a - b;
//    }
//
//    @Override
//    public int mul(int a, int b) {
//        System.out.println("[target] a+b = " + (a * b));
//        return a * b;
//    }
//
//    @Override
//    public int div(int a, int b) {
//        System.out.println("[target] a+b = " + (a / b));
//        return a / b;
//    }
//}
