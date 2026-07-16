package com.shinhan.bananaapp.manytoone;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
  private Long id;
  private String productName;
  private Long price;
  private Integer stock;
  private String category;
  private LocalDateTime regDate;
  private LocalDateTime modDate;
}
