package com.shinhan.bananaapp.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShinhanConfig {
    public ShinhanConfig(){
//        System.out.println("SinhanConfig constructor");
    }

    @Bean("shin")
    public ShinhanDTO makeDTO1(){
//        System.out.println("ShinhanConfig > makeDTO1 :: ShinhanDTO 생성 Bean1");
        return new ShinhanDTO("신한DS", "을지로", "010-1234-1234");
    }
    @Bean("shin2")
    public ShinhanDTO makeDTO2(){
//        System.out.println("ShinhanConfig > makeDTO2 :: ShinhanDTO 생성 Bean2");
        return new ShinhanDTO("신한DS2", "을지로2", "010-5678-5678");
    }
}
