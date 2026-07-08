package com.shinhan.bananaapp.di2;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "empId")
public class EmpDTO {
    Integer empId;
    String empName;
    Long salary;
}
