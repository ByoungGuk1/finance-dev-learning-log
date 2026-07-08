package com.shinhan.bananaapp.repository;

import com.shinhan.bananaapp.di2.EmpDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//타입이 같으면 다른 Component에서 자동 Injection(@Autowired),
// 같은 타입이 여러개 존재하면 이름 구별(@Qualifier)
@Repository("employeeRepo")
public class EmpRepository {

    private final List<EmpDTO> empList = new ArrayList<>();

    {
        EmpDTO emp1 = new EmpDTO();
        emp1.setEmpId(1);
        emp1.setEmpName("김길동");
        emp1.setSalary(1000L);

        EmpDTO emp2 = new EmpDTO(2, "병국", 2000L);

        EmpDTO emp3 = EmpDTO.builder().empId(3).empName("민준").salary(3000L).build();

        empList.add(emp1);
        empList.add(emp2);
        empList.add(emp3);
    }

    public List<EmpDTO> selectAll() {
        return empList;
    }

    public EmpDTO selectById(int id) {
        return empList.stream().filter(emp -> emp.getEmpId() == id).findFirst().orElse(null);
    }

    public Boolean save(EmpDTO empDTO) {
        EmpDTO foundEmpDTO = selectById(empDTO.getEmpId());
        if (foundEmpDTO != null) {
            empList.remove(foundEmpDTO);
        }
        return empList.add(empDTO);
    }

    public Boolean deleteById(int id) {
        return empList.removeIf(emp -> emp.getEmpId() == id);
    }
}
