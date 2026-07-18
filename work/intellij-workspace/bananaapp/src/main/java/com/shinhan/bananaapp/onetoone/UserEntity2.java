package com.shinhan.bananaapp.onetoone;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TBL_USER2")
@Getter
@Setter
@ToString(exclude = "cellphone")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity2 {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  @OneToOne(
      mappedBy = "user",
      orphanRemoval = true,
      cascade = CascadeType.ALL
  )
  private UserCellPhoneEntity2 cellphone;
}
