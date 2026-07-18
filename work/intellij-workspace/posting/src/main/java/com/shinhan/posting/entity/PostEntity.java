package com.shinhan.posting.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@ToString(exclude = "commentList")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TBL_POST")
@EqualsAndHashCode(of = "id")
@Builder
public class PostEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String content;
  private String writer;
  @CurrentTimestamp
  @Column(updatable = false)
  private LocalDateTime createdDate;
  @UpdateTimestamp
  private LocalDateTime updatedDate;

  @Builder.Default
  @OneToMany(
      mappedBy = "post",
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  private List<CommentEntity> commentList = new ArrayList<>();

  public void addComment(CommentEntity comment) {
    commentList.add(comment);
    comment.setPost(this);
  }
}
