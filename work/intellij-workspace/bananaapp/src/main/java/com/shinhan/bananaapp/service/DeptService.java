package com.shinhan.bananaapp.service;

import com.shinhan.bananaapp.dto.DepartmentDTO;
import com.shinhan.bananaapp.repository.mybatis.DeptRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeptService {
    private final DeptRepo deptRepo;

    public List<DepartmentDTO> findAll() {
        return deptRepo.findAll();
    }
}
