package com.shinhan.bananaapp.manytoone;

import com.shinhan.bananaapp.entity2.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false, length = 100)
  private String productName;     // 상품명
  @Column(nullable = false)
  private Long price;             // 가격
  @Column(nullable = false)
  private Integer stock;              // 재고
  @Column(length = 50)
  private String category;        // 카테고리
}