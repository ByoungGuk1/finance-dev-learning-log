package com.shinhan.bananaapp.onetoone;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TBL_USER_CELLPHONE1")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCellPhoneEntity1 {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String model;
  private String phoneNumber;

  // 일반적인 RDB 상태와 유사
  // 비식별자로 대상 테이블에서 참조하기
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "TBL_USER1_id")
  private UserEntity1 user;
}
