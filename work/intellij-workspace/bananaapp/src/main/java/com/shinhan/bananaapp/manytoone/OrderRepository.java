package com.shinhan.bananaapp.manytoone;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
  // 방법 1 - @EntityGraph
  //  존재하는 함수를 재정의 (fetch join)
  @EntityGraph(attributePaths = "product")
  // 방법 2 - fetch join
//  @Query("select O from OrderEntity O join fetch O.product")
  List<OrderEntity> findAll();

  // 특정 상품의 주문 목록 조회
  List<OrderEntity> findByProduct(ProductEntity product);

  List<OrderEntity> findByProductPriceGreaterThan(Long price);

  // 주문 상태로 조회
  List<OrderEntity> findByStatus(String status);

//  // 상품별 주문 건수
//  @Query("select p.productName as pName, count(p.id) as pCount from OrderEntity as o join fetch o.product as p group by p.productName")
//  List<Map<String, Object>> findProductNameAndCount();
//
//  @Query("select new com.shinhan.bananaapp.manytoone.OrderCountDTO(p.productName, count(p.id)) from OrderEntity as o join fetch o.product as p group by p.productName")
//  List<OrderCountDTO> findProductNameAndCountToDTO();
//
//  @Query("select p.productName as productName, count(p.id) as productCount from OrderEntity as o join fetch o.product as p group by p.productName")
//  List<OrderCountInterface> findProductNameAndCountToInterface();
}
