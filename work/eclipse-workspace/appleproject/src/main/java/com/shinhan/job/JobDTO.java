package com.shinhan.job;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 7. 2. 오후 4:54:16 설명 : JobDTO
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobDTO {
	private String jobId;
	private String jobTitle;
	private int minSalary;
	private int maxSalary;
}
