package com.shinhan.posting.domain.request;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDTO {
  private String content;
  private String writer;
}
