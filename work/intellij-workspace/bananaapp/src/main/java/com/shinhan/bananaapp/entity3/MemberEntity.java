package com.shinhan.bananaapp.entity3;

import com.shinhan.bananaapp.entity2.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_member")
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(of = "mid")
@NoArgsConstructor
@AllArgsConstructor
public class MemberEntity extends BaseEntity {
  @Id
  private String mid;           // 회원 ID (PK)
  private String mname;
  private String mpassword;     //Spring Security는 암호화 되지 않은 비밀번호는 사용 불가
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private MemberRole mrole;
}
