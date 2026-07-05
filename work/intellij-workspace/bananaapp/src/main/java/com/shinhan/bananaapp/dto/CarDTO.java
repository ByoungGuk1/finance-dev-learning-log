package com.shinhan.bananaapp.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CarDTO {
    private String model;
    private int price;
}
