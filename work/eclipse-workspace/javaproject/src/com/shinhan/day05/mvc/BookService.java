package com.shinhan.day05.mvc;

import java.util.Arrays;

//	1. 책이 입고 되면, 책 제목을 수정
//	2. 책이 입고 되면, 책의 가격이 10% 증가
public class BookService {
	private BookDTO[] bookList;

	public BookDTO[] updateTitle(BookDTO[] bookList, String company) {
		BookDTO[] result = bookList;
		
		for(BookDTO book : bookList) {
			book.setTitle(company + book.getTitle());
		}
		
		return  result;
	}
	
	public BookDTO[] updatePrice(BookDTO[] bookList, double rate) {
		for(BookDTO book : bookList) {
			book.setPrice((int)(book.getPrice() * rate));
		}
		return bookList;
	}
	
	public BookService() {;}
	public BookService(BookDTO[] bookList) {
		this.bookList = bookList;
	}
	
	@Override
	public String toString() {
		return "BookService [bookList=" + Arrays.toString(bookList) + "]";
	}
}
