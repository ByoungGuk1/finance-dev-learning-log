package com.shinhan.bananaapp.manytoone;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
  private Long id;
  private Integer quantity;
  private Long totalPrice;
  private String status;
  private LocalDateTime orderDate;
  // 연관 상품 정보 (flat하게 포함)
  private Long productId;
  private String productName;     // join 결과 담을 필드
  private Long price;
}
