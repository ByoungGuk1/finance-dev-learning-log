package com.shinhan.day07;

/**
 * =====================================================
 * [중급 LAB 1] 은행 계좌 관리 시스템
 * 모듈: Module 02 - 6장 클래스
 * 난이도: ★★☆
 * 목표: 1. 생성자 오버로딩으로 다양한 계좌 개설
 *       2. Getter/Setter로 캡슐화 적용
 *       3. 인스턴스 멤버 vs static 멤버 구분
 *       4. 메서드 오버로딩
 * 선행지식: 필드, 생성자, 메서드, 접근제한자
 * 예상시간: 50분
 * =====================================================
 */
// 열거형 데이터 타입 생성(값이 한정적으로 제한), 상수들의 묶음
enum AccountType{
	SAVINGS, CHECKING
}
// =====================================================
// BankAccount.java — 완성하세요
// =====================================================
class BankAccount {

    // ── 필드 선언 ──────────────────────────────────────

    // TODO [★★☆] 인스턴스 필드 4개를 private으로 선언하세요 (5분)
    // - accountNumber : String  (계좌번호)
    // - ownerName     : String  (예금주)
    // - balance       : int     (잔액)
    // - accountType   : String  (계좌종류: "SAVINGS" or "CHECKING")
	private String accountNumber;
	private String ownerName;
	private int balance;
	private AccountType accountType;

    // TODO [★☆☆] static 필드 1개를 선언하세요 (3분)
    // - totalAccounts : int  (총 개설 계좌 수, private static)
	private static int totalAccounts;

    // ── 상수 선언 ──────────────────────────────────────
    // TODO [★☆☆] 1회 최대 이체 한도 상수를 선언하세요 (2분)
    // - MAX_TRANSFER : int = 10_000_000  (public static final)
	public static final int MAX_TRANSFER = 10_000_000;

    // ── 생성자 오버로딩 ────────────────────────────────

    // TODO [★★☆] 생성자 3개를 오버로딩하세요 (15분)
    //
    // [생성자1] 매개변수: accountNumber, ownerName, balance, accountType
    //   - 4개 필드 모두 초기화
    //   - totalAccounts++ 처리
	public BankAccount(String accountNumber, String ownerName, int balance, AccountType accountType) {
		this.accountNumber = accountNumber;
		this.ownerName = ownerName;
		this.balance = balance;
		this.accountType = accountType;
		totalAccounts++;
	}

    // [생성자2] 매개변수: accountNumber, ownerName, balance
    //   - this(...)로 생성자1 호출
    //   - accountType 기본값: "SAVINGS"
	public BankAccount(String accountNumber, String ownerName, int balance) {
		this(accountNumber, ownerName, balance, AccountType.SAVINGS);
	}

    // [생성자3] 매개변수: accountNumber, ownerName
    //   - this(...)로 생성자2 호출
    //   - balance 기본값: 0
	public BankAccount(String accountNumber, String ownerName) {
		this(accountNumber, ownerName, 0);
	}

    // ── Getter / Setter ────────────────────────────────

