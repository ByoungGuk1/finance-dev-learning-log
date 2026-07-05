package com.shinhan.bananaapp.controller;

import com.shinhan.bananaapp.dto.CarDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Spring - POJO(Plain Old Java Object) 사용

// @RestController = @Controller + @ResponseBody
@Slf4j
@RestController
public class SampleController {
    @GetMapping("/hello")
    public String f1(){
        return "Hello";
    }

    @GetMapping("/car")
    public CarDTO f2(){
        return CarDTO.builder().model("ABC").price(2000).build();
    }

    @GetMapping("/carlist")
    public List<CarDTO> f3(){
        return List.of(
                CarDTO.builder()
                        .model("ABC")
                        .price(2000)
                        .build(),
                CarDTO.builder()
                        .model("DEF")
                        .price(3000)
                        .build()
        );
    }

    @GetMapping("/log-test")
    public String logTest() {
        System.out.println("로그 수준 test 중");
        log.trace("TRACE 레벨 로그");
        log.debug("DEBUG 레벨 로그");
        log.info("INFO 레벨 로그");
        log.warn("WARN 레벨 로그");
        log.error("ERROR 레벨 로그");
        return "로그 확인";
    }
}
