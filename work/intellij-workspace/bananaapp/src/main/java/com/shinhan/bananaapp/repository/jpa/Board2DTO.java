package com.shinhan.bananaapp.repository.jpa;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board2DTO {
  String writer;
  long count;
}
