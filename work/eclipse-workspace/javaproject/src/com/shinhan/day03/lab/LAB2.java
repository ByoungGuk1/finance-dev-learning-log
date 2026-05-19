package com.shinhan.day03.lab;

public class LAB2 {
//	과제
	public static void main(String[] args) {
		f_Q1();
		f_Q2();
	}

	private static void f_Q1() {
		String str1 = "he**o wor*d";
		String str2 = "hello-world";
		String str3 = "hello world";
		
		str1 = myReplace(str1, '*', 'l');
		str2 = myReplace(str2, '-', ' ');
		str3 = myReplace(str3, '*', 'a');
		
		System.out.println("str1 = " + str1);
		System.out.println("str2 = " + str2);
		System.out.println("str3 = " + str3);
	}

	private static void f_Q2() {
		String addr = "서울시,강남구,역삼동,삼성SDS멀티캠퍼스";
		String[] addrArr = split(addr, ',');
		System.out.println("배열 크기 : " + addrArr.length);
		for(int i=0; i<addrArr.length; i++){
			System.out.print(addrArr[i] + " ");
		}
	}

	private static String myReplace(String str, char oldChar, char newChar) {
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == oldChar) {
				result.append(newChar);
			} else {
				result.append(str.charAt(i));
			}
		}
		return result.toString();
	}

	private static String[] split(String str, char separator) {
		String[] result = null;
		StringBuilder  data = new StringBuilder();
		int arrSize = 1;
		int index = 0;

		for(char ch : str.toCharArray()) {
			if(ch == separator) {
				arrSize++;
			}
		}

		result = new String[arrSize];

		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) != separator) {
				data.append(str.charAt(i));
			} else {
				result[index++] = data.toString();
				data = new StringBuilder();
			}
		}
		result[index] = data.toString();

		return result;
	}

}
