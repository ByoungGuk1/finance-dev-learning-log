package com.shinhan.day08.lab;
/**
 * =====================================================
 * [중급 LAB 3] 다형성 & 타입 변환 — 금융 거래 처리
 * 모듈: Module 02 - 7장 상속
 * 난이도: ★★☆
 * 목표: 1. 필드 다형성 / 매개변수 다형성 구현
 *       2. 자동 타입 변환 & 강제 타입 변환
 *       3. instanceof 연산자로 타입 확인
 *       4. 부모 타입 배열로 자식 객체 일괄 처리
 * 선행지식: extends, @Override, abstract
 * 예상시간: 55분
 * =====================================================
 *
 * 클래스 구조:
 *   Account (추상 부모)
 *   ├── SavingsAccount  (보통예금)
 *   ├── FixedDeposit    (정기예금)
 *   └── LoanAccount     (대출계좌)
 *
 *   BankBranch (지점) — Account[] 필드로 필드 다형성
 *   AccountProcessor  — Account 매개변수로 매개변수 다형성
 */

// =====================================================
// Account — 추상 부모
// =====================================================
abstract class Account {

    // TODO [★☆☆] 필드를 선언하세요 (3분)
    // - accountNumber : final String  (계좌번호, protected)
    // - ownerName     : String        (예금주, protected)
    // - balance       : int           (잔액, protected)
	protected final String accountNumber;
	protected String ownerName;
	protected int balance;

    // TODO [★☆☆] 생성자를 작성하세요 (3분)
    // 매개변수: accountNumber, ownerName, balance
    public Account(String accountNumber, String ownerName, int balance) {
		this.accountNumber = accountNumber;
		this.ownerName = ownerName;
		this.balance = balance;
	}


    // TODO [★★☆] 추상 메서드 2개를 선언하세요 (3분)
    // - calculateInterest() : int  — 이자/수수료 계산 (자식마다 다름)
    // - getAccountType()    : String — 계좌 종류명 반환
    public abstract int calculateInterest();
    public abstract String getAccountType();


    // TODO [★☆☆] 공통 메서드를 작성하세요 (5분)
    // deposit(int amount)  — 잔액 += amount, "[입금] 예금주 +N원" 출력
    public void deposit(int amount) {
		balance += amount;
		System.out.println("[입금] "+ownerName+" +"+amount+"원");
	}
    // withdraw(int amount) — 잔액 부족 시 false 반환, 성공 시 true
    public boolean withdraw(int amount) {
		if(balance < amount) {
			return false;
		}
		balance -= amount;
		return true;
	}
    // printInfo()          — "[계좌] 계좌번호 | 예금주 | 종류 | 잔액" 출력
    //                         힌트: getAccountType() 호출
    public void printInfo() {
		System.out.println("[계좌] "+accountNumber+" | "+ownerName+" | "+getAccountType()+" | "+balance);
	}

    public String getAccountNumber() { return accountNumber; }
	public String getOwnerName()     { return ownerName;     }
    public int    getBalance()       { return balance;       }

    @Override
    public String toString() {
        return String.format("Account{%s, %s, %s, %,d}",
                accountNumber, ownerName, getAccountType(), balance);
    }
}


// =====================================================
// SavingsAccount — 보통예금 (입출금 자유)
// =====================================================
class SavingAccount extends Account {

    private static final double INTEREST_RATE = 1.5; // 연 1.5%

    
    // TODO [★☆☆] 생성자 (3분)
    // super(accountNumber, ownerName, balance) 호출
	public SavingAccount(String accountNumber, String ownerName, int balance) {
		super(accountNumber, ownerName, balance);
	}


    // TODO [★★☆] calculateInterest() 구현 (5분)
    // 월이자 = 잔액 × 1.5% / 12
    // 출력: "[이자지급] 홍길동(보통예금) → 이자: 1,250원"
    // 반환: 이자 (int)
	@Override
	public int calculateInterest() {
		int monthRate = (int)(balance * INTEREST_RATE / 100 / 12);
		System.out.println("[이자지급] "+ownerName+"("+getAccountType()+") → 이자: "+monthRate+"원");
		return monthRate;
	}


