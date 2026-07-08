package com.shinhan.bananaapp.di2;


import com.shinhan.bananaapp.service.EmpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class PersonController {

    //@Qualifier("personService")
    final EmpService empService;

//    PersonController(@Qualifier("personService") EmpService empService){
//        this.empService = empService;
//    }


    @GetMapping("/list")
    public List<EmpDTO> f_1() {
        return empService.selectAllService();
    }
}
//요청->Controller->Service-->Repository
