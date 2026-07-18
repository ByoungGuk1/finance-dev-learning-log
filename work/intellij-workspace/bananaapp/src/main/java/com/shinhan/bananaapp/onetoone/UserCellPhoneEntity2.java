package com.shinhan.bananaapp.onetoone;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TBL_USER_CELLPHONE2")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCellPhoneEntity2 {
  @Id //  @MapsId로 인해 실제로 생성되지는 않음, 주 테이블의 키와 타입이 동일해야한다.
  private Long id;
  private String model;
  private String phoneNumber;

  //대상 TABLE에서 식별자로 사용
  @MapsId // PK 면서 FK
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TBL_USER2_id")
  private UserEntity2 user;
}
