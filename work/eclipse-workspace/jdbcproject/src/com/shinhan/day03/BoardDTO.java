package com.shinhan.day03;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 11. 오후 12:17:05 설명 : BoardDTO
 */
// JavaBeans 기술을 사용하는 JSP, Mybatis, Spring에서 column 이름이 같으면 mapping이 쉽다.
// DTO (Data Transfer Object) : Data 전송 목적으로 만듦
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BoardDTO {
	private int bno;
	private String title;
	private String contants;
	private String writer;
	private Date regdate;
	private Date updatedate;
}
