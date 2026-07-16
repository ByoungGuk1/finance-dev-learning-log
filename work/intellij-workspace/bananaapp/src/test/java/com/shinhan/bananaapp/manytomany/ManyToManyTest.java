package com.shinhan.bananaapp.manytomany;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@Slf4j
@SpringBootTest
class ManyToManyTest {
  @Autowired
  private FreeBoardRepository boardRepo;
  @Autowired
  private FreeReplyRepository replyRepo;

  @Test
  void insertDatas() {
    IntStream.rangeClosed(1, 10).forEach(i -> {
      FreeBoardEntity board = FreeBoardEntity.builder().title("title-" + i).content("content: " + i).writer("tester" + (i % 3)).build();
      boardRepo.save(board);
    });
  }
}