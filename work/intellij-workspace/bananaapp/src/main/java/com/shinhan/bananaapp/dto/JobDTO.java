package com.shinhan.bananaapp.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "jobId")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobDTO {
    private String jobId;
    private String jobTitle;
    private int minSalary;
    private int maxSalary;
}
