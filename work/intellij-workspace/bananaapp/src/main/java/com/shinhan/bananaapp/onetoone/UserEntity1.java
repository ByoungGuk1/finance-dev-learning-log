package com.shinhan.bananaapp.onetoone;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TBL_USER1")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity1 {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
}
