package com.shinhan.day11.lab3;

import java.util.Arrays;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 5. 29. 오후 4:00:54 설명 : Median
 */
public class Median {
//	입력 받은 숫자 배열 중 평균값에 제일 가까운 중간 값을 계산하는 클래스 입니다.
//	int findMedian(int[] values) 메소드를 완성하십시오 
	public static void main(String[] args) {
		Median median = new Median();
		int[] values1 = { 10, 4, 53, 63, 17, 37, 52, 16, 33, 65 };
		System.out.println("input : " + Arrays.toString(values1));
		System.out.println("median : " + median.findMedian(values1));
		System.out.println("===============================");

		int[] values2 = { 32, 53, 52, 76, 15, 98, 76, 65, 36, 10 };
		System.out.println("input : " + Arrays.toString(values2));
		System.out.println("median : " + median.findMedian(values2));
	}

	public int findMedian(int[] values) {
		int result = 0;
		double avg = 0.0;
		int sum = 0;

		for (int number : values) {
			sum += number;
		}

		avg = (double) sum / values.length;

		for (int number : values) {
			result = Math.abs((double) avg - result) >= Math.abs((double) avg - number) ? number : result;
		}

		return result;
	}
}
