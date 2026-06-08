package com.shinhan.test;

import java.util.Arrays;
import java.util.HashMap;

public class Prob1 {
	public static void main(String[] args) {
		String[] array = { "황남기85점", "조성호89점", "한인성88점", "독고정진77점" };
		printMaxScore(array);
	}

	private static void printMaxScore(String[] array) {
		HashMap<String, Integer> stdMap = new HashMap<>();
		Arrays.stream(array).forEach(data -> {
			char[] charData = data.toCharArray();
			String name = "";
			String score = "";
			for (int i = 0; i < charData.length - 1; i++) {
				if (charData[i] >= '0' && charData[i] <= '9') {
					score += charData[i];
				} else {
					name += charData[i];
				}
			}
			stdMap.put(name, Integer.valueOf(score));
		});
		stdMap.entrySet().stream().max((o1, o2) -> o1.getValue() - o2.getValue()).ifPresent((entry) -> {
			System.out.println("최고점수는 " + entry.getKey() + "님 " + entry.getValue() + "점 입니다.");
		});

	}
}