    // TODO [★☆☆] getAccountType() 구현 (1분)
    // 반환: "보통예금"
	@Override
	public String getAccountType() {
		return "보통예금";
	}


	public static double getInterestRate() {
		return INTEREST_RATE;
	}
}


// =====================================================
// FixedDeposit — 정기예금
// =====================================================
class FixedDeposit extends Account {

    private double interestRate; // 연이율 (%)
    private int termMonths;   // 약정 개월수


    // TODO [★☆☆] 생성자 (3분)
    // 매개변수: accountNumber, ownerName, balance, interestRate, termMonths
    // super() 호출
	public FixedDeposit(String accountNumber, String ownerName, int balance, double interestRate, int termMonths) {
		super(accountNumber, ownerName, balance);
		this.interestRate = interestRate;
		this.termMonths = termMonths;
	}
	

    // TODO [★★☆] calculateInterest() 구현 (7분)
    // 만기 이자 = 잔액 × 연이율/100 × 약정개월/12
    // 출력: "[만기이자] 김철수(정기예금 12개월) → 이자: 150,000원, 만기수령액: 3,150,000원"
    // 반환: 이자 (int)
	@Override
	public int calculateInterest() {
		int result = (int)(balance * interestRate/100 * termMonths/12);
		System.out.println("[만기이자] "+ownerName+"("+getAccountType()+") → 이자: "+result+"원, 만기수령액: "+(balance+result)+"원");
		return result;
	}


    // TODO [★☆☆] getAccountType() 구현 (1분)
    // 반환: "정기예금(" + termMonths + "개월)"
	@Override
	public String getAccountType() {
		return "정기예금(" + termMonths + "개월)";
	}


	public int getTermMonths() {
		return termMonths;
	}
	
}


// =====================================================
// LoanAccount — 대출 계좌
// =====================================================
class LoanAccount extends Account {

    private double interestRate; // 대출 이율 (%)
    private int    loanAmount;   // 대출 원금


    // TODO [★☆☆] 생성자 (3분)
    // balance 자리에 loanAmount 전달 (대출금 = 잔액 개념)
    // super(accountNumber, ownerName, loanAmount) 호출
	public LoanAccount(String accountNumber, String ownerName, int loanAmount, double interestRate) {
		super(accountNumber, ownerName, loanAmount);
		this.interestRate = interestRate;
		this.loanAmount = loanAmount;
	}


    // TODO [★★☆] calculateInterest() 구현 (5분)
    // 월이자 = 대출잔액 × 연이율/100 / 12
    // 출력: "[이자청구] 이영희(대출계좌) → 이번달 이자: 54,166원"
    // 반환: 월이자 (int)
	@Override
	public int calculateInterest() {
		int month = (int)(loanAmount * interestRate/100 / 12);
		System.out.println("[이자청구] "+ownerName +"("+getAccountType()+") → 이번달 이자: "+month+"원");
		return month;
	}


    // TODO [★☆☆] getAccountType() 구현 (1분)
    // 반환: "대출계좌"
	@Override
	public String getAccountType() {
		return "대출계좌";
	}


    // TODO [★★☆] repay(int amount) 대출 상환 메서드 추가 (7분)
    // 조건: amount > balance 이면 "상환금액 초과" 출력 후 return
    // 정상: balance -= amount
    //       "[대출상환] 이영희 -N원 (남은대출: N원)" 출력
	public boolean repay(int amount) {
		if(amount > balance) {
			System.out.println("상환금액 초과");
			return false;
		}
		balance -= amount;
		return true;
	}


	public double getInterestRate() {
		return interestRate;
	}
}


// =====================================================
// BankBranch — 지점 (필드 다형성)
// =====================================================
class BankBranch {

    private String branchName;

    // TODO [★★☆] 계좌 목록 필드 선언 (3분)
    // Account[] 배열로 선언 — 부모 타입 배열에 자식 객체를 담는 것이 핵심!
    // 최대 10개 계좌를 담을 수 있도록 크기를 10으로 설정
    // private Account[] accounts = new Account[10];
    // private int       accountCount = 0;   // 실제 등록된 계좌 수
    private Account[] accounts = new Account[10];
    private int accountCount = 0;

    
    // TODO [★☆☆] 생성자 (2분)
    // branchName 초기화, "[지점개설] 지점명" 출력
	public BankBranch(String branchName) {
		this.branchName = branchName;
		System.out.println("[지점개설] "+branchName);
	}

