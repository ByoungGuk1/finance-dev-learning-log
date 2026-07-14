package com.shinhan.bananaapp.service.jpa;

import com.shinhan.bananaapp.entity1.BoardEntity;
import com.shinhan.bananaapp.repository.jpa.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
  private final BoardRepository boardRepository;

  // 전체 조회
  public List<BoardEntity> findAll() {
    return boardRepository.findAll();
  }

  //1건 조회
  public BoardEntity findById(int id) {
    return boardRepository.findById(id).orElse(null);
  }

  //입력 + 수정
  public BoardEntity save(BoardEntity boardEntity) {
    return boardRepository.save(boardEntity);
  }

  //삭제
  public BoardEntity delete(int id) {
    BoardEntity boardEntity = boardRepository.findById(id).orElse(null);
    if (boardEntity != null) {
      boardRepository.delete(boardEntity);
    }
    return boardEntity;
  }

  public List<BoardEntity> findByWriter(String writer) {
    return boardRepository.findByWriter(writer);
  }

  public List<BoardEntity> findByBnoBetween(Integer bno1, Integer bno2) {
    return boardRepository.findByBnoBetween(bno1, bno2);
  }

  public List<BoardEntity> findByWriterContainingAndBnoBetweenAndTitleContainingOrderByBnoDesc(String writer, Integer bno1, Integer bno2, String title) {
    return boardRepository.findByWriterContainingAndBnoBetweenAndTitleContainingOrderByBnoDesc(writer, bno1, bno2, title);
  }

  public List<BoardEntity> findByRegDateAfter(Timestamp date) {
    return boardRepository.findByRegDateAfter(date);
  }

  public List<BoardEntity> customQuery(int bno) {
    return boardRepository.customQuery(bno);
  }
}
