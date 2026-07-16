package com.shinhan.bananaapp.manytoone;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

  // 카테고리로 조회하기
  List<ProductEntity> findByCategory(String category);

  // 재고가 있는 상품만 조회
//  @Query("select P from ProductEntity P where P.stock > :stock")
  List<ProductEntity> findByStockGreaterThan(int stock);

  // 상품명으로 조회
//  @Query("select P from ProductEntity P where P.productName like %:productName%")
  List<ProductEntity> findByProductNameContaining(String productName);
}
