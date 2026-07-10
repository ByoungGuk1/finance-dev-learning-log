package com.shinhan.bananaapp.controller;

import com.shinhan.bananaapp.dto.DepartmentDTO;
import com.shinhan.bananaapp.service.DeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dept")
@RequiredArgsConstructor
public class DeptRestController {
    private final DeptService deptService;

    @GetMapping("list.do")
    public List<DepartmentDTO> selectAll() {
        return deptService.findAll();
    }
}
