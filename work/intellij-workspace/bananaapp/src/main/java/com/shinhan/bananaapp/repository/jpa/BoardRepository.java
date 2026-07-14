package com.shinhan.bananaapp.repository.jpa;

import com.shinhan.bananaapp.entity1.BoardEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.sql.Timestamp;
import java.util.List;

public interface BoardRepository
    extends CrudRepository<BoardEntity, Integer>,
    PagingAndSortingRepository<BoardEntity, Integer>,
    JpaRepository<BoardEntity, Integer> {

  List<BoardEntity> findByWriter(String writer);

  List<BoardEntity> findByBnoBetween(Integer bno1, Integer bno2);

  List<BoardEntity> findByWriterContainingAndBnoBetweenAndTitleContainingOrderByBnoDesc(String writer, Integer bno1, Integer bno2, String title);

  List<BoardEntity> findByRegDateAfter(Timestamp date);

  // JPQL (JPA Query Language)를 집적 작성
//  @Query("select b from BoardEntity b where b.bno between 1 and ?1")
  @Query("select b from BoardEntity b where b.bno between 1 and :bno")
  List<BoardEntity> customQuery(@Param("bno") int bno);
}
