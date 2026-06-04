package com.shinhan.day14;

import java.util.Comparator;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 4. 오전 11:27:51 설명 : ComparatorImplA
 */
public class ComparatorImplA implements Comparator<Integer> { // 같은 타입의 두 객체를 비교
	@Override
	public int compare(Integer o1, Integer o2) {
//		desc
		return o2 - o1;
	}
}
