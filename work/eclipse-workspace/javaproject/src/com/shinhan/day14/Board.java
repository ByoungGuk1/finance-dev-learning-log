package com.shinhan.day14;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
class Board {
	String title;
	String content;
	String writer;
}