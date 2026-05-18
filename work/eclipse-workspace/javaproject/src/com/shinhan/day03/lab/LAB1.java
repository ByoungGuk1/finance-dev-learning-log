package com.shinhan.day03.lab;

import java.util.Arrays;

public class LAB1 {

	public static void main(String[] args) {
		int[] arr = {3,24,1,55,17,43,5};
		f_Q1(arr);
		System.out.println(Arrays.toString(arr));
		f_A1_asc(arr);
		System.out.println(Arrays.toString(arr));
		f_A1_desc(arr);
		System.out.println(Arrays.toString(arr));
		f_A1_usingMethod(arr);
		System.out.println(Arrays.toString(arr));
		f_Q2();
	}

	private static void f_Q1(int[] arr) {
//		주어진 일차원 배열의 내용을 오름차순으로 정렬하는 로직을 작성하시오.
//		(단, 로직은 main() 메서드에 작성하고 반드시 이중 for문을 사용하여 구현해야 한다.) 
	    for (int i = 0; i < arr.length - 1; i++) {
	        int minIndex = i;

	        for (int j = i + 1; j < arr.length; j++) {
	            if (arr[minIndex] > arr[j]) {
	                minIndex = j;
	            }
	        }

	        int temp = arr[i];
	        arr[i] = arr[minIndex];
	        arr[minIndex] = temp;
	    }
	}
	
	private static void f_A1_usingMethod(int[] arr) {
		Arrays.sort(arr);	//	Ascending
	}

	private static void f_A1_desc(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if(arr[i] < arr[j]) {
					int tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
				}
			}
		}
	}

	private static void f_A1_asc(int[] arr) {
		for(int data : arr) {
			System.out.print(data + " ");
		}
		System.out.println();
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if(arr[i] > arr[j]) {
					int tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
				}
			}
		}
	}

	private static void f_Q2() {
//		2차원 배열을 이용하여 두 행렬에 대한 차를 구할 수 있는 sub() 메소드와
//		결과를 출력하는 prn() 메소드를 ArrayTest 클래스 내에 구현하십시오.
		
		/*
		 * Class : ArrayTest
			Method 1 : public int[][] sub(int[][] a, int[][] b, int[][] c)
			-	행렬의 차를 구하기 위한 메소드. [ a – b = c ]
			
			배열 a    -     배열 b     =     배열 c
			30 30 30 30     1  2  3  4        29 28 27 26 
			40 40 40 40  -  5  6  7  8    =   35 34 33 32 
			50 50 50 50     9 10  11  12       41 40 39 38
			 
			Method 2 : public void prn(int[][] c)
			-	결과 배열 c 의 내용을 출력하기 위한 메소드. 
			-	아래 실행 결과 예시와 같이 각 행별로 줄바꿈을 하며, 각 열의 내용은 공백으로 구분.
		 * */
		int[][] a = {{30,30,30,30},{40,40,40,40},{50,50,50,50}};
		int[][] b = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
		int[][] c = new int[a.length][a[0].length];
		sub(a,b,c);
		prn_a(a);
		prn_a(b);
		prn_a(c);
	}
	
	private static void sub(int[][] a, int[][] b, int[][] c) {
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[i].length; j++) {
				c[i][j] = a[i][j] - b[i][j];
			}
		}
	}
	
	private static void prn_a(int[][] arr) {
		for(int[] array : arr) {
			System.out.println(Arrays.toString(array));
		}
	}

}