    // TODO [★★☆] addAccount(Account account) 메서드 (5분)
    // 조건: accountCount >= accounts.length 이면 "계좌 등록 한도 초과" 출력 후 return
    // 정상: accounts[accountCount++] = account
    //       "[계좌등록] 계좌번호 (예금주)" 출력
    // → Account 타입으로 받으므로 어떤 자식이든 등록 가능 (필드 다형성!)
	public boolean addAccount(Account account) {
		if(accountCount >= accounts.length) {
			System.out.println("계좌 등록 한도 초과");
			return false;
		}
		accounts[accountCount++] = account;	//	필드의 다형성
		return true;
	}


    // TODO [★★★] processMonthEnd() 월말 이자 처리 (10분)
    // for (int i = 0; i < accountCount; i++) 로 순회
    // Account acc = accounts[i];
    //
    // instanceof로 타입 확인 후 처리:
    //   if (acc instanceof SavingsAccount) {
    //       SavingsAccount sa = (SavingsAccount) acc;  // 강제 타입 변환
    //       int interest = sa.calculateInterest();
    //       sa.deposit(interest);                      // 이자 자동 입금
    //   } else if (acc instanceof FixedDeposit) {
    //       FixedDeposit fd = (FixedDeposit) acc;
    //       fd.calculateInterest();
    //   } else if (acc instanceof LoanAccount) {
    //       LoanAccount la = (LoanAccount) acc;
    //       la.calculateInterest();                    // 이자 청구만 출력
    //   }
	public void processMonthEnd() {
		for(Account acc : accounts) {
			if(acc == null)	break;
			if(acc instanceof SavingAccount sa) {
				int interest = sa.calculateInterest();
				sa.deposit(interest);
			} else if(acc instanceof FixedDeposit fd) {
				fd.calculateInterest();
			} else if(acc instanceof LoanAccount la) {
				la.calculateInterest();
			}
		}
	}
	
	
    // TODO [★★☆] printAllAccounts() (3분)
    // "=== 지점명 전체 계좌 (N개) ===" 출력 (N = accountCount)
    // for (int i = 0; i < accountCount; i++) 로 순회
    // 각 계좌의 printInfo() 호출
	public void printAllAccounts() {
		System.out.println("=== "+branchName+" 전체 계좌 ("+accountCount+"개) ===");
		for(int i = 0; i < accountCount; i++) {
			if(accounts[i] == null)	break;
			accounts[i].printInfo();
		}
	}
}


// =====================================================
// AccountProcessor — 매개변수 다형성
// =====================================================
class AccountProcessor {

    // TODO [★★★] processAccount(Account account) (10분)
    // 매개변수를 Account 타입으로 받아 자식 타입에 따라 처리
    //
    // instanceof로 타입 확인 후:
    // - SavingsAccount  → "보통예금 처리: 이자 계산 후 자동 입금"
    // - FixedDeposit fd → "정기예금 처리: 만기이자 " + fd.calculateInterest() + "원"
    // - LoanAccount  la → "대출계좌 처리: 이자 " + la.calculateInterest() + "원 청구"
    //
    // 출력: "[처리완료] 예금주 — 처리내용"
	
	public void processAccount(Account account) {
		int money = account.calculateInterest();
		if(account instanceof SavingAccount) {
			System.out.println("보통예금 처리: 이자 계산 후 자동 입금");
			account.deposit(money);
		} else if(account instanceof FixedDeposit) {
			System.out.println("정기예금 처리: 만기이자 " + money + "원");
		} else if(account instanceof LoanAccount) {
			System.out.println("대출계좌 처리: 이자 " + money + "원 청구");
		}
		System.out.println("[처리완료] "+account.ownerName+" — "+account.getAccountType());
	}


