package com.shinhan.bananaapp.repository.jpa;

import com.shinhan.bananaapp.entity3.MemberEntity;
import com.shinhan.bananaapp.entity3.MemberRole;
import com.shinhan.bananaapp.entity3.ProfileEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
class ManyToOneTest {
  @Autowired
  private ProfileRepository profileRepository;
  @Autowired
  private MemberRepository memberRepository;

  @Test
  void insertMember() {
    MemberEntity memberEntity1 = MemberEntity.builder().mid("hong").mname("홍길동").mpassword("1234").mrole(MemberRole.USER).build();
    MemberEntity memberEntity2 = MemberEntity.builder().mid("lee").mname("이순신").mpassword("1234").mrole(MemberRole.USER).build();
    MemberEntity memberEntity3 = MemberEntity.builder().mid("jang").mname("장보고").mpassword("1234").mrole(MemberRole.USER).build();
    memberRepository.save(memberEntity1);
    memberRepository.save(memberEntity2);
    memberRepository.save(memberEntity3);
  }

  @Test
  void findAllMembers() {
    memberRepository.findAll().stream().map(MemberEntity::toString).forEach(log::info);
  }

  @Test
  void insertProfile() {
    MemberEntity foundMember1 = memberRepository.getReferenceById("jang");  // select문을 사용하지 않아서 대량인 경우 유용
    IntStream.rangeClosed(1, 5).forEach(i -> {
      ProfileEntity pe = ProfileEntity.builder().pfile("프로파일-" + i).pcurrent(i == 5).member(foundMember1).build();
      profileRepository.save(pe);
    });

    MemberEntity foundMember2 = memberRepository.findByMid("lee");
    if (foundMember2 == null) {
      log.info("tbl_member 조회 실패");
      return;
    }
    IntStream.rangeClosed(1, 3).forEach(i -> {
      ProfileEntity pe = ProfileEntity.builder().pfile("프로파일-" + i).pcurrent(i == 3).member(foundMember2).build();
      profileRepository.save(pe);
    });
  }

  @Test
//  @Transactional
  void findAllProfiles() {
    List<ProfileEntity> pe = profileRepository.findAll();
    pe.stream().map(ProfileEntity::toString).forEach(log::info);

//    Map<String, MemberEntity> member = new HashMap<>();
//    pe.forEach(d -> {
//      member.put(d.getMember().getMid(), d.getMember());
//    });
//    member.forEach((k, v) -> {
//      log.info("{} : {}", k, v.toString());
//    });
  }

  @Test
  void findProfileByMember() {
    MemberEntity foundMember = memberRepository.findByMid("jang");
    if (foundMember == null) {
      return;
    }
    List<ProfileEntity> profileEntityList = profileRepository.findByMember(foundMember);
    if (profileEntityList == null) {
      return;
    }
    profileEntityList.stream()
        .map(d -> d.toString() + "-> Member: " + d.getMember().toString())
        .forEach(log::info);
  }
}