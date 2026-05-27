package com.shinhan.day09;
/**
 * =====================================================
 * [팀 버그 헌팅] 신한은행 계좌 관리 시스템
 * 모듈: Module 02 - 6장 클래스 + 7장 상속
 * =====================================================
 * 이 코드에는 버그가 10개 숨어 있습니다.
 * 팀별로 버그를 찾아 번호, 위치, 이유를 적어주세요.
 *
 * 버그 유형:
 *   - 컴파일 오류       : 실행 전 Eclipse가 빨간 줄 표시
 *   - 런타임 오류       : 실행 중 Exception 발생
 *   - 논리 오류         : 실행은 되지만 결과가 틀림
 *
 * 팀 기록지:
 *   버그 #  | 위치(클래스/메서드) | 유형 | 원인 | 수정 방법
 *   ------  | ------------------ | ---- | ---- | --------
 *   #1      |  패키지          |      |      |
 *   ...
 * =====================================================
 */

// =====================================================
// FinancialProduct — 추상 부모 클래스
// =====================================================
abstract class FinancialProduct {

    protected String productName;
    protected int    amount;
    private   double interestRate;

    public FinancialProduct(String productName, int amount, double interestRate) {
        this.productName  = productName;
        this.amount       = amount;
        this.interestRate = interestRate;
    }

    // ★ BUG #1 [논리오류] ★
    // 이율을 반환해야 하는데 잘못된 값을 반환함
    public double getInterestRate() {
        return interestRate;  // ← 여기//찾음
    }

    public int getAmount()         { return amount;      }
    public String getProductName() { return productName; }

    public abstract int calculateReturn(int months);

//    상속 받은 자식이 상속 불가 -> final
    // 모든 상품에 공통 적용되는 계약서 출력
     public /*final*/ void printContract() {
        System.out.println("=== 계약서: " + productName + " ===");
        System.out.printf("금액: %,d원 | 이율: %.1f%%%n", amount, interestRate);
    }

    @Override
    public String toString() {
        return String.format("상품명=%s, 금액=%,d, 이율=%.1f%%",
                productName, amount, interestRate);
    }
}


// =====================================================
// SavingsAccount — 보통예금
// =====================================================
class SavingsAccount extends FinancialProduct {

    private static int totalAccounts = 0; // 총 계좌 수

    public SavingsAccount(String productName, int amount, double interestRate) {
        super(productName, amount, interestRate);
        totalAccounts++;
    }

   
    // 단리 이자 계산: 금액 × 이율/100 × 개월/12
    @Override
    public int calculateReturn(int months) {
        return (int)(amount * getInterestRate() / 100 * months / 12);
      
    }

  
    // 입금 메서드 
    public void deposit(int depositAmount) {
        amount += depositAmount;   //찾음
        System.out.printf("[입금] %s +%,d원 → 잔액: %,d원%n",
                productName, depositAmount, amount);
    }

   
 
    @Override        //찾음   
    public String toString() {
        return String.format("SavingsAccount{%s, 총계좌수=%d}", super.toString(), totalAccounts);
    }

    public static int getTotalAccounts() { return totalAccounts; }
}


// =====================================================
// LoanAccount — 대출 계좌
// =====================================================
class LoanAccount extends FinancialProduct {

    private int loanAmount;

    public LoanAccount(String productName, int loanAmount, double interestRate) {
        super(productName, loanAmount, interestRate);
        this.loanAmount = loanAmount;
    }

    // 월이자 = 대출금 × 이율/100 / 12
    @Override
    public int calculateReturn(int months) {
        return (int)(loanAmount * getInterestRate() / 100 / months); // <-매개변수 사용
    }

    // 대출 상환 메서드
    public void repay(int repayAmount) {
        // 상환금이 잔액보다 크면 거절
        if (repayAmount <= loanAmount) {  //찾음   
            loanAmount -= repayAmount;   
            System.out.printf("[상환] -%,d원 → 남은대출: %,d원%n",
                    repayAmount, loanAmount);
        } else {
            System.out.println("상환금액이 잔액을 초과합니다.");
        }
    }

    // 계약서 출력을 자식이 임의로 불가 
//    재정의 불가
//    @Override
//    public void printContract() {
//        System.out.println("=== 대출 계약서: " + productName + " ===");
//        System.out.printf("대출금: %,d원 | 이율: %.1f%%%n", loanAmount, getInterestRate());
//        System.out.println("※ 중요 약관 생략됨");  // 중요 약관이 빠진 불완전한 계약서
//    }

