package com.shinhan.bananaapp.manytoone;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderCountDTO {
  private String productName;
  private Long pCount;
}
