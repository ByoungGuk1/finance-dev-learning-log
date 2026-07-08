package com.shinhan.bananaapp.section5;

import com.shinhan.bananaapp.dto.AccountDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
//방법 2 생성자를 통해 주입 (권장)
//@RequiredArgsConstructor
@Slf4j
public class EmpService implements EmpServiceInterface {
    //방법 1 필드 주입
//    @Autowired
    private final EmpRepository empRepo;

    //방법 2 생성자를 통해 주입 (권장)
    public EmpService(EmpRepository empRepo) {
//        log.info("empService");
        this.empRepo = empRepo;
    }

    public AccountDTO selectEmpService() {
        return empRepo.getData();
    }
}
