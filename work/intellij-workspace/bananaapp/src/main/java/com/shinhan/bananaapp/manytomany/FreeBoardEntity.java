package com.shinhan.bananaapp.manytomany;

import com.shinhan.bananaapp.entity2.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_free_board")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "replyList")    // 무한루프 방지 필수!
public class FreeBoardEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String content;
  private String writer;

  // mappedBy = FreeReplyEntity의 필드명 "board"
  // 메어있음 -> 참조하는 테이블에 column이 결정
  @BatchSize(size = 2)
  @OneToMany(
      mappedBy = "board",
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY,
      orphanRemoval = true
  )
  private List<FreeReplyEntity> replyList = new ArrayList<>();
}
