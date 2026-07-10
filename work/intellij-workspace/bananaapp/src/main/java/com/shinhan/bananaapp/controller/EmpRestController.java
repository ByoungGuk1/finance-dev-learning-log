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
        result.put("result", empService.deleteEmp(empId));
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/detail.do")
    public ResponseEntity<?> getEmp(@RequestParam("empid") Integer empId) {
        return ResponseEntity.ok(empService.selectById(empId));
    }

    @PostMapping("/detail.do")
    public ResponseEntity<?> addEmp(@RequestBody EmpDTO empDTO) {
        Map<String, Integer> result = new HashMap<>();
        System.out.println("input: " + empDTO.toString());
        result.put("result", empService.updateEmp(empDTO));
        System.out.println("output: " + result);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/insert.do")
    public ResponseEntity<?> insertEmp(@RequestBody EmpDTO empDTO) {
        return ResponseEntity.ok(empService.createEmp(empDTO));
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
