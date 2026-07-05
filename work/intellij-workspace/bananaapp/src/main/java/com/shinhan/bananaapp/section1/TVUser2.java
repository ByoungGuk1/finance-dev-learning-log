package com.shinhan.bananaapp.section1;

public class TVUser2 {
    public static void main(String[] args) {
        f1();
    }

    public static void f1(){
//        Factory Pattern => Factory가 의존 관게 (결합도 높음)
        TV tv = TVFactory.makeTV("Samsung");
        tv.powerOn();
        tv.powerOff();
    }
}
