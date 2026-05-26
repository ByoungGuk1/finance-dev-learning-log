package com.shinhan.day08.lab;
/**
 * =====================================================
 * [초급 LAB 2] 추상 클래스 — 금융 상품 설계
 * 모듈: Module 02 - 7장 상속
 * 난이도: ★☆☆
 * 목표: 1. abstract 클래스와 abstract 메서드 선언
 *       2. 자식 클래스에서 추상 메서드 구현 (강제)
 *       3. final 메서드로 오버라이딩 금지
 *       4. protected 필드 접근
 * 선행지식: extends, @Override, 접근제한자
 * 예상시간: 40분
 * =====================================================
 *
 * 클래스 구조:
 *   FinancialProduct (추상 부모)    ← abstract
 *   ├── SavingsAccount (예금)       ← 자식1
 *   └── LoanProduct    (대출)       ← 자식2
 */

// =====================================================
// FinancialProduct.java — 추상 부모 클래스
// =====================================================
abstract class FinancialProduct {
    // TODO [★☆☆] 필드를 선언하세요 (3분)
    // - productName  : String   (상품명, protected)  ← 자식이 직접 접근 가능
	protected String productName;
    // - interestRate : double   (연이율%, protected)
	protected double interestRate;
    // - amount       : int      (금액, private)
	private int amount;
    // ⚠️ protected: 같은 패키지 + 자식 클래스에서 접근 가능


    // TODO [★☆☆] 생성자를 작성하세요 (3분)
    // 매개변수: productName, interestRate, amount
    // "[상품생성] 상품명 (이율: X%)" 출력
	public FinancialProduct(String productName, double interestRate, int amount) {
		this.productName = productName;
		this.interestRate = interestRate;
		this.amount = amount;
		System.out.println("[상품생성] "+productName+" (이율: "+interestRate+"%)");
	}

	
    // TODO [★☆☆] Getter를 작성하세요 (3분)
    // getProductName(), getInterestRate(), getAmount()
    public String getProductName() {
		return productName;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public int getAmount() {
		return amount;
	}
	

    // TODO [★★☆] 추상 메서드 2개를 선언하세요 (5분)
    // → 자식마다 계산 방식이 달라서 부모가 구현할 수 없음
    //
    // calculateReturn(int months) : int
    //   — 예금: 이자 계산 / 대출: 상환금 계산
    //   반환: 금액 (int)
	public abstract int calculateReturn(int months);
    // printSummary()
    //   — 상품 요약 출력 (자식마다 다름)
	public abstract void printSummary();
    // 힌트: public abstract int calculateReturn(int months);


    // TODO [★★☆] final 메서드를 작성하세요 (5분)
    // → 모든 상품에 공통 적용, 자식이 변경하면 안 됨
    //
    // printContract() — final 메서드 (오버라이딩 금지)
    // 출력:
    //   "=============================="
    //   "[계약서] 상품명"
    //   "금액: N원 | 이율: N%"
    //   "=============================="
    // 힌트: public final void printContract() { ... }
	public final void printContract() {
		System.out.println("==============================");
		System.out.println("[계약서] "+productName);
		System.out.println("금액: "+amount+"원 | 이율: "+interestRate+"%");
		System.out.println("==============================");
	}
	
	@Override
    public String toString() {
        return String.format("FinancialProduct{상품=%s, 이율=%.1f%, 금액=%d}",
                productName, interestRate, amount);
    }
}


// =====================================================
// SavingsAccount.java — 예금 상품
// =====================================================
enum AccountType{
	정기예금, 자유적금
}

class SavingsAccount extends FinancialProduct {

    // TODO [★☆☆] 추가 필드를 선언하세요 (2분)
    // - accountType : String  (예금 종류: "정기예금" / "자유적금", private)
	private AccountType accountType;


    // TODO [★☆☆] 생성자를 작성하세요 (4분)
    // 매개변수: productName, interestRate, amount, accountType
    // 조건: super(productName, interestRate, amount) 첫 줄 호출
    //       "[예금개설] 상품명 (종류: accountType)" 출력
	public SavingsAccount(String productName, double interestRate, int amount, AccountType accountType) {
		super(productName, interestRate, amount);
		this.accountType = accountType;
		String message = String.format( "[예금개설] %s (종류: %s)", productName, accountType);
		System.out.println(message);
	}


    // TODO [★★☆] calculateReturn(int months) 추상 메서드를 구현하세요 (8분)
    // 단리 이자 계산:
    //   이자 = 금액 × 연이율/100 × 개월수/12
    //   반환: (int) 이자 금액만 반환
    // 출력: "[이자계산] 정기예금 12개월 → 이자: 150,000원 (원금+이자: 3,150,000원)"
    // ⚠️ protected 필드(productName, interestRate)는 직접 접근 가능
	@Override
	public int calculateReturn(int months) {
		int result = 0;
		result = (int)(getAmount() * interestRate/100 * months/12);
		String message = String.format("[이자계산] %s %d개월 → 이자: %d원 (원금+이자: %d원)", accountType, months, result, getAmount() + result);
		System.out.println(message);
		return result;
	}


