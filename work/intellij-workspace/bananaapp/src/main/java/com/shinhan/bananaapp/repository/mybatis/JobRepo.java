package com.shinhan.bananaapp.repository.mybatis;

import com.shinhan.bananaapp.dto.JobDTO;
import com.shinhan.bananaapp.mapper.JobMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
@RequiredArgsConstructor
public class JobRepo {
    private final JobMapper jobMapper;

    public List<JobDTO> findAll() {
        return jobMapper.findAll();
    }
}
