package com.shinhan.bananaapp.manytoone;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
class ManyToOne2Test {
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private OrderRepository orderRepository;

  @Test
  void insertProduct() {
    ProductEntity p1 = ProductEntity.builder().productName("노트북123").price(2_000_000L).stock(100).category("PC").build();
    ProductEntity p2 = ProductEntity.builder().productName("노트북456").price(3_000_000L).stock(0).category("PC").build();

    productRepository.save(p1);
    productRepository.save(p2);

    productRepository.findAll().stream().map(ProductEntity::toString).forEach(log::info);
  }

  @Test
  void insertOrder() {
    ProductEntity p1 = productRepository.getReferenceById(1L);
    ProductEntity p2 = productRepository.getReferenceById(2L);

    OrderEntity o1 = OrderEntity.builder().quantity(5).totalPrice(10_000_000L).product(p1).build();
    OrderEntity o2 = OrderEntity.builder().quantity(2).totalPrice(4_000_000L).product(p1).build();

    OrderEntity o3 = OrderEntity.builder().quantity(2).totalPrice(6_000_000L).product(p2).build();

    orderRepository.save(o1);
    orderRepository.save(o2);
    orderRepository.save(o3);
  }

  @Test
  void selectAllProduct() {
    productRepository.findAll().stream().map(ProductEntity::toString).forEach(log::info);
  }

  @Test
  @Transactional
  void selectAllOrder() {
    orderRepository.findAll().stream().map(d -> d.toString() + "-Product-" + d.getProduct().toString()).forEach(log::info);
  }

  // 카테고리로 조회하기
  @Test
  void selectProductByCategory() {
    productRepository.findByCategory("PC").stream().map(ProductEntity::toString).forEach(log::info);
  }

  // 재고가 있는 상품만 조회
  @Test
  void selectProductByStock() {
    productRepository.findByStockGreaterThan(0).stream().map(ProductEntity::toString).forEach(log::info);
  }

  // 상품명으로 조회
  @Test
  void selectProductByName() {
    productRepository.findByProductNameContaining("노트북").stream().map(ProductEntity::toString).forEach(log::info);
  }

  // 특정 상품의 주문 목록 조회
  @Test
  void selectOrderByProduct() {
    ProductEntity p1 = productRepository.getReferenceById(1L);
    orderRepository.findByProduct(p1).stream().map(OrderEntity::toString).forEach(log::info);
  }

  // 주문 상태로 조회
  @Test
  @Transactional
  void selectOrderByStatus() {
    orderRepository.findByStatus("ORDERED").stream().map(d -> d.toString() + "-" + d.getProduct().toString()).forEach(log::info);
  }

  @Test
  @Transactional
  void selectOrderByPriceGraterThen() {
    orderRepository.findByProductPriceGreaterThan(100_000L).stream().map(d -> d.toString() + "-" + d.getProduct().toString()).forEach(log::info);
  }

//  @Test
//  @Transactional
//  void selectOrderByProductNameAndCountGroupByProductName() {
//    orderRepository.findProductNameAndCount().stream().map(Map::entrySet).forEach(d -> {
//      d.stream().map(Map.Entry::getKey).forEach(log::info);
//    });
//  }

//  @Test
//  @Transactional
//  void selectOrderByProductNameAndCountGroupByProductNameToDTO() {
//    orderRepository.findProductNameAndCountToDTO().stream().map(OrderCountDTO::toString).forEach(log::info);
//    orderRepository.findProductNameAndCountToInterface().stream().map(OrderCountInterface::toString).forEach(log::info);
//  }
}