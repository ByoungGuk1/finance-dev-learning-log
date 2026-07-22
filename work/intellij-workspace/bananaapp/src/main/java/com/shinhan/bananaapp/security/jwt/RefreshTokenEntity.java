package com.shinhan.bananaapp.security.jwt;

import com.shinhan.bananaapp.entity2.BaseEntity;
import com.shinhan.bananaapp.entity3.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

@ToString(exclude = "member")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // 단방향 연관관계 (MemberEntity 참조)
  @ManyToOne(fetch = FetchType.LAZY) //지연로딩
  @JoinColumn(name = "member_id", nullable = false)
  private MemberEntity member;

  @Column(nullable = false, unique = true, length = 500)
  private String refreshToken;
}
