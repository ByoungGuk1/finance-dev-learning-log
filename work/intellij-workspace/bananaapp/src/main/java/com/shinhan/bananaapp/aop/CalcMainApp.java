package com.shinhan.bananaapp.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CalcMainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("section3.xml");
        Calculator calculator = (Calculator) context.getBean("calculator");

        int result = calculator.add(1, 2, 3);
        System.out.println(result);
    }
}
