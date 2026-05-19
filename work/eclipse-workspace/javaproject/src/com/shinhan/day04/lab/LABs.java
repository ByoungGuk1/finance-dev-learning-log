package com.shinhan.day04.lab;

import java.util.Arrays;

public class LABs {
	public static void main(String[] args) {
//		13번 문제 : arrData[0].substring(1);	//	substring method 한번 더 확인
		LABs q = new LABs();
		for(int i = 4; i < 15; i++) {
			if(i == 10)	{
				System.out.println(10 + "번 문제 출력 결과 입니다.");
				q.lab10(args);
				System.out.println();
				continue;
			}
			if(i == 6 || i == 11)	continue;
			q.callQuestion(i);			
		}
	}
	
	private void callQuestion(int n) {
		LABs q = new LABs();
		System.out.println(n + "번 문제 출력 결과 입니다.");
		switch(n) {
		case 4 -> {q.lab4();}
		case 5 -> {q.lab5();}
		case 7 -> {q.lab7();}
		case 8 -> {q.lab8();}
		case 9 -> {q.lab9();}
//		case 10 -> {q.lab10();}	//	메인의 아규먼트 필요
		case 12 -> {q.lab12();}
		case 13 -> {q.lab13();}
		case 14 -> {q.lab14();}
		}
		System.out.println();
	}
//
//	private void lab1() {
////		1 : name, scores
////		2 : 주소(해시값)
//	}
//
//	private void lab2() {
////		1 : NullPointerExeception
////		2 : str = "";
//	}
//
//	private void lab3() {
////		1. 18
////		2. " "
////		3. 8
////		4. Hello, Spring World!
////		5.  Java
//	}

	private void lab4() {
//		요구사항
//		  - 5명의 학생 점수 (85, 92, 76, 88, 95) 를 배열로 선언한다.
//		  - for 문을 사용하여 모든 점수를 한 줄에 하나씩 출력한다.
		int[] scores = {85,92,76,88,95};
		for(int score : scores) {
			System.out.println(score);
		}
	}

	private void lab5() {
		String[] fruits = {"사과", "바나나", "포도", "딸기", "수박"};
		for(String fruit : fruits) {
			System.out.print(fruit + " ");
		}
		System.out.println();
	}

//	private void lab6() {
////		1.
////			1-true
////			2-false
////			3-true
////		2. ????
//	}

	private void lab7() {
		int[] original = {10, 20, 30, 40, 50};
		int[] copied   = new int[3];
		// System.arraycopy() 호출 작성
		System.arraycopy(original, 1, copied, 0, 3);
		// 복사된 배열 출력
		System.out.println(Arrays.toString(copied));
	}

	private void lab8() {
		String printFormat = "%s - 총점: %d, 평균: %.1f\n";
		String[] names = {"김철수", "이영희", "박민준"};
		int[][] scores = {
		    {85, 90, 78},
		    {92, 88, 95},
		    {76, 82, 80}
		};

		int sum = 0;
		// 총점과 평균 계산 후 출력 (소수점 1자리)
		for(int i = 0; i < scores.length; i++) {
			for(int score : scores[i]) {
				sum += score;
			}
			System.out.printf(printFormat, names[i], sum, (double) sum / scores[i].length);
			sum = 0;
		}
	}

	private void lab9() {
        Season current = Season.SUMMER;
        
        // switch 문 작성
        switch(current) {
        case SPRING -> System.out.println("봄입니다.");
        case SUMMER -> System.out.println("여름입니다. 시원한 음료를 드세요!");
        case FALL -> System.out.println("가을입니다.");
        case WINTER -> System.out.println("겨울입니다.");
        default -> {System.err.println("입력 값이 계절이 아닙니다.");}
        }
        
//      배열로 받기
        Season[] seasonArray = Season.values();
        System.out.println(Arrays.toString(seasonArray));
	}

	private void lab10(String[] args) {
//		명령행 인수(args)로 두 정수를 입력받아 사칙연산 결과를 출력하는 프로그램을 작성하시오.
		int num1 = Integer.parseInt(args[0]);
		int num2 = Integer.parseInt(args[1]);
		System.out.println("더하기: " + (num1 + num2));
		System.out.println("빼기: " + (num1 - num2));
		System.out.println("곱하기: " + num1 * num2);
		System.out.println("나누기: " + num1 / num2);
	}

//	private void lab11() {
////		1.
////			1-true
////			2-false
////			3-Alice
////		2. null
////		3. 0번의 주소를 이미 갖고 있고 있기 때문에 0번 인덱스가 다른 참조로 바뀌어도 변하지 않는다.
////		4. 스택에 배열의 이름들 = names -> 힙에 배열 구조 생성???
//	}

