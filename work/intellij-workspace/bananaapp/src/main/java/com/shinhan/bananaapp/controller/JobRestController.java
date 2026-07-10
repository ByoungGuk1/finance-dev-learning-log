package com.shinhan.bananaapp.controller;

import com.shinhan.bananaapp.dto.JobDTO;
import com.shinhan.bananaapp.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/job")
public class JobRestController {
    private final JobService jobService;

    @GetMapping("list.do")
    public List<JobDTO> findAll() {
        return jobService.findAll();
    }
}
