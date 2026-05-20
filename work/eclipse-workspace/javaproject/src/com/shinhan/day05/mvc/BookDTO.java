package com.shinhan.day05.mvc;

//	DTO (Data Transfer Object)
public class BookDTO {
	private String title;
	private int price;
	
	public BookDTO() {;}
	public BookDTO(String title, int price) {
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
		return "BookDTO [title=" + title + ", price=" + price + "]";
	}
}
