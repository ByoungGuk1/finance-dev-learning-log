package com.shinhan.day14;

import java.util.Comparator;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 4. 오전 11:30:24 설명 : ComparatorImplB
 */
public class ComparatorImplB implements Comparator<String> {
	@Override
	public int compare(String o1, String o2) {
		return o2.compareTo(o1);
	}
}
