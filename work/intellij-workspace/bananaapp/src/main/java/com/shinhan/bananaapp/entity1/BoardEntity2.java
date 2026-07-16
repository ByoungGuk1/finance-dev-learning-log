package com.shinhan.bananaapp.entity1;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "bno")
@Builder
@Table(name = "TBL_BOARD2")
public class BoardEntity2 {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer bno;
  private String title;
  private String content;
  private String writer;
  @Column(updatable = false)
  @CreationTimestamp
  private Timestamp regDate;
  @UpdateTimestamp
  private Timestamp updateDate;
}
