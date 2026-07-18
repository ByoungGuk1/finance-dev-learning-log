package com.shinhan.bananaapp.onetoone;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TBL_USER")
@Getter
@Setter
@ToString(exclude = "cellphone")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  @JoinColumn(name = "FK_TBL_USER_CELLPHONE_id")
  @OneToOne(
      cascade = CascadeType.ALL  // 영속성 전이
  )
  private UserCellPhoneEntity cellphone;
}
