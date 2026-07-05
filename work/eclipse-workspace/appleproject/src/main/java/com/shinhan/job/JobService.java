package com.shinhan.job;

import java.util.List;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 24. 오후 12:11:26 설명 : DepartmentService
 */
public class JobService {
	JobDAO jobDAO = new JobDAO();

	public List<JobDTO> selectAll() {
		return jobDAO.selectAll();
	}
}
