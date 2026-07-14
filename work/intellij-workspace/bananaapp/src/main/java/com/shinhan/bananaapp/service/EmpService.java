package com.shinhan.bananaapp.service;

//타입이 같으면 다른 Component에서 자동 Injection(@Autowired),
// 같은 타입이 여러개 존재하면 이름 구별(@Qualifier)


import com.shinhan.bananaapp.dto.EmpDTO;
import com.shinhan.bananaapp.repository.mybatis.EmpRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("personService")
public class EmpService {
    private final EmpRepository empRepo;

    public EmpService(@Qualifier("employeeRepo") EmpRepository empRepo) {
        this.empRepo = empRepo;
    }

    public List<EmpDTO> selectAllService() {
        return empRepo.selectAll();
    }

    public EmpDTO selectById(int id) {
        return empRepo.selectById(id);
    }

    public Integer createEmp(EmpDTO empDTO) {
        EmpDTO foundEmp = empRepo.selectById(empDTO.getEmployeeId());
        if (foundEmp != null) {
            return -1;
        }
        return empRepo.save(empDTO);
    }

    public Integer updateEmp(EmpDTO empDTO) {
        EmpDTO foundEmp = empRepo.selectById(empDTO.getEmployeeId());
        if (foundEmp == null) {
            return -1;
        }
        return empRepo.save(empDTO);
    }

    public Integer deleteEmp(int id) {
        return empRepo.deleteById(id);
    }

    public List<EmpDTO> selectByName(String empName) {
        return empRepo.selectByName(empName);
    }

    public List<EmpDTO> selectByCondition(EmpDTO empDTO) {
        return empRepo.findByCondition(empDTO);
    }
}
