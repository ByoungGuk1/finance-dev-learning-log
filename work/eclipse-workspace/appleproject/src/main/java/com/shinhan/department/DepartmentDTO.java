package com.shinhan.department;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 24. 오후 12:03:08 설명 : DepartmentDTO
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "departmentId")
@Builder
public class DepartmentDTO {
	private int departmentId;
	private String departmentName;
	private int managerId;
	private int locationId;
}
