package com.shinhan.bananaapp.repository.jpa;

import com.shinhan.bananaapp.entity3.MemberEntity;
import com.shinhan.bananaapp.entity3.ProfileEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
  // 1. 단순 조인 (X)
//  @Query("select p from ProfileEntity p join p.member")
  // 2. fetch join (O)
//  @Query("select p from ProfileEntity p join fetch p.member")
  // 3. EntityGraph (O)
  @EntityGraph(attributePaths = "member")
//  ㄴ> 해당 구문 생략 가능 -> @Query("select p from ProfileEntity p")
  List<ProfileEntity> findAll();


  // 특정 멤버의 profile 조회
  // 규칙에 맞는 함수 정의 => JPQL
  @Query("select p from ProfileEntity p join fetch p.member where p.member = :member")
  List<ProfileEntity> findByMember(@Param("member") MemberEntity member);
}
