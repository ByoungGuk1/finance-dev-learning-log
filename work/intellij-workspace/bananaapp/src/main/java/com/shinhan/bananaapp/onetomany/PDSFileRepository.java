package com.shinhan.bananaapp.onetomany;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PDSFileRepository extends JpaRepository<PDSFileEntity, Long> {
  // DML 문장을 Query에 작성하는 경우 반드시 @Modifying, @Transactional 사용
  @Transactional
  @Modifying
  @Query("update PDSFileEntity f set f.pdsfilename = :fname where f.fno = :fno")
  int updateFileNameByFno(@Param("fname") String fname, @Param("fno") long fno);
}
