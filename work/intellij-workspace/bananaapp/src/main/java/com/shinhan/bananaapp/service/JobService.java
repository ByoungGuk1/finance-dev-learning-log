package com.shinhan.bananaapp.service;

import com.shinhan.bananaapp.dto.JobDTO;
import com.shinhan.bananaapp.repository.JobRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepo jobRepo;

    public List<JobDTO> findAll() {
        return jobRepo.findAll();
    }
}
