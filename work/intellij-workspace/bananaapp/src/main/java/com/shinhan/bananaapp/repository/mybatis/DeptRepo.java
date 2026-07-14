package com.shinhan.bananaapp.repository.mybatis;

import com.shinhan.bananaapp.dto.DepartmentDTO;
import com.shinhan.bananaapp.mapper.DeptMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
@RequiredArgsConstructor
public class DeptRepo {
    private final DeptMapper deptMapper;

    public List<DepartmentDTO> findAll() {
        return deptMapper.findAll();
    }
}
