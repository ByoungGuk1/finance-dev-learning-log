package com.shinhan.bananaapp.repository;

import com.shinhan.bananaapp.dto.EmpDTO;
import com.shinhan.bananaapp.mapper.EmpMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

//타입이 같으면 다른 Component에서 자동 Injection(@Autowired),
// 같은 타입이 여러개 존재하면 이름 구별(@Qualifier)
@Repository("employeeRepo")
@RequiredArgsConstructor
public class EmpRepository {
    private final EmpMapper empMapper;

    public List<EmpDTO> selectAll() {
        return empMapper.findAll();
    }

    public EmpDTO selectById(int id) {
        return empMapper.findById(id);
    }

    public Integer save(EmpDTO empDTO) {
        EmpDTO foundEmpDTO = selectById(empDTO.getEmployeeId());
        if (foundEmpDTO != null) {
            foundEmpDTO.setDepartmentId(empDTO.getDepartmentId() != null ? empDTO.getDepartmentId() : null);
            foundEmpDTO.setSalary(empDTO.getSalary() != null ? empDTO.getSalary() : null);
            foundEmpDTO.setJobId(empDTO.getJobId() != null ? empDTO.getJobId() : null);
            foundEmpDTO.setEmail(empDTO.getEmail() != null ? empDTO.getEmail() : null);
            foundEmpDTO.setHireDate(empDTO.getHireDate() != null ? empDTO.getHireDate() : null);
            foundEmpDTO.setCommissionPct(empDTO.getCommissionPct() != null ? empDTO.getCommissionPct() : null);
            foundEmpDTO.setFirstName(empDTO.getFirstName() != null ? empDTO.getFirstName() : null);
            foundEmpDTO.setLastName(empDTO.getLastName() != null ? empDTO.getLastName() : null);
            deleteById(foundEmpDTO.getEmployeeId());
        }
        return empMapper.save(empDTO);
    }

    public Integer deleteById(int id) {
        return empMapper.delete(id);
    }

    public List<EmpDTO> findByCondition(EmpDTO empDTO) {
        return empMapper.findByCondition(empDTO);
    }

    public List<EmpDTO> selectByName(String empName) {
        return empMapper.findByName(empName);
    }
}
