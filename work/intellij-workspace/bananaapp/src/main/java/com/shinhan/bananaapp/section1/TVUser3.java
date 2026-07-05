package com.shinhan.bananaapp.section1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TVUser3 {
    public static void main(String[] args) {
        f1();
    }

    public static void f1(){
//        IOC => 제어의 역전 / 직접 생성하지 않고 스프링 컨테이너가 생성해서 전달
        ApplicationContext context = new ClassPathXmlApplicationContext("section1.xml");
//        Bean: Spring은 Object를 Bean이라고 부른다.
        TV tv = context.getBean("tv",TV.class);
        tv.powerOn();
        tv.powerOff();
    }
}
