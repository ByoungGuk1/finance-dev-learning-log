//package com.shinhan.bananaapp.manytomany;
//
//import com.shinhan.bananaapp.entity2.BaseEntity;
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Table(name = "tbl_free_reply")
//@Getter
//@Setter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString(exclude = "board")         // 무한루프 방지 필수!
//public class FreeReplyEntity extends BaseEntity {
//
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  private Long id;
//  private String reply;
//  private String writer;
//
//  // FK 실제 관리 — board_bno 컬럼 자동 생성
//  @ManyToOne(fetch = FetchType.LAZY)
//  private FreeBoardEntity board;
//}