    // TODO [★★☆] printSummary() 추상 메서드를 구현하세요 (5분)
    // 출력형식:
    //   "--- 예금 상품 요약 ---"
    //   "상품명: 정기예금 (정기예금)"
    //   "원금: 3,000,000원 | 연이율: 5.0%"
	@Override
	public void printSummary() {
		System.out.println("--- 예금 상품 요약 ---");
		System.out.println("상품명: "+productName+" ("+accountType+")");
		System.out.println("원금: "+getAmount()+"원 | 연이율: "+interestRate+"%");
	}


    // TODO [★☆☆] final 메서드 오버라이딩 시도 (2분 — 오류 확인용)
    // 아래 주석을 해제하면 컴파일 오류 발생
//     @Override
//     public void printContract() { }  // ❌ final은 오버라이딩 불가!
}


// =====================================================
// LoanProduct.java — 대출 상품
// =====================================================
enum LoanType{
	신용대출, 담보대출
}

class LoanProduct extends FinancialProduct {

    // TODO [★☆☆] 추가 필드 선언 (2분)
    // - loanType : String  (대출 종류: "신용대출" / "담보대출", private)
	private LoanType loanType;


    // TODO [★☆☆] 생성자 작성 (4분)
    // 매개변수: productName, interestRate, amount, loanType
    // super() 호출, "[대출개설] 상품명 (종류: loanType)" 출력
	public LoanProduct(String productName, double interestRate, int amount, LoanType loanType) {
		super(productName, interestRate, amount);
		this.loanType = loanType;
		System.out.println("[대출개설] "+productName+" (종류: "+loanType+")");
	}


    // TODO [★★☆] calculateReturn(int months) 구현 (8분)
    // 단순 월납입액 계산 (원리금 균등상환):
    //   월이율 = interestRate / 100 / 12
    //   월납입액 = amount * 월이율 / (1 - (1+월이율)^(-months))
    // 출력: "[상환계산] 신용대출 36개월 → 월납입액: 302,000원"
    // 반환: 월납입액 (int)
    // 힌트: (int) Math.round(...)
	@Override
	public int calculateReturn(int months) {
		int monthAmount = 0;
		double monthRate = 0.0;
		monthRate = interestRate / 100 / 12;
		monthAmount = (int)Math.round(getAmount() * monthRate / (1 - Math.pow((1+monthRate),(-months))));
//		제곱 표현 방법 다시 보기
		System.out.println("[상환계산] "+loanType+" 36개월 → 월납입액: "+monthAmount+"원");
		return monthAmount;
	}


    // TODO [★★☆] printSummary() 구현 (5분)
    // 출력형식:
    //   "--- 대출 상품 요약 ---"
    //   "상품명: 신용대출 (신용대출)"
    //   "대출금: 10,000,000원 | 연이율: 6.5%"
	@Override
	public void printSummary() {
		System.out.println("--- 대출 상품 요약 ---");
		System.out.println("상품명: "+productName+" ("+loanType+")");
		System.out.println("대출금: "+getAmount()+"원 | 연이율: "+interestRate+"%");
	}
}


// =====================================================
// Lab2Main — 실행 및 결과 확인
// =====================================================
public class Lab2_AbstractClass {

    public static void main(String[] args) {

        System.out.println("========== [1] 추상 클래스 직접 생성 시도 ==========");
        // TODO: 아래 주석 해제 → 컴파일 오류 직접 확인
        // FinancialProduct fp = new FinancialProduct(...); // ❌ 추상 클래스는 new 불가!


        System.out.println("========== [2] 자식 클래스 객체 생성 ==========");
        // TODO [★☆☆] 객체 2개를 생성하세요 (3분)
        // s1: "정기예금", 5.0, 3_000_000, "정기예금"
        // l1: "신용대출", 6.5, 10_000_000, "신용대출"
        SavingsAccount s1 = new SavingsAccount("정기예금", 5.0, 3_000_000, AccountType.정기예금);
        LoanProduct l1 = new LoanProduct("신용대출", 6.5, 10_000_000, LoanType.신용대출);


        System.out.println("\n========== [3] 추상 메서드 구현 확인 ==========");
        // TODO: s1.calculateReturn(12) 호출 — 12개월 이자 계산
        // TODO: l1.calculateReturn(36) 호출 — 36개월 월납입액 계산
        s1.calculateReturn(12);
        l1.calculateReturn(36);


        System.out.println("\n========== [4] printSummary() ==========");
        // TODO: s1.printSummary(), l1.printSummary() 호출
        s1.printSummary();
        l1.printSummary();
        

        System.out.println("\n========== [5] final 메서드 — printContract() ==========");
        // TODO: s1.printContract(), l1.printContract() 호출
        // → 부모의 final 메서드가 그대로 실행됨을 확인
        s1.printContract();
        l1.printContract();
        

        System.out.println("\n========== [6] 부모 타입으로 다루기 ==========");
        // TODO [★★☆] 추상 클래스 타입 배열로 다형성 체험 (5분)
        // FinancialProduct[] products = { s1, l1 };
        // for (FinancialProduct p : products) {
        //     p.printSummary();        // 각자 다른 printSummary() 실행
        //     p.printContract();       // 공통 final 메서드 실행
        // }
        FinancialProduct[] products = { s1, l1 };
        for (FinancialProduct p : products) {
        	p.printSummary();
        	p.printContract();
        }
    }
}
