package com.shinhan.bananaapp.repository.jpa;

import com.shinhan.bananaapp.entity1.BoardEntity2;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BoardRepository2
    extends JpaRepository<BoardEntity2, Integer> {
  List<BoardEntity2> findByWriter(String writer);

  @Query("SELECT B FROM BoardEntity2 B WHERE B.bno = :bno")
  Optional<BoardEntity2> customQuery(@Param("bno") int bno);

  @Query("SELECT B FROM BoardEntity2 B WHERE B.bno = ?1")
  Optional<BoardEntity2> customQuery2(int bno);

  //작성자 별 보드의 갯수
  @Query("SELECT b.writer AS writer, COUNT(b.title) AS count FROM BoardEntity2 b GROUP BY b.writer")
  List<Map<String, Long>> customCountByWriter();

  @Query("SELECT b.writer AS writer, COUNT(b.title) AS count FROM BoardEntity2 b GROUP BY b.writer")
  List<Object[]> customArray();

  @Query("SELECT new com.shinhan.bananaapp.repository.jpa.Board2DTO(b.writer, COUNT(b.title)) FROM BoardEntity2 b GROUP BY b.writer")
  List<Board2DTO> customDTO();

  //interface 사용
  @Query("SELECT b.writer AS writer, COUNT(b.title) AS count FROM BoardEntity2 b GROUP BY b.writer")
  List<Board2Interface> customInterface();
}
