package com.shinhan.bananaapp.section1;

public class TVUser1 {
    public static void main(String[] args) {
        f1();
    }

    public static void f1(){
//        Interface Pattern => 의존 관게 (결합도 높음)
        TV tv = new SamsungTV();
        tv.powerOn();
        tv.powerOff();
    }
}
