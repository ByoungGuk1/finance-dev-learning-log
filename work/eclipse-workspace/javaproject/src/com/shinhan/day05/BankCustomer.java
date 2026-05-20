package com.shinhan.day05;

//	자바빈즈기술 : field 는 외부에서 직접 수정 불가 (권장) -> 즉, 필드변수는 private
//		기본 생성자가 있어야한다.
//		일반적으로 메서드는 외부에서 접근이 가능하도록 public으로 공개한다.

//	class : 설계도, 틀, template
/*
 *	public	-> import를 하면 다른 패키지에서도 사용이 가능하다. 즉, 모든 패키지에서 접근이 가능하다.
 *	protected	-> 같은 패키지에서 접근 가능, 다른 패키지의 경우 상속을 받으면 사용이 가능
 *	생략		-> 같은 패키지에서만 사용이 가능하다.
 *	private	-> 같은 클래스에서만 접근 가능
 *		class 의 modifier(접근지정자) : public / 생략
 *		field 의 접근 지정자 : public, protected, 생략, private -> 접근 가능 범위가 점점 좁아짐
 *			static, final, transient -> 활용 방법
 */
public class BankCustomer {
//	1. field : data 저장을 위한 목적으로 속성, 변수 라고도 표현
//		객체 생성 시 자동으로 초기화
//		static, non-static 두가지로 구분
//			non-static : instance 변수 -> 객체마다 독립적으로 생성, 객체 생성(new)시 자동 초기화
//			static	: class 변수 -> 클래스 로드시 자동 초기화, object들의 공유 변수가 된다(C언어의 share변수와 비슷)
	static int count;	//	클래스가 로드 되면 바로 초기화
	private String name;	//	new 생성자() -> 생성과 함께 초기화
	private int age;
	private int balance;
	
//	2. constructor : 사용자 정의가 없으면 기본 생성자가 컴파일시에 추가
//		정의를 한다면 컴파일시에 추가가 되지 않는다.
//		생성자는 정의 시 반드시 class 이름과 동일해야한다.
//		생성시 초기화의 목적으로 사용
//		생성자는 오버로딩이 가능하다
//			오버로딩 : 이름이 같고 매개변수가 다름
//		field 이름과 매개변수 이름이 같아서 충돌이 발생할 수도 있다
//			이를 방어하기 위해 this 사용
//		생성자의 Overloading 때문에 count++ 의 로직이 반복적이다.
//			=> 하나의 생성자에 로직을 넣고 다른 생성자가 해당 생성자를 호출
	public BankCustomer() {
		this(null, 0, 0);	//	-> 해당 클래스의 다른 생성자를 호출
		System.out.println("기본 생성자 -> argument가 없다.");
	}
	public BankCustomer(String name){
		this(name, 0, 0);
		System.out.println("생성자 -> argument가 1개");
	}
	public BankCustomer(String name, int age){
		this(name, age, 0);
		System.out.println("생성자 -> argument가 2개");
	}
	public BankCustomer(String name, int age, int balance) {
		super();
		System.out.println("생성자 -> argument가 3개");
		this.name = name;
		this.age = age;
		this.balance = balance;
		count++;
	}
	
//	3. 메서드 : 기능, 이름은 동사로 정의
//		return 값이 없다면 :: void
	void print() {
		int age = 99;
		System.out.println("name : " + name);
		System.out.println("필드(맴버 변수) age : " + this.age);
		System.out.println("지역변수 age : " + age);
		System.out.println("balance : " + balance);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name + "님";	//	사용은 사용부에서 내부 로직은 내부에서 하도록 분리
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age - 1;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
//	4. block : 문장들의 묶음
	{	//	생성자 사용하면 되어서 거의 사용하지 않음
		System.out.println("=== 객체 생성마다 생성자 실행 후 실행===");
	}
	static {
		System.out.println("=== class load 시 1회만 수행 ===");
	}
	
//	5. inner class
	
}
