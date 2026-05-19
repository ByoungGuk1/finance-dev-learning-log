package com.shinhan.day04;

import java.util.Scanner;

public class 확인문제5장 {
	public static void main(String[] args) {
		f_Q9();
	}
	private static void f_Q9() {
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		int student = 0;
		int[] scores = null;
		
		while(flag) {
			System.out.println(
					"-----------------------------------------------------\n"
					+ "1.학생수 | 2.점수입력 | 3.점수리스트 | 4.분석 | 5.종료\n"
					+ "-----------------------------------------------------"
					);
			System.out.print("선택> ");
			int selectNum = Integer.parseInt(sc.nextLine());
			switch(selectNum) {
			case 1 -> {
				System.out.print("학생수> ");
				student = Integer.parseInt(sc.nextLine());
				scores = new int[student];
			}
			case 2 -> {
				if(student == 0) {
					System.err.println("학생수를 먼저 입력해주세요.");
					break;
				}
				for(int i = 0; i < student; i++) {
					System.out.print("scores[" + i + "]>");
					int score = Integer.parseInt(sc.nextLine());
					scores[i] = score;
				}
			}
			case 3 -> {
				if(scores == null) {
					System.err.println("점수입력을 먼저 진행해주세요.");
					break;
				}
				for(int i = 0; i < student; i++) {
					System.out.printf("scores[%d]: %d\n", i, scores[i]);
				}
			}
			case 4 -> {
				if(scores == null) {
					System.err.println("점수입력을 먼저 진행해주세요.");
					break;
				}
				int maxScore = 0;
				int sum = 0;
				double avg = 0.0;
				for(int selectScore : scores) {
					maxScore = maxScore < selectScore ? selectScore : maxScore;
					sum += selectScore;
				}
				avg = (double) sum / student;
				System.out.println("최고 점수: " + maxScore);
				System.out.printf("평균 점수: %.1f\n", avg);
			}
			case 5 -> {
				flag = false;
				System.out.println("프로그램 정상 종료");
			}
			default -> {System.err.println("입력 숫자를 확인해주세요.");}
			}
		}
		sc.close();
	}
}
