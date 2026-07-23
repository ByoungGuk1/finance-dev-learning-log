package com.shinhan.bananaapp.aop;


//핵심로직, 주관심사 , Target
public class CalculatorImpl implements Calculator {
  @Override
  public int add(int a, int b) {
    System.out.println("[Target] a+b=" + (a + b));
    return a + b;
  }

  @Override
  public int add(int a, int b, int c) {
    System.out.println("[Target] a+b+c=" + (a + b + c));
    return a + b + c;
  }

  @Override
  public int subtract(int a, int b) {
    System.out.println("[Target] a-b=" + (a - b));
    return a - b;
  }

  @Override
  public int multiply(int a, int b) {
    System.out.println("[Target] a*b=" + (a * b));
    return a * b;
  }

  @Override
  public int divide(int a, int b) {
    System.out.println("[Target] a/b=" + (a / b));
    return a / b;
  }
}
