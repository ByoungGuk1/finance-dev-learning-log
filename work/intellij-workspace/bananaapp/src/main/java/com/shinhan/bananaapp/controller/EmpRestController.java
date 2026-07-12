package com.shinhan.bananaapp.controller;

import com.shinhan.bananaapp.dto.EmpDTO;
import com.shinhan.bananaapp.service.EmpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/emp")
@RequiredArgsConstructor
public class EmpRestController {
    private final EmpService empService;

    @GetMapping("/delete.do")
    public ResponseEntity<?> deleteEmp(@RequestParam("empid") Integer empId) {
        Map<String, Integer> result = new HashMap<>();
        int deletedCount = empService.deleteEmp(empId);
        result.put("result", deletedCount);
        return deletedCount > 0
                ? ResponseEntity.ok(result)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

    @GetMapping("/detail.do")
    public ResponseEntity<?> getEmp(@RequestParam("empid") Integer empId) {
        EmpDTO employee = empService.selectById(empId);
        return employee != null
                ? ResponseEntity.ok(employee)
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/detail.do")
    public ResponseEntity<?> addEmp(@ModelAttribute EmpDTO empDTO) {
        Map<String, Integer> result = new HashMap<>();
        System.out.println("input: " + empDTO.toString());
        int updatedCount = empService.updateEmp(empDTO);
        result.put("result", updatedCount);
        System.out.println("output: " + result);
        return updatedCount > 0
                ? ResponseEntity.ok(result)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

    @PostMapping("/insert.do")
    public ResponseEntity<?> insertEmp(@ModelAttribute EmpDTO empDTO) {
        int createdCount = empService.createEmp(empDTO);
        return createdCount > 0
                ? ResponseEntity.status(HttpStatus.CREATED).body(createdCount)
                : ResponseEntity.status(HttpStatus.CONFLICT).body(createdCount);
    }

    @GetMapping("/list.do")
    public ResponseEntity<?> getEmpList() {
        return ResponseEntity.ok(empService.selectAllService());
    }

    @GetMapping("/search.do")
    public ResponseEntity<?> searchEmp(@RequestParam("fname") String empName) {
        return ResponseEntity.ok(empService.selectByName(empName));
    }

    @GetMapping("/condition.do")
    public ResponseEntity<?> searchCondition(@RequestParam("deptid") int deptId, @RequestParam("jobid") String jobId, @RequestParam("salary") double salary, @RequestParam("hire_date") String hireDate) {
        EmpDTO empDTO = EmpDTO.builder().departmentId(deptId).jobId(jobId).salary(salary).hireDate(Date.valueOf(hireDate)).build();
        return ResponseEntity.ok(empService.selectByCondition(empDTO));
    }
}
