package com.shinhan.bananaapp.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "employeeId")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpDTO {
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date hireDate;
    private String jobId;
    private Double salary;
    private Double commissionPct;
    private Integer managerId;
    private Integer departmentId;
}
