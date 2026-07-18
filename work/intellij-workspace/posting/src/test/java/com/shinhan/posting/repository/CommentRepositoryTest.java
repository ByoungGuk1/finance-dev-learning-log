package com.shinhan.posting.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class CommentRepositoryTest {
  @Autowired
  private CommentRepository repo;

}