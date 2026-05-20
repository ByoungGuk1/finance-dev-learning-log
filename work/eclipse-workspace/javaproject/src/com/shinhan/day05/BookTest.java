package com.shinhan.day05;

import java.util.Arrays;

//	Controller : 사용자가 요청을 보내면 요청한 것에 대한 로직은 서비스 호출로 수행
//	결과를 사용자에게 응답
public class BookTest {
	public static void main(String[] args) {
	    Book[] b = new Book[5];
		b[0] = new Book("Java Program",30000);
		b[1] = new Book("JSP Program",25000);
		b[2] = new Book("SQL Fundamentals",20000);
		b[3] = new Book("JDBC Program",32000);
		b[4] = new Book("EJB Program",25000);
		
		BookMgr bm = new BookMgr(b);
//		bm.printBookList();
//		bm.printTotalPrice();
		b = bm.updateBookList(b);
		
		int totalPrice = bm.getTotalPrice();
		
		BookView.display(b);
		BookView.display(totalPrice);
	}
}

//	Java Beans (객체)
//	업무 로직은 없고 field, constructor, getter/setter
//	VO (Value Object)	=:= C언어의 구조체와 유사
//	DTO (Data Transfer Object)
class Book {
	private String title;
	private int price;

	public Book() {;}
	public Book(String title, int price) {
		this.title = title;
		this.price = price;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Book [title=" + title + ", price=" + price + "]";
	}
}

// Service : Business Logic 수행
class BookMgr {
	private Book[] booklist;
	
//	public void printBookList() {
//		System.out.println("=== 책 목록 ===");
//		for(Book book : booklist) {			
//			System.out.println(book.getTitle());
//		}
//		System.out.println();
//	}
//	public void printTotalPrice() {
//		int totalPrice = 0 ;
//		for(Book book : booklist) {
//			totalPrice += book.getPrice();
//		}
//		System.out.println("=== 책 가격의 총합 ===");
//		System.out.println("전체 책 가격의 합 : "+ totalPrice);
//	}
	public Book[] updateBookList(Book[] bookList) {
		for (int i = 0; i < bookList.length; i++) {
			bookList[i].setTitle("신한-" + bookList[i].getTitle());
			bookList[i].setPrice((int)(bookList[i].getPrice() * 1.1));
		}
		return bookList;
	}
	public int getTotalPrice() {
		int totalPrice = 0 ;
		for(Book book : booklist) {
			totalPrice += book.getPrice();
		}
		return totalPrice;
	}

	public BookMgr() {;}
	public BookMgr(Book[] booklist) {
		this.booklist = booklist;
	}
	
	@Override
	public String toString() {
		return "BookMgr [booklist=" + Arrays.toString(booklist) + "]";
	}
}

class BookView{
	public static void display(int totalPrice) {
		System.out.println("=== 책 가격의 총합 ===");
		System.out.println("전체 책 가격의 합 : "+ totalPrice);
	}
	
	public static void display(Book[] bookList) {
		System.out.println("=== 책 목록 ===");
		for(Book book : bookList) {			
			System.out.println(book.getTitle());
		}
		System.out.println();
	}
}