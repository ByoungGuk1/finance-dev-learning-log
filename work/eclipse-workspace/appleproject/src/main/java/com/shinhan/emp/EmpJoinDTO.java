package com.shinhan.emp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
