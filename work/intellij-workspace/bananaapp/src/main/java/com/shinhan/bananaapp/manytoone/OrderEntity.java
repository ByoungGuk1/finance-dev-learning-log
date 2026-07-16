package com.shinhan.bananaapp.manytoone;

import com.shinhan.bananaapp.entity2.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "product")
public class OrderEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private Integer quantity;           // 주문 수량
  @Column(nullable = false)
  private Long totalPrice;        // 총 금액 (quantity × price)
  @Column(length = 20)
  @Builder.Default
  private String status = "ORDERED";  // ORDERED / CANCELLED
  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime orderDate;
  // ── @ManyToOne — 핵심 ─────────────────────
  // 주문(N) → 상품(1)
  // LAZY: 주문 조회 시 상품은 실제 사용할 때만 조회
  @ManyToOne(fetch = FetchType.LAZY)
  //@JoinColumn(name = "product_id", nullable = false)
  private ProductEntity product;
}