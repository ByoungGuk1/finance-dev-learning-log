package com.shinhan.bananaapp.repository.jpa;

import com.shinhan.bananaapp.dto.jpa.BoardDTO;
import com.shinhan.bananaapp.entity1.BoardEntity2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Slf4j
@SpringBootTest
class BoardRepository2Test {
  @Autowired
  private ModelMapper modelMapper;
  @Autowired
  private BoardRepository2 boardRepository2;

  @Test
  void insert10() {
    IntStream.rangeClosed(1, 10).forEach(i -> {
      BoardEntity2 boardEntity2 = BoardEntity2.builder().title("title" + i).content("글").writer("tester" + (i % 5)).build();
      boardRepository2.save(boardEntity2);
    });
  }

  @Test
  void findAll() {
    List<BoardEntity2> boardEntity2s = boardRepository2.findAll();
    boardEntity2s.stream().map(BoardEntity2::toString).forEach(log::info);
  }

  @Test
  void findById() {
    boardRepository2.findById(1).ifPresent(d -> log.info(d.toString()));
  }

  @Test
  void findByWriter() {
    boardRepository2.findByWriter("tester0").stream().map(BoardEntity2::toString).forEach(log::info);
  }

  @Test
  void customQuery() {
    boardRepository2.customQuery(2).stream().map(BoardEntity2::toString).forEach(log::info);
    log.info("-----------------");
    boardRepository2.customQuery2(2).stream().map(BoardEntity2::toString).forEach(log::info);
  }

  @Test
  void countByWriter() {
    boardRepository2.customCountByWriter().stream().map(Map::entrySet).forEach(System.out::println);
  }

  @Test
  void customArray() {
    boardRepository2.customArray().stream().map(d -> d[0] + " : " + d[1]).forEach(log::info);
  }

  @Test
  void customDTO() {
    boardRepository2.customDTO().stream().map(Board2DTO::toString).forEach(log::info);
  }

  @Test
  void customInterface() {
    boardRepository2.customInterface().stream().map(data -> data.getWriter() + " : " + data.getCount()).forEach(log::info);
  }

  @Test
  void paging1() {
    Pageable pageable1 = PageRequest.of(1, 10);
    Pageable pageable2 = PageRequest.of(2, 10, Sort.by("bno").ascending());
    Pageable pageable3 = PageRequest.of(0, 10, Sort.Direction.ASC, "bno");
    boardRepository2.findAll(pageable3).map(BoardEntity2::toString).forEach(log::info);

    Page<BoardEntity2> result = boardRepository2.findAll(pageable3);
    log.info("전체 건 수 : {}", result.getTotalElements());
    log.info("전체 페이지 수 : {}", result.getTotalPages());
    log.info("현재 페이지 : {}", result.getNumber());
    log.info("조회 건 수 : {}", result.getNumberOfElements());
    log.info("한 페이지의 건 수 : {}", result.getSize());
  }

  // entity -> DTO
  @Test
  void entityToDTO() {
    BoardEntity2 entity = boardRepository2.findById(1).orElse(null);
    if (entity == null) {
      return;
    }
    log.info("entity : {}", entity);

    // 1. 직접 변경하기
    BoardDTO dto1 = BoardDTO.builder()
        .bno(entity.getBno())
        .title(entity.getTitle())
        .content(entity.getContent())
        .writer(entity.getWriter())
        .regDate(entity.getRegDate())
        .updateDate(entity.getUpdateDate())
        .build();
    log.info("직접 변경하기 : {}", dto1);

    // 2. modelMapper 사용하기
    BoardDTO dto2 = modelMapper.map(entity, BoardDTO.class);
    log.info("modelMapper 사용 : {}", dto2);
  }

  // DTO -> entity
  @Test
  void DTOToEntity() {
    BoardDTO dto1 = BoardDTO.builder().build();
    BoardEntity2 entity = modelMapper.map(dto1, BoardEntity2.class);
    log.info("entity: {}", entity);
  }
}