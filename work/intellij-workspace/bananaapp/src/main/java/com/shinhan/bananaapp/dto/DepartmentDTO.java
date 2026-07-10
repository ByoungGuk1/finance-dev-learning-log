package com.shinhan.bananaapp.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "departmentId")
public class DepartmentDTO {
    private Integer departmentId;
    private String departmentName;
    private Integer managerId;
    private Integer locationId;
}
