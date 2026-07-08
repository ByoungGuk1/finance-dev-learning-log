package com.shinhan.bananaapp.di;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller : 응답은 템플릿으로 => 리턴값은 템플릿의 이름
// 템플릿의 위치는 resource 하위에
//@Controller
//@ResponseBody
@RestController
// RestController = Controller + ResponseBody
@RequiredArgsConstructor
public class ShinhanController {
//    @Autowired
//    private ShinhanService service;

    private final ShinhanService service;

    @GetMapping("/hi")
    public String f_1(){
        return service.f_getDTO();
    }
}
