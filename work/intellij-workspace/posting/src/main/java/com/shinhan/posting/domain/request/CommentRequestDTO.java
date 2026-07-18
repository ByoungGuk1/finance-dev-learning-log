package com.shinhan.posting.dto.request;

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
