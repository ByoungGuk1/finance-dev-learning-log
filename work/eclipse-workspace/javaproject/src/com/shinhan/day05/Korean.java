package com.shinhan.day05;

public class Korean {
	private String nation = "대한민국";
	private String name;
	private String ssn;
	
//	생성자 Overload : 매개변수가 다르다
	public Korean() {;}
	public Korean(String name) {
		this.name = name;
	}
	public Korean(String name, String ssn) {
		this.name = name;
		this.ssn = ssn;
	}
	
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	@Override
	public String toString() {
		return "Korean [nation=" + nation + ", name=" + name + ", ssn=" + ssn + "]";
	}
}
