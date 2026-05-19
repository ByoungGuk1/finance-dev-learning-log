package com.shinhan.day04;
/*
 *	>java LAB2review
 *	JVM 이 class load 한다. static 이 Method 영역으로 들어간다
 *	검증
 *	main 시작 
 */
public class LAB2review {
//	[modifier] returnType 메서드이름(변수타입 매개변수) { ... }
	public static void main(String[] args) {
		f_A1();
		f_A2();
	}

	private static void f_A1() {
//		String 을 만드는 방법
		String s1 = "문자열";
		String s2 = new String("문자열");
		char[] arr = new char[] {'문', '자', '열'};	//	new char[] -> 생략 가능
		String s3 = new String(arr);
		byte[] arr2 = {65, 66, 67, 68};
		String s4 = new String(arr2);
		int data = 100;
		String s5 = String.valueOf(data);
		StringBuffer sb = new StringBuffer("문자열");
		String s6 = new String(sb);
		
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s4);
		System.out.println(s5);
		System.out.println(s6);
		
		System.out.println("문자열에 특정문자 변경하는 테스트");
		System.out.println("-------------Sample 1 --------------");
		String str1=myReplace("hello world",'l','*');
		System.out.println(str1);
		
		System.out.println("-------------Sample 2 --------------");
		String str2=myReplace("hello world",' ','-');
		System.out.println(str2);
		
		System.out.println("-------------Sample 3 --------------");
		String str3=myReplace("hello world",'a','*');
		System.out.println(str3);

	}

	private static void f_A2() {
		LAB2review loadClass = new LAB2review();
		String addr = "서울시,강남구,역삼동,삼성SDS멀티캠퍼스";
		String[] addrArr = loadClass.split(addr, ',');
		System.out.println("배열 크기 : " + addrArr.length);
		for(int i=0; i<addrArr.length; i++){
			System.out.print(addrArr[i] + " ");
		}
	}

	private static String myReplace(String str, char oldChar, char newChar) {
		String result = null;
		char[] arr = str.toCharArray();
		for(int i = 0; i < arr.length; i++) {
			arr[i] = arr[i] == oldChar ? newChar : arr[i];
		}
		result = new String(arr);
		return result;
	}

	private String[] split(String str, char separator) {
		String[] result = null;
		char[] charArray = str.toCharArray();
		int arrSize = 1;
		int index = 0;
		StringBuilder data = new StringBuilder();

		for(char ch : charArray) {
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
