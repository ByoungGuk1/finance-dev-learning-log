package com.shinhan.bananaapp.repository.jpa;

import com.shinhan.bananaapp.entity3.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {
  List<MemberEntity> findByMname(String mname);

  MemberEntity findByMid(String mid);
}
