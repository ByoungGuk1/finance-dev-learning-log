package com.shinhan.bananaapp.repository.jpa;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.shinhan.bananaapp.entity2.QWebBoardEntity;
import com.shinhan.bananaapp.entity2.WebBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface WebBoardRepository extends JpaRepository<WebBoardEntity, Long>, QuerydslPredicateExecutor<WebBoardEntity> {
  // 1. 기본적인 crud 제공 -> findAll, findById, save, delete, count ...

  // 2. 규칙에 맞는 함수 정의
  //  findBy.....

  // 3. JPQL (JPA Query Languege)
  // @Query("select ....")

  // 4. QueryDSL 을 사용하여 동적으로 SQL 생성 => QDomain 필요
  //  QDomain : Entity를 JPA에서 사용 가능하도록 제공
  // QuerydslPredicateExecutor<?> 상속 필요

  // BooleanBuilder로 동적 검색 조건 생성
  // type = "tcw" → title, content, writer 검색
  // interface는 구현 불가 -> Modifier 중 default를 사용하여 재정의가 가능한 메서드 구현
  default Predicate makePredicate(String type, String keyword) {
    QWebBoardEntity qWebBoard = QWebBoardEntity.webBoardEntity;
    BooleanBuilder builder = new BooleanBuilder();
    if (type == null) return builder;

    // or title like '%:keyword%'
    if (type.contains("t"))
      builder.or(qWebBoard.title.contains(keyword));
    // or content like '%:keyword%'
    if (type.contains("c"))
      builder.or(qWebBoard.content.contains(keyword));
    // or writer like '%:keyword%'
    if (type.contains("w"))
      builder.or(qWebBoard.writer.eq(keyword));
    return builder;
  }
}
