package com.one.q3;

import java.util.Arrays;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 2. 오전 11:50:24 설명 : PlayGround
 */

public class PlayGround {
	public static void main(String[] args) {
		int ticketCount = 10;
		int[][] requests = { { 2, 3 }, { 1, 7 }, { 2, 4 }, { 3, 5 } };
		int[][] checkedPeoples = new int[requests.length][2];
		int checkedIndex = 0;

		while (checkedPeoples[checkedPeoples.length - 1][0] != 0) {
			int[] highTear = new int[2];
			for (int[] people : requests) {
				highTear = people[0] < highTear[0] ? people : highTear;
			}
		}

		for (int[] people : checkedPeoples) {
			System.out.print(Arrays.toString(people));
		}
		System.out.println();
	}
}
