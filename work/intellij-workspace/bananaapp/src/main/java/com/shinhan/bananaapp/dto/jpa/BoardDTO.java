package com.shinhan.bananaapp.dto.jpa;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "bno")
@Builder
public class BoardDTO {
  private Integer bno;
  private String title;
  private String content;
  private String writer;
  private Timestamp regDate;
  private Timestamp updateDate;

  private String comment;
}
