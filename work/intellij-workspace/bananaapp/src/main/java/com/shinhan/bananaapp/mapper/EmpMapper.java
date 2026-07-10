package com.shinhan.bananaapp.mapper;

import com.shinhan.bananaapp.dto.EmpDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpMapper {
    List<EmpDTO> findAll();

    EmpDTO findById(int id);

    Integer save(EmpDTO empDTO);

    Integer delete(int id);

    List<EmpDTO> findByCondition(EmpDTO empDTO);

    List<EmpDTO> findByName(String empName);
}
