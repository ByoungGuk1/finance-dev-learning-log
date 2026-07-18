package com.shinhan.posting.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@ToString(exclude = "post")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TBL_COMMENT")
@EqualsAndHashCode(of = "id")
@Builder
public class CommentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String content;
  private String writer;
  @CurrentTimestamp
  @Column(updatable = false)
  private LocalDateTime createdDate;
  @UpdateTimestamp
  private LocalDateTime updatedDate;

  @ManyToOne(
      fetch = FetchType.LAZY
  )
  @JoinColumn(name = "FK_TBL_POST_ID")
  private PostEntity post;
}
