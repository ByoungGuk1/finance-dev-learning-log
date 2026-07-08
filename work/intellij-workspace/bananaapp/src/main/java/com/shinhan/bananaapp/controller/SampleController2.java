package com.shinhan.bananaapp.controller;

import com.shinhan.bananaapp.property.ShinhanProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SampleController2 {
//
//    @Value("${shinhan.api.key}")
//    private String shinhanApiKey;


    @Autowired
    private ShinhanProperties shinhanProperties;

    @GetMapping("/sample1")
    public String f_sample2() {
        String a1 = shinhanProperties.getApi1().toString();
        String a2 = shinhanProperties.getApi2().toString();
//        String a3 = shinhanProperties.getApi3().toString();
        return a1 + '\n' + a2;
    }
}
