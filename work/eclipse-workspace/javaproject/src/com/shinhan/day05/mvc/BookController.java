package com.shinhan.day05.mvc;

public class BookController {
	public static void main(String[] args) {
	    BookDTO[] b = new BookDTO[5];
		b[0] = new BookDTO("Java Program",30000);
		b[1] = new BookDTO("JSP Program",25000);
		b[2] = new BookDTO("SQL Fundamentals",20000);
		b[3] = new BookDTO("JDBC Program",32000);
		b[4] = new BookDTO("EJB Program",25000);
		
		BookService service = new BookService(b);
		
		BookDTO[] bookList = service.updateTitle(b, "shinhan ");
		bookList = service.updatePrice(bookList, 1.1);
		
		BookView.printBookListInfo(bookList);
		System.out.println();
		BookView.printBookOfIndex(bookList, 2);
		System.out.println();
		BookView.printBookListOfKind(bookList, "shinhan Java Program");
		System.out.println();
		BookView.printAllBookTitle(bookList);
	}
}
