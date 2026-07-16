package com.shinhan.bananaapp.entity2;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_webboard")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WebBoardEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long bno;
  private String title;
  private String content;
  private String writer;
}

