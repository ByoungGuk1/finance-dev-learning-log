package com.shinhan.bananaapp.repository.jpa;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shinhan.bananaapp.entity2.QWebBoardEntity;
import com.shinhan.bananaapp.entity2.WebBoardEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
class WebBoardRepositoryTest {
  @Autowired
  private WebBoardRepository repo;
  @Autowired
  private JPAQueryFactory jpaQueryFactory;

  @Test
  public void qEntity() {
    Predicate predicate = repo.makePredicate("ctw", "JPA");
    Iterator<WebBoardEntity> iteratorEntity = repo.findAll(predicate).iterator();
    List<WebBoardEntity> list = new ArrayList<>();
    iteratorEntity.forEachRemaining(list::add);
    list.stream().map(WebBoardEntity::toString).forEach(log::info);
  }

  @Test
  public void insert() {
    IntStream.rangeClosed(1, 10).forEach(index -> {
      WebBoardEntity wb = WebBoardEntity.builder().title("JPA-" + index).content("내용 : " + index).writer("tester" + index % 6).build();
      repo.save(wb);
    });
    repo.save(WebBoardEntity.builder().title("제목").content("내용에 JPA 포함").writer("작성자").build());
    repo.save(WebBoardEntity.builder().title("작성자에 값").content("작성자에 값").writer("JPA").build());
    repo.save(WebBoardEntity.builder().title("값없음").content("없어요").writer("없음").build());
  }

  @Test
  public void JPAQueryFactory() {
    List<WebBoardEntity> list = jpaQueryFactory
        .select(QWebBoardEntity.webBoardEntity)
        .from(QWebBoardEntity.webBoardEntity)
        .where(QWebBoardEntity.webBoardEntity.bno.gt(5L).and(QWebBoardEntity.webBoardEntity.writer.like("%test%")))
        .orderBy(QWebBoardEntity.webBoardEntity.bno.desc())
        .fetch();
    list.stream().map(WebBoardEntity::toString).forEach(log::info);
  }
}