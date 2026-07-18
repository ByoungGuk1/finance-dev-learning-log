package com.shinhan.bananaapp.security;

import com.shinhan.bananaapp.entity3.MemberEntity;
import com.shinhan.bananaapp.entity3.MemberRole;
import com.shinhan.bananaapp.repository.jpa.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@Slf4j
class MemberTest {
  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Test
  void insertMember() {
    MemberEntity memberEntity1 = MemberEntity.builder().mid("hong").mname("홍길동").mpassword(passwordEncoder.encode("1234")).mrole(MemberRole.USER).build();
    MemberEntity memberEntity2 = MemberEntity.builder().mid("lee").mname("이순신").mpassword(passwordEncoder.encode("1234")).mrole(MemberRole.USER).build();
    MemberEntity memberEntity3 = MemberEntity.builder().mid("jang").mname("장보고").mpassword(passwordEncoder.encode("1234")).mrole(MemberRole.USER).build();
    memberRepository.save(memberEntity1);
    memberRepository.save(memberEntity2);
    memberRepository.save(memberEntity3);
  }
}