    // TODO [★★☆] printAccountReport(Account account) (7분)
    // 계좌 타입별 상세 리포트 출력
    // 강제 타입 변환 사용:
    //   if (account instanceof FixedDeposit) {
    //       FixedDeposit fd = (FixedDeposit) account;  // 강제 타입 변환
    //       System.out.println("약정기간: " + ...);
    //   }
	public void printAccountReport(Account account) {
		if(account instanceof SavingAccount) {
			System.out.println("연 이율: "+SavingAccount.getInterestRate());
		} else if(account instanceof FixedDeposit fd) {
			System.out.println("약정기간: "+fd.getTermMonths());
		} else if(account instanceof LoanAccount la) {
			System.out.println("대출 이율: "+la.getInterestRate());
		}
	}
}


// =====================================================
// Lab3Main — 실행
// =====================================================
public class Lab3_Polymorphism {

    public static void main(String[] args) {

        System.out.println("========== [1] 계좌 생성 ==========");
        // TODO [★☆☆] 계좌 3종류를 각각 생성하세요 (3분)
        // acc1: SavingsAccount "110-001", "홍길동", 1_000_000
        // acc2: FixedDeposit   "110-002", "김철수", 3_000_000, 5.0, 12
        // acc3: LoanAccount    "110-003", "이영희", 10_000_000, 6.5
        SavingAccount acc1 = new SavingAccount("110-001", "홍길동", 1_000_000);
        FixedDeposit acc2 = new FixedDeposit("110-002", "김철수", 3_000_000, 5.0, 12);
        LoanAccount acc3 = new LoanAccount("110-003", "이영희", 10_000_000, 6.5);


        System.out.println("\n========== [2] 자동 타입 변환 ==========");
        // TODO [★★☆] 부모 타입으로 대입 후 동작 확인 (3분)
        // Account a = acc1;   // 자동 변환
        // a.printInfo();      // 오버라이딩된 getAccountType() 호출됨
        // a.calculateInterest(); // 오버라이딩된 메서드 호출됨
        Account a = acc1;
        a.printInfo();
        a.calculateInterest();
        

        System.out.println("\n========== [3] instanceof + 강제 타입 변환 ==========");
        // TODO [★★☆] 아래 코드를 완성하세요 (7분)
        // Account[] accounts = { acc1, acc2, acc3 };
        // for (Account acc : accounts) {
        //     System.out.println(acc.getOwnerName() + " → " + acc.getAccountType());
        //
        //     if (acc instanceof LoanAccount) {
        //         LoanAccount la = (LoanAccount) acc;   // 강제 타입 변환
        //         la.repay(500_000);                    // 자식 전용 메서드 호출
        //     }
        // }
        Account[] accounts = { acc1, acc2, acc3 };
        for (Account acc : accounts) {
        	System.out.println(acc.getOwnerName() + " → " + acc.getAccountType());
        	if (acc instanceof LoanAccount) {
        		LoanAccount la = (LoanAccount) acc;
        		la.repay(500_000);
        	}
        }
        
        
        System.out.println("\n========== [4] 필드 다형성 — BankBranch ==========");
        // TODO [★★☆] BankBranch 생성 후 계좌 3개 등록 (3분)
        // BankBranch branch = new BankBranch("강남지점");
        // branch.addAccount(acc1);
        // branch.addAccount(acc2);
        // branch.addAccount(acc3);
        // branch.printAllAccounts();
        BankBranch branch = new BankBranch("강남지점");
        for(Account acc : accounts) {
        	branch.addAccount(acc);
        }
        branch.printAllAccounts();
        
        
        System.out.println("\n========== [5] 월말 이자 처리 ==========");
        // TODO: branch.processMonthEnd() 호출
        // → for 루프로 accounts[] 순회하며 타입별 다른 처리가 일어남을 확인
        branch.processMonthEnd();

        System.out.println("\n========== [6] 매개변수 다형성 ==========");
        // TODO [★★★] AccountProcessor로 각 계좌 처리 (3분)
        // AccountProcessor processor = new AccountProcessor();
        // Account[] accounts = { acc1, acc2, acc3 };
        // for (Account acc : accounts) {
        //     processor.processAccount(acc);
        // }
        AccountProcessor processor = new AccountProcessor();
        for (Account acc : accounts) {
             processor.processAccount(acc);
         }
    }
}