	private void lab12() {
        int[] arr = {1, 2, 3};
        arr = addElement(arr, 4);
        arr = addElement(arr, 5);
        // 예상 출력: 1 2 3 4 5
        for (int v : arr) System.out.print(v + " ");
        System.out.println();
	}
	static int[] addElement(int[] original, int newValue) {
		int[] result = Arrays.copyOf(original, original.length + 1);
		result[result.length - 1] = newValue;
		return result;
	}

	private void lab13() {
//		CSV(Comma Separator Value) -> ','를 구분자로 데이터를 작성
//		XML(Extensible Markup Langueage) -> 실제 data 보다 3배가량 크기가 커진다.
//		JSON(JavaScript Object Notation)
		String printFormat = "이름: %s\n나이: %s\n지역: %s\n직업: %s\n";
		String data = "홍길동,25,서울,개발자";
//		String[] category = {"이름","나이","지역","직업"};
		/*
			1. split() 으로 데이터를 분리하시오.
			2. 각 항목을 이름, 나이, 지역, 직업으로 출력하시오.
			3. 이름의 성(첫 글자)만 * 로 가려서 출력하시오.  예) *길동
			
			예상 출력
			  이름:  *길동
			  나이:  25
			  지역:  서울
			  직업:  개발자
		 */
		
        // 1. split() 으로 분리
		String[] arrData = data.split(",");

		// 3. 이름 마스킹 처리
		StringBuffer maskedName = new StringBuffer("*");
		for (int i = 1; i < arrData[0].length(); i++) {
			maskedName.append(arrData[0].charAt(i));
		}
		arrData[0] = maskedName.toString();
//		arrData[0] = "*" + arrData[0].substring(1);	//	substring method 한번 더 확인
		
		// 2. 각 항목 출력
		System.out.printf(printFormat, arrData[0], arrData[1], arrData[2], arrData[3]);
//		for (int i = 0; i < arrData.length; i++) {
//			System.out.printf("%s: %s\n", category[i], arrData[i]);
//		}
	}

	private void lab14() {
		/*
		요구사항
		  1. Grade enum 정의:  A (90 이상),  B (80 이상),  C (70 이상),  F (70 미만)
		  2. 학생 이름 배열과 점수 배열을 별도로 관리한다.
		  3. 각 학생의 점수를 기반으로 Grade 등급을 계산하여 출력한다.
		  4. 전체 평균 점수와 최고점/최저점 학생을 출력한다.
		  5. NullPointerException 이 발생하지 않도록 방어 코드를 적용한다.
		*/
		/*
		 * 출력결과
			=== 성적 조회 결과 ===
			홍길동:  92점  →  A
			김영수:  78점  →  C
			이미나:  85점  →  B
			박준호:  61점  →  F

			전체 평균:  79.0점
			최고점:  92점  (홍길동)
			최저점:  61점  (박준호)
		 */
        String[] names = {"홍길동", "김영수", "이미나", "박준호"};
        int[] scores = {92, 78, 85, 61};

//		출력로직
    	printDatas(names, scores);
	}
	
    static Grade getGrade(int score) {
    	Grade result = null;
    	
    	result = score >= 90 ? Grade.A : score >= 80 ? Grade.B : score >= 70 ? Grade.C : Grade.F; 
    	
    	return result;
    }
    
    private static void printDatas(String[] names, int[] scores) {
//		출력로직
    	String printFormat = "%s:\t%d점\t→\t%s\n";
    	double scoreAvg = 0.0;
    	int totalScore = 0;
    	int maxScore = Integer.MIN_VALUE;
    	int minScore = Integer.MAX_VALUE;
    	String maxScoreMemberName = null;
    	String minScoreMemberName = null;
    	
    	if(names == null || scores == null || names.length != scores.length) {
    		System.err.println("이름과 점수의 값이 모두 들어갔는지 확인해주세요");
    		return;
    	}
    	
        System.out.println("=== 성적 조회 결과 ===");
        
        for (int i = 0; i < names.length; i++) {
        	if(maxScore < scores[i]) {
        		maxScore = scores[i];
        		maxScoreMemberName = names[i];
        	}
        	if(minScore > scores[i]) {
        		minScore = scores[i];
        		minScoreMemberName = names[i];
        	}
        	totalScore += scores[i];
			System.out.printf(printFormat, names[i], scores[i],getGrade(scores[i]));
		}
        scoreAvg = (double)totalScore / names.length;
        System.out.println();
        System.out.printf("전체 평균: %.1f\n", scoreAvg);
        System.out.println("최고점: " + maxScore + "점 (" + maxScoreMemberName + ")");
        System.out.println("최저점: " + minScore + "점 (" + minScoreMemberName + ")");
    }

}