    // TODO [★☆☆] Getter 4개를 작성하세요 (5분)
    // getAccountNumber(), getOwnerName(), getBalance(), getAccountType()
	public String getAccountNumber() {
		return accountNumber;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public int getBalance() {
		return balance;
	}

	public AccountType getAccountType() {
		return accountType;
	}

    // TODO [★★☆] Setter — balance는 직접 setter 대신 deposit/withdraw 사용
    //             ownerName setter만 작성하세요 (5분)
    // setOwnerName(String ownerName)
    // 조건: null 이거나 빈 문자열이면 "이름 오류" 출력 후 변경하지 않음
	public void setOwnerName(String ownerName) {
		if(ownerName == null || ownerName.equals("")) {
			System.err.println("이름 입력 오류");
			return;
		}
		this.ownerName = ownerName;
	}
	
    // ── 인스턴스 메서드 ────────────────────────────────

    // TODO [★★☆] deposit() 메서드 오버로딩 2개를 작성하세요 (10분)
    //
    // [deposit1] deposit(int amount)
    //   - amount <= 0 이면 "입금액 오류" 출력 후 return
    //   - 정상이면 balance += amount, 입금 내역 출력
    //   출력형식: "[입금] 홍길동 +30,000원 → 잔액: 130,000원"
	public boolean deposit(int amount) {
		if(amount <= 0) {
			System.err.println("입금액 오류");
			return false;
		}
		balance += amount;
		System.out.println("[입금] " + ownerName + " +" + amount + "원 → 잔액: " + balance + "원");
		return true;
	}
    // [deposit2] deposit(int amount, String memo)
    //   - deposit(amount) 호출 후
    //   - "[메모] " + memo 출력
	public void deposit(int amount, String memo) {
		if(deposit(amount)) {
			System.out.println("[메모] " + memo);
		}
	}

    // TODO [★★☆] withdraw(int amount) 메서드를 작성하세요 (10분)
    //   - amount <= 0 이면 "출금액 오류" 출력 후 return
    //   - amount > balance 이면 "잔액 부족" 출력 후 return
    //   - amount > MAX_TRANSFER 이면 "1회 이체 한도 초과" 출력 후 return
    //   - 정상이면 balance -= amount, 출금 내역 출력
    //   출력형식: "[출금] 홍길동 -20,000원 → 잔액: 110,000원"
	public boolean withdraw(int amount) {
		if(amount <= 0) {
			System.err.println("출금액 오류");
			return false;
		}
		if(amount > balance) {
			System.err.println("잔액 부족");
			return false;
		}
		if(amount > MAX_TRANSFER) {
			System.err.println("1회 이체 한도 초과");
			return false;
		}
		balance -= amount;
		System.out.println("[출금] " + ownerName + " -" + amount + "원 → 잔액: " + balance + "원");
		return true;
	}

    // ── static 메서드 ──────────────────────────────────

    // TODO [★☆☆] getTotalAccounts() static 메서드를 작성하세요 (3분)
    // 반환: 총 개설 계좌 수 (int)
	public static int getTotalAccounts() {
		return totalAccounts;
	}

    // ── toString ───────────────────────────────────────

    // TODO [★☆☆] toString()을 오버라이드하세요 (3분)
    // 출력형식: "BankAccount{계좌=110-123, 예금주=홍길동, 잔액=130,000, 종류=SAVINGS}"
	@Override
	public String toString() {
		return "BankAccount{계좌=" + accountNumber + ", 예금주=" + ownerName + ", 잔액=" + balance + ", 종류=" + accountType + "}";
	}
}


// =====================================================
// Lab1Main.java — 실행 및 결과 확인
// =====================================================
public class Lab1_BankAccount {

    public static void main(String[] args) {

        System.out.println("========== [1] 생성자 오버로딩 테스트 ==========");
        // TODO [★☆☆] 생성자 3가지 방식으로 각각 계좌를 생성하세요 (5분)
        // acc1: 4개 매개변수 생성자 ("110-001", "홍길동", 100_000, "SAVINGS")
        // acc2: 3개 매개변수 생성자 ("110-002", "김철수", 50_000)
        // acc3: 2개 매개변수 생성자 ("110-003", "이영희")
        BankAccount acc1 = new BankAccount("110-001", "홍길동", 100_000_000, AccountType.SAVINGS);
        BankAccount acc2 = new BankAccount("110-002", "김철수", 50_000);
        BankAccount acc3 = new BankAccount("110-003", "이영희");

        // 생성 후 System.out.println()으로 각 계좌 출력 (toString 확인)
        System.out.println(acc1);
        System.out.println(acc2);
        System.out.println(acc3);

        System.out.println("\n========== [2] static 멤버 확인 ==========");
        // TODO: 총 개설 계좌 수 출력 (3이 나와야 함)
        // System.out.println("총 계좌 수: " + BankAccount.getTotalAccounts());
        System.out.println("총 계좌 수: " + BankAccount.getTotalAccounts());

        System.out.println("\n========== [3] 입금 테스트 ==========");
        // TODO: acc1에 30,000원 입금
        // TODO: acc1에 15,000원 입금, 메모 "3월 용돈"
        // TODO: acc1에 -5,000원 입금 시도 (오류 케이스)
        acc1.deposit(30_000);
        acc1.deposit(15_000, "3월 용돈");
        acc1.deposit(-5_000);

        System.out.println("\n========== [4] 출금 테스트 ==========");
        // TODO: acc2에서 30,000원 출금
        // TODO: acc2에서 100,000원 출금 시도 (잔액 부족)
        // TODO: acc1에서 11,000,000원 출금 시도 (한도 초과)
        acc2.withdraw(30_000);
        acc2.withdraw(100_000);
        acc1.withdraw(11_000_000);

        System.out.println("\n========== [5] Setter 테스트 ==========");
        // TODO: acc3의 예금주를 "이영희(수정)"으로 변경 후 출력
        // TODO: acc3의 예금주를 ""(빈 문자열)로 변경 시도 (오류 케이스)
        acc3.setOwnerName("이영희(수정)");
        System.out.println(acc3);
        acc3.setOwnerName("");

        System.out.println("\n========== [6] 최종 잔액 확인 ==========");
        // TODO: acc1, acc2, acc3 잔액을 getBalance()로 각각 출력
        System.out.println(acc1.getBalance());
        System.out.println(acc2.getBalance());
        System.out.println(acc3.getBalance());
        
        // 이 코드에서 캡슐화가 잘 된 부분과 개선할 부분을 찾아보기
    }
}
