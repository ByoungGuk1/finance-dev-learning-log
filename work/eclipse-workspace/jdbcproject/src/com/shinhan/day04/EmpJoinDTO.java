package com.shinhan.day04;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 15. 오전 11:30:46 설명 : EmpJoinDTO
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class EmpJoinDTO {
	private String firstName;
	private String lastName;
	private Double salary;
	private String departmentName;
	private String jobTitle;
}
