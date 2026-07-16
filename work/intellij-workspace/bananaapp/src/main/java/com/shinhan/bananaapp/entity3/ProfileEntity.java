package com.shinhan.bananaapp.entity3;

import com.shinhan.bananaapp.entity2.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

// 1 명의 멤버는 N 개의 프로파일을 가진다.
// DB는 반드시 Profile이 참조. (FK)
// 자바의 경우 Member에서도 참조할 수 있다.
@Entity
@Table(name = "tbl_profile")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "pno")
@ToString(exclude = "member")   // LAZY 프록시 접근 방지
public class ProfileEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long pno;
  private String pfile;
  private boolean pcurrent;

  // FK 실제 관리 — @JoinColumn 을 생략하면 member_mid 컬럼 자동 생성
  // eager(default) : 해당 엔티티 실행시 항상 가져오기
  //  => Profile 이 3명 멤버의 data라면, Profile 1번 Member 3번 select 실행
  // lazy : 엔티티 실행 시 가져오지 않지만, member를 조회하는 경우 가져오기
  // lazy 로딩 시 주의 사항 => 해당 칼럼은 toSting 에서 제외
  // Exception 발생을 막기 위해 @Transactional 추가
  // 문제점은 select문 사용시 N+1 문제 발생
  // 이를 해결하기 위해 @EntityGraph 또는 fetch join 사용
  @ManyToOne(fetch = FetchType.LAZY)  //default값은 EAGER,  LAZY 필수!
  private MemberEntity member;
}