package com.shinhan.bananaapp.onetomany;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PDSBoardRepository extends JpaRepository<PDSBoardEntity, Long> {
  @Query("select b from PDSBoardEntity b left join fetch b.files2")
  List<PDSBoardEntity> findAll2();

  @EntityGraph(attributePaths = "files2")
  @Query("select b from PDSBoardEntity b")
  List<PDSBoardEntity> findAll3();
}
