package com.shinhan.bananaapp.section1;

public class LgTV implements TV{
    @Override
    public void powerOn(){
        System.out.println("LgTV 전원 켜기");
    }
    @Override
    public void powerOff(){
        System.out.println("LgTV 전원 끄기");
    }
}
