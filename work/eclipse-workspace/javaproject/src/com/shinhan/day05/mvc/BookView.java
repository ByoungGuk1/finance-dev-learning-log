package com.shinhan.day05.mvc;

//	view : 추후 HTML로 만들 예정
public class BookView {
//	1. Book 여러권의 정보를 출력
	public static void printBookListInfo(BookDTO[] bookList) {
		System.out.println("=== 책들의 정보 ===");
		for(BookDTO book : bookList) {
			System.out.println(book);
		}
	}
	
//	특정 위치의 책의 정보를 출력
	public static void printBookOfIndex(BookDTO[] bookList, int index) {
		System.out.println("==" + index + "번째 책의 정보 ===");
		System.out.println(bookList[index]);
	}
	
	public static void printAllBookTitle(BookDTO[] bookList) {
		System.out.println("=== 책 목록 ===");
		for(BookDTO book : bookList) {
			System.out.println(book.getTitle());
		}
	}
	
	public static void printBookListOfKind(BookDTO[] bookList, String kind) {
		System.out.println("==" + kind + "인 책의 정보 ==");
		for(BookDTO book : bookList) {
			if(book.getTitle().equals(kind)) {
				System.out.println(book.getTitle());
			}
		}
	}
}
