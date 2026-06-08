package com.shinhan.test;

import java.util.Arrays;

public class Prob2 {
	public static void main(String[] args) {
		System.out.println(leftPad("Samsung", 10, '#'));
		System.out.println(leftPad("SDS", 5, '*'));
		System.out.println(leftPad("Multicampus", 5, '@'));
	}

	public static String leftPad(String str, int size, char fillChar) {
		String result = "";
		try {
			if (str.length() > size) {
				throw new IllegalSizeException("문자열의 길이가 size보다 큽니다.");
			}
			char[] original = str.toCharArray();
			char[] newArray = new char[size];
			Arrays.fill(newArray, fillChar);
			for (int i = 0; i < original.length; i++) {
				newArray[i + size - original.length] = original[i];
			}
			result = String.valueOf(newArray);
		} catch (IllegalSizeException | ArrayIndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
}

class IllegalSizeException extends RuntimeException {
	public IllegalSizeException(String errorMessage) {
		super(errorMessage);
	}
}
