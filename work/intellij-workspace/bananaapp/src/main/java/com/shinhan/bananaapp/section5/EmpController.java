package com.shinhan.bananaapp.section5;

import com.shinhan.bananaapp.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emp")
//@RequiredArgsConstructor
public class EmpController {
//    @Autowired // 필드를 이용해서 component 주입
    private final EmpServiceInterface service;

    public EmpController(@Qualifier("empService2") EmpServiceInterface service) {
        this.service = service;
    }

    @GetMapping("/acc")
    public AccountDTO selectData(){
        return service.selectEmpService();
    }
}
