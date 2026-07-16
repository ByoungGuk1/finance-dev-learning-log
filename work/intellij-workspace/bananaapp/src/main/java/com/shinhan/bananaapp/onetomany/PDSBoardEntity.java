package com.shinhan.bananaapp.onetomany;

import com.shinhan.bananaapp.entity2.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "files2")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_pdsboard")
public class PDSBoardEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long pid;
  private String pname;
  private String pwriter;

  @BatchSize(size = 10) // => where pdsno in (?, ?, ? ...)
  @OneToMany(
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      // orphanRemoval = false(default) : 부모에서 제거해도 기존 테이블에 남음 -> 참조만 잃어버림
      fetch = FetchType.LAZY
  )
  @JoinColumn(name = "pdsno") // tbl_pdsfiles.pdsno FK
  private List<PDSFileEntity> files2 = new ArrayList<>();
}

