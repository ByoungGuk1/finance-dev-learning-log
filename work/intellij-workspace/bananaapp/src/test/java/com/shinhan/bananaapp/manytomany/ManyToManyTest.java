package com.shinhan.bananaapp.manytomany;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@SpringBootTest
class ManyToManyTest {
  @Autowired
  private FreeBoardRepository boardRepo;
  @Autowired
  private FreeReplyRepository replyRepo;

  @Test
  void insertBoards() {
    IntStream.rangeClosed(1, 10).forEach(i -> {
      FreeBoardEntity board = FreeBoardEntity.builder().title("title-" + i).content("content: " + i).writer("tester" + (i % 3)).build();
      boardRepo.save(board);
    });
  }

  //Board 5건 + Reply 3건
  @Test
  void insertDatas() {
    IntStream.rangeClosed(100, 105).forEach(i -> {
      FreeBoardEntity board = FreeBoardEntity.builder().title("title-" + i).content("content: " + i).writer("tester" + (i % 4)).build();
      List<FreeReplyEntity> replyList = new ArrayList<>();
      IntStream.rangeClosed(1, 3).forEach(j -> {
        replyList.add(FreeReplyEntity.builder().reply("댓글이" + j).writer("user" + (j % 2)).board(board).build());
      });
      board.setReplyList(replyList);
      boardRepo.save(board);
    });
  }

  // 존재하는 Board(11) 에 Reply 추가하기 (2개)
  @Test
  @Transactional
  @Commit
  void insertReplies() {
    FreeBoardEntity foundBoard = boardRepo.getReferenceById(10);
    List<FreeReplyEntity> replyList = new ArrayList<>();
    IntStream.rangeClosed(1, 2).forEach(i -> {
      replyList.add(FreeReplyEntity.builder().reply("댓글" + i).writer("user" + i).board(foundBoard).build());
    });
    if (foundBoard.getReplyList().isEmpty()) {
      foundBoard.setReplyList(replyList);
    } else {
      foundBoard.getReplyList().addAll(replyList);
    }
    boardRepo.save(foundBoard);
  }

  @Test
  @Transactional
  void findAll() {
    boardRepo.findAllBoardAndReplies().stream().map(b -> b.toString() + "~~~" + b.getReplyList().toString()).forEach(log::info);
  }
}