    public int getLoanAmount() { return loanAmount; }
}


// =====================================================
// BankBranch — 지점 관리 (다형성)
// =====================================================
class BankBranch {

    private String branchName;
    private FinancialProduct[] products = new FinancialProduct[10];
    private int productCount = 0;

    public BankBranch(String branchName) {
        this.branchName = branchName;
        System.out.println("[지점개설] " + branchName);
    }

    public void addProduct(FinancialProduct product) {
    	if(productCount >= 10) {//찾음
    		System.out.println("제품이 가득 참");
    	}
        products[productCount++] = product;
        System.out.println("[상품등록] " + product.getProductName());
    }

    public void processAll() {
        System.out.println("\n=== " + branchName + " 월말 처리 ===");
        for (int i = 0; i < productCount; i++) {
            FinancialProduct p = products[i];

            System.out.println("[공통처리] " + p.getProductName());//찾음
            if (p instanceof SavingsAccount) {
                SavingsAccount sa = (SavingsAccount) p;
                int interest = sa.calculateReturn(1);
                sa.deposit(interest);

            } else if (p instanceof LoanAccount) {
                LoanAccount la = (LoanAccount) p;
                System.out.printf("[이자청구] %,d원%n", la.calculateReturn(1));
            }
        }
    }

    public void processLoanOnly() {
        System.out.println("\n=== 대출 계좌 처리 ===");
        for (int i = 0; i < productCount; i++) {
        	if(products[i] instanceof LoanAccount la) {//찾음
	            System.out.printf("[대출처리] %s | 잔액: %,d원%n", la.getProductName(), la.getLoanAmount());
        	}
        }
    }

    public void printAll() {
        System.out.println("\n=== " + branchName + " 상품 목록 (" + productCount + "개) ===");
        for (int i = 0; i < productCount; i++) {
            System.out.println("  " + products[i]);
        }
    }
}


// =====================================================
// BugHunting — main
// =====================================================
public class BugHunting {

    public static void main(String[] args) {

        System.out.println("========== 신한은행 계좌 관리 시스템 ==========\n");

        // ── 상품 생성 ───────────────────────────────────
        SavingsAccount sa1 = new SavingsAccount("보통예금", 1_000_000, 3.0);
        SavingsAccount sa2 = new SavingsAccount("정기예금", 5_000_000, 5.0);
        LoanAccount    la1 = new LoanAccount("신용대출", 10_000_000, 6.5);

        // ── 이자 계산 ───────────────────────────────────
        System.out.println("=== 이자 계산 ===");
        System.out.printf("보통예금 월이자: %,d원%n", sa1.calculateReturn(1));
        System.out.printf("정기예금 12개월 이자: %,d원%n", sa2.calculateReturn(12));

        // ── 입금 테스트 ─────────────────────────────────
        System.out.println("\n=== 입금 테스트 ===");
        System.out.printf("입금 전 잔액: %,d원%n", sa1.getAmount());
        sa1.deposit(500_000);
        System.out.printf("입금 후 잔액: %,d원%n", sa1.getAmount());
        // 예상: 1,500,000원 / 실제: ???

        // ── 대출 상환 ───────────────────────────────────
        System.out.println("\n=== 대출 상환 ===");
        System.out.printf("상환 전 대출: %,d원%n", la1.getLoanAmount());
        la1.repay(3_000_000);
        System.out.printf("상환 후 대출: %,d원%n", la1.getLoanAmount());
        // 예상: 7,000,000원 / 실제: ???

        // ── 지점 등록 ───────────────────────────────────
        System.out.println("\n--지점등록");
        BankBranch branch = new BankBranch("강남지점");
        branch.addProduct(sa1);
        branch.addProduct(sa2);
        branch.addProduct(la1);
        branch.printAll();

        // ── 월말 처리 ───────────────────────────────────
        branch.processAll();
        // 예상: 예금은 이자 입금, 대출은 이자 청구
        // 실제: ???

        // 총 계좌수 출력 
        System.out.println("\n총 계좌 수: " + SavingsAccount.getTotalAccounts()); // 클래스 메서드로 사용(인스턴스형태로 x)

        branch.processLoanOnly();
    }
}
