package com.shinhan.bananaapp.onetomany;

import com.shinhan.bananaapp.entity2.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_pdsfiles")
public class PDSFileEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long fno;
  private String pdsfilename;
  // pdsno FK는 @JoinColumn이 PDSBoard 쪽에서 관리
}

