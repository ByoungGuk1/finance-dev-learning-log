package com.shinhan.day08.lab;

/**
 * ===================================================== [고급 LAB 6] sealed 클래스 +
 * 7장 종합 — 금융 이벤트 시스템 모듈: Module 02 - 7장 상속 난이도: ★★★ 목표: 1. sealed 클래스로 상속 범위 제한
 * (Java 17+) 2. final / non-sealed 자식 클래스 설계 3. 추상 클래스 + 템플릿 메서드 패턴 적용 4.
 * instanceof 패턴매칭으로 타입 안전 처리 선행지식: 7장 전체 (상속, 추상, 다형성, instanceof, final) 예상시간:
 * 80분 =====================================================
 *
 * 시나리오: 은행 트랜잭션 이벤트 처리 시스템
 *
 * sealed 계층: BankEvent (sealed 추상) — permits 3개만 허용 ├── DepositEvent (final) —
 * 입금 이벤트 (더 이상 상속 불가) ├── WithdrawEvent (final) — 출금 이벤트 (더 이상 상속 불가) └──
 * TransferEvent (non-sealed) — 이체 이벤트 (추가 확장 허용) └── InternationalTransfer —
 * 해외송금
 *
 * EventProcessor (추상) — 이벤트 처리기 (템플릿 메서드) 순서 고정: canProcess → doProcess →
 * afterProcess ├── AuditProcessor (final) — 감사 처리기 (모든 이벤트를 기록하고 위험 감지, 감사 로직을
 * 자식이 변경하면 안 됨 (보안)) └── AlertProcessor — 알림 처리기 (기준금액 이상일 때만 알림
 * 발송,canProcess() 오버라이딩으로 필터링 )
 *
 * EventBus (싱글톤) — 이벤트 라우터
 */

// =====================================================
// BankEvent — sealed 추상 클래스 (핵심!)
// =====================================================

// TODO [★★★] sealed 추상 클래스를 선언하세요 (8분)

sealed abstract class BankEvent permits DepositEvent, WithdrawEvent, TransferEvent {
// 필드 (protected):
//   - eventId   : final String  (이벤트 ID)
//   - accountId : final String  (계좌번호)
//   - ownerName : String        (예금주)
//   - amount    : final int     (금액)
//   - timestamp : final String  (발생시각 — 간단히 "2026-05-26 09:00" 고정값 사용)
	protected final String eventId; // (이벤트 ID)
	protected final String accountId; // (계좌번호)
	protected String ownerName; // (예금주)
	protected final int amount; // (금액)
	protected final String timestamp = "2026-05-26 09:00"; // (발생시각 — 간단히 "2026-05-26 09:00" 고정값 사용)

// 생성자: (eventId, accountId, ownerName, amount)
//   → 모든 필드 초기화
//   → "[이벤트생성] eventId | accountId | N원" 출력
	public BankEvent(String eventId, String accountId, String ownerName, int amount) {
		super();
		this.eventId = eventId;
		this.accountId = accountId;
		this.ownerName = ownerName;
		this.amount = amount;
	}

// 추상 메서드:
//   - getEventType()   : String  (이벤트 유형명)
//   - getDescription() : String  (이벤트 설명)
	public abstract String getEventType();

	public abstract String getDescription();

// 공통 메서드 printEvent():
//   출력:
//   "[이벤트] 유형 | eventId"
//   "  계좌: accountId | 예금주: ownerName"
//   "  금액: N원 | 시각: timestamp"
//   "  설명: getDescription()"
	public void printEvent() {
		System.out.println("[이벤트] " + getEventType() + " | " + eventId);
		System.out.println("\t계좌: " + accountId + " | 예금주: " + ownerName);
		System.out.println("\t금액: " + amount + "원 | 시각: " + timestamp);
		System.out.println("\t설명: " + getDescription());
	}

// Getter:
//   getEventId(), getAccountId(), getOwnerName(), getAmount()
	public String getEventId() {
		return eventId;
	}

	public String getAccountId() {
		return accountId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public int getAmount() {
		return amount;
	}

	public String getTimestamp() {
		return timestamp;
	}
}
// =====================================================
// DepositEvent — 입금 이벤트 (final: 더 이상 상속 불가)
// =====================================================

// TODO [★★☆] final 자식 클래스를 구현하세요 (7분)
//
final class DepositEvent extends BankEvent {
// 추가 필드:
//   - depositorName : String (입금자명, private)
	private String depositorName;

// 생성자: (accountId, ownerName, amount, depositorName)
//   eventId = "DEP-" + System.currentTimeMillis() % 10000
//   super(eventId, accountId, ownerName, amount) 호출
//   this.depositorName = depositorName

	public DepositEvent(String accountId, String ownerName, int amount, String depositorName) {
		this("DEP-" + System.currentTimeMillis() % 10000, accountId, ownerName, amount, depositorName);
	}

	public DepositEvent(String eventId, String accountId, String ownerName, int amount, String depositorName) {
		super(eventId, accountId, ownerName, amount);
		this.depositorName = depositorName;
	}

// getEventType()    → "입금"
	@Override
	public String getEventType() {
		return "입금";
	}

// getDescription()  → "입금자: depositorName → 계좌: accountId (+N원)"
	@Override
	public String getDescription() {
		return "입금자: " + depositorName + " → 계좌: " + accountId + " (+" + amount + "원)";
	}

// getDepositorName(): String  (Getter)
	public String getDepositorName() {
		return depositorName;
	}

// ⚠️ final 확인: 아래 주석 해제 → 컴파일 오류 직접 확인
// class FakeDeposit extends DepositEvent { } // ❌ final 상속 불가
}

// =====================================================
// WithdrawEvent — 출금 이벤트 (final)
// =====================================================

// TODO [★★☆] final 자식 클래스를 구현하세요 (7분)

enum WithdrawEventType {
	ATM, 창구, 앱
}

final class WithdrawEvent extends BankEvent {
// 추가 필드:
//   - channel : String (출금 채널: "ATM" / "창구" / "앱", private)
	private WithdrawEventType channel;

// 생성자: (accountId, ownerName, amount, channel)
//   eventId = "WIT-" + System.currentTimeMillis() % 10000
//   super(eventId, accountId, ownerName, amount) 호출
	public WithdrawEvent(String accountId, String ownerName, int amount, WithdrawEventType channel) {
		this("WIT-" + System.currentTimeMillis() % 10000, accountId, ownerName, amount, channel);
	}

	public WithdrawEvent(String eventId, String accountId, String ownerName, int amount, WithdrawEventType channel) {
		super(eventId, accountId, ownerName, amount);
		this.channel = channel;
	}

// getEventType()   → "출금"
	@Override
	public String getEventType() {
		return "출금";
	}

// getDescription() → "채널: channel | 계좌: accountId (-N원)"
	@Override
	public String getDescription() {
		return "채널: " + channel + " | 계좌: " + accountId + " (-" + amount + "원)";
	}

// getChannel()     : String  (Getter)
	public WithdrawEventType getChannel() {
		return channel;
	}

// isLargeWithdraw() : boolean
//   → amount >= 1_000_000 이면 true  (100만원 이상 대량출금 감지용)
	public boolean isLargeWithdraw() {
		return amount >= 1_000_000;
	}
}
// =====================================================
// TransferEvent — 이체 이벤트 (non-sealed: 자식 추가 가능)
// =====================================================

// TODO [★★★] non-sealed 자식 클래스를 구현하세요 (8분)
//
non-sealed class TransferEvent extends BankEvent {
// 추가 필드:
//   - fromAccountId : String (출금 계좌, private)
//   - toAccountId   : String (입금 계좌, private)
//   - toOwnerName   : String (수신자명, private)
	private String formAccountId;
	private String toAccountId;
	private String toOwnerName;

// 생성자: (fromAccountId, toAccountId, fromOwner, toOwner, amount)
//   eventId   = "TRF-" + System.currentTimeMillis() % 10000
//   accountId = fromAccountId  (부모의 accountId = 출금계좌)
//   super(eventId, fromAccountId, fromOwner, amount) 호출
	public TransferEvent(String fromAccountId, String toAccountId, String fromOwner, String toOwner, int amount) {
		this("TRF-" + System.currentTimeMillis() % 10000, fromAccountId, fromOwner, amount, fromAccountId, toAccountId,
				toOwner);
	}

	public TransferEvent(String eventId, String accountId, String ownerName, int amount, String formAccountId,
			String toAccountId, String toOwnerName) {
		super(eventId, accountId, ownerName, amount);
		this.formAccountId = formAccountId;
		this.toAccountId = toAccountId;
		this.toOwnerName = toOwnerName;
	}

// getEventType()   → "이체"
	@Override
	public String getEventType() {
		return "이체";
	}

// getDescription() → "fromOwner(fromAccountId) → toOwner(toAccountId) N원"
	@Override
	public String getDescription() {
		return ownerName + "(" + formAccountId + ") → " + toOwnerName + "(" + toAccountId + ") " + amount + "원";
	}

// Getter: getFromAccountId(), getToAccountId(), getToOwnerName()
	public String getFormAccountId() {
		return formAccountId;
	}

	public String getToAccountId() {
		return toAccountId;
	}

	public String getToOwnerName() {
		return toOwnerName;
	}

// ✅ non-sealed 확인: InternationalTransfer가 이 클래스를 상속 가능!

}
// =====================================================
// InternationalTransfer — 해외송금 (TransferEvent 자식)
// non-sealed 덕분에 추가 상속 가능!
// =====================================================

// TODO [★★★] TransferEvent를 상속하여 구현하세요 (8분)
//
class InternationalTransfer extends TransferEvent {
// 추가 필드:
//   - targetCurrency : String  (목표통화: "USD" / "JPY" / "EUR", private)
//   - exchangeRate   : double  (환율, private)
//   - fee            : int     (해외송금 수수료, private)
	private String targetCurrency;
	private double exchangeRate;
	private int fee;

// 생성자: (fromAccountId, toAccountId, fromOwner, toOwner,
//          amount, targetCurrency, exchangeRate, fee)
//   super(fromAccountId, toAccountId, fromOwner, toOwner, amount) 호출
	public InternationalTransfer(String fromAccountId, String toAccountId, String fromOwner, String toOwner, int amount,
			String targetCurrency, double exchangeRate, int fee) {
		super(fromAccountId, toAccountId, fromOwner, toOwner, amount);
		this.targetCurrency = targetCurrency;
		this.exchangeRate = exchangeRate;
		this.fee = fee;
	}

// getEventType()   → "해외송금"
	@Override
	public String getEventType() {
		return "해외송금";
	}

// getDescription() → super.getDescription()
//                    + " | " + targetCurrency
//                    + " 환율:" + exchangeRate
//                    + " 수수료:" + fee + "원"
	@Override
	public String getDescription() {
		return super.getDescription() + " | " + targetCurrency + " 환율:" + exchangeRate + " 수수료:" + fee + "원";
	}

// calculateConvertedAmount() : double
//   변환금액 = (amount - fee) * exchangeRate
//   출력: "[환전] N원 → targetCurrency N.## (환율: exchangeRate)"
//   힌트: String.format("%.2f", 변환금액)
	public double calculateConvertedAmount() {
		double result = (amount - fee) * exchangeRate;
		String resultFormat = String.format("%.2f", result);
		System.out.println(
				"[환전] " + amount + "원 → " + targetCurrency + " " + resultFormat + " (환율: " + exchangeRate + ")");
		return result;
	}

// Getter: getTargetCurrency(), getExchangeRate(), getFee()
	public String getTargetCurrency() {
		return targetCurrency;
	}

	public double getExchangeRate() {
		return exchangeRate;
	}

	public int getFee() {
		return fee;
	}
}

// =====================================================
// EventProcessor — 추상 이벤트 처리기 (템플릿 메서드)
// =====================================================
abstract class EventProcessor {

	protected String processorName;

	public EventProcessor(String processorName) {
		this.processorName = processorName;
	}

	// TODO [★★★] 템플릿 메서드 process()를 final로 구현하세요 (8분)

	public final void process(BankEvent event) {
		System.out.println("\n[" + processorName + "] 처리 시작");
		if (!canProcess(event)) {
			System.out.println("  처리 대상 아님: " + event.getEventType());
			return;
		}
		doProcess(event);
		afterProcess(event); // private 메서드 호출
	}

// afterProcess(BankEvent event) — private 메서드
//   출력: "[완료] processorName | eventId | eventType 처리됨"
	private void afterProcess(BankEvent event) {
		System.out.println("[완료] " + processorName + " | " + event.eventId + " | " + event.getEventType() + " 처리됨");
	}
// ⚠️ final → 자식이 process() 순서 자체를 바꿀 수 없음

// TODO [★☆☆] 추상 메서드와 기본 메서드를 선언하세요 (3분)
	public abstract void doProcess(BankEvent event);
//   → 자식이 반드시 구현 (실제 처리 로직)

	protected boolean canProcess(BankEvent event) {
//   → 기본값 true 반환 (자식이 필요시 오버라이딩)
		return true;
	}
}

// =====================================================
// AuditProcessor — 감사 처리기 (final)
// =====================================================

// TODO [★★★] AuditProcessor를 구현하세요 (10분)
//
final class AuditProcessor extends EventProcessor {
// 생성자: super("감사처리기")
	public AuditProcessor() {
		super("감사처리기");
	}

// doProcess(BankEvent event):
	public void doProcess(BankEvent event) {
		// 1. event.printEvent() 호출
		event.printEvent();
		// 2. instanceof로 타입별 추가 감사 처리:
		if (event instanceof WithdrawEvent) {
			WithdrawEvent we = (WithdrawEvent) event; // 강제 타입 변환
			if (we.isLargeWithdraw()) {
				System.out.println("  [경보] 대량출금 감지! 보안팀 통보");
			}
		} else if (event instanceof InternationalTransfer) {
			InternationalTransfer it = (InternationalTransfer) event;
			it.calculateConvertedAmount();
			System.out.println("  [경보] 해외송금 감지! 외환팀 통보");
		} else if (event instanceof TransferEvent) {
			TransferEvent te = (TransferEvent) event;
			System.out.println("  수신자: " + te.getToOwnerName());
		}
	}

// ⚠️ instanceof 순서 중요:
//    InternationalTransfer를 TransferEvent보다 먼저 검사해야 함
//    (자식이 부모보다 먼저)
}

// =====================================================
// AlertProcessor — 알림 처리기
// =====================================================

// TODO [★★☆] AlertProcessor를 구현하세요 (7분)
//
class AlertProcessor extends EventProcessor {
// 추가 필드:
//   - amountThreshold : int (알림 기준금액, private)
	private int amountThreshold;

// 생성자: (amountThreshold)
//   super("알림처리기") 호출
//   this.amountThreshold = amountThreshold
	public AlertProcessor(int amountThreshold) {
		super("알림처리기");
		this.amountThreshold = amountThreshold;
	}

// canProcess(BankEvent event) 오버라이딩:
//   → event.getAmount() >= amountThreshold 이면 true
//   → 미만이면 false (기준 미달 → 알림 안 보냄)
	@Override
	protected boolean canProcess(BankEvent event) {
		return event.getAmount() >= amountThreshold;
	}

// doProcess(BankEvent event):
//   출력:
//   "[알림발송] ownerName 고객님"
//   "  이벤트: getEventType() | 금액: N원"
//   "[SMS] 010-****-**** 발송 완료"
	@Override
	public void doProcess(BankEvent event) {
		System.out.println("[알림발송] " + event.ownerName + " 고객님");
		System.out.println("\t이벤트: " + event.getEventType() + " | 금액: " + event.amount + "원");
		System.out.println("[SMS] 010-****-**** 발송 완료");
	}
}

// =====================================================
// EventBus — 이벤트 라우터 (싱글톤 — static 필드 방식)
// =====================================================
class EventBus {

	// TODO [★★☆] static 필드 싱글톤을 구현하세요 (5분)
// (내부 클래스 미학습 → static 필드 방식 사용)

	private static EventBus instance = null;

	private EventBus() {
		System.out.println("[EventBus] 이벤트 버스 시작");
	}

	public static EventBus getInstance() {
		if (instance == null) {
			instance = new EventBus(); // 최초 1회만 생성
		}
		return instance;
	}

// TODO [★★☆] 처리기 목록 필드를 선언하세요 (2분)
	private EventProcessor[] processors = new EventProcessor[10];
	private int processorCount = 0;

// TODO [★★☆] registerProcessor(EventProcessor processor) (4분)
// 조건: processorCount >= processors.length 이면 "처리기 한도 초과" 출력 후 return
// 정상: processors[processorCount++] = processor
//       "[등록] processor.processorName 처리기 등록됨" 출력
	public void registerProcessor(EventProcessor processor) {
		if (processorCount >= processors.length) {
			System.out.println("처리기 한도 초과");
			return;
		}
		processors[processorCount++] = processor;
		System.out.println("[등록] " + processor.processorName + " 처리기 등록됨");
	}

// TODO [★★★] publish(BankEvent event) (6분)
// 출력: "[EventBus] 이벤트 발행: eventType | eventId"
// for (int i = 0; i < processorCount; i++) 순회
//   → processors[i].process(event) 호출
	public void publish(BankEvent event) {
		System.out.println("[EventBus] 이벤트 발행: " + event.getEventType() + " | " + event.eventId);
		for (int i = 0; i < processorCount; i++) {
			processors[i].process(event);
		}
	}

// TODO [★★☆] publishAll(BankEvent... events) 가변길이 매개변수 (4분)
// 출력: "=== 일괄 발행 시작 (N건) ==="
// for (BankEvent event : events) → publish(event) 호출
// 출력: "=== 일괄 발행 완료 ==="
	public void publishAll(BankEvent... events) {
		System.out.println("=== 일괄 발행 시작 (" + events.length + "건) ===");
		for (BankEvent event : events) {
			publish(event);
		}
		System.out.println("=== 일괄 발행 완료 ===");
	}
}

// =====================================================
// Lab6_SealedAndSystem — main
// =====================================================
public class Lab6_SealedAndSystem {

	public static void main(String[] args) {

		System.out.println("========== [1] sealed 상속 제한 확인 ==========");
// ✅ permits 목록에 없는 클래스가 상속 시도 → 컴파일 오류
// 아래 주석 해제해서 직접 확인해보세요
// class HackEvent extends BankEvent { } // ❌ sealed 위반!

		System.out.println("========== [2] 이벤트 객체 생성 ==========");
// TODO [★★☆] 이벤트 4종류를 생성하세요 (5분)
//
// e1: DepositEvent("110-001", "홍길동", 500_000, "김철수")
// e2: WithdrawEvent("110-001", "홍길동", 1_500_000, "ATM")  ← 대량출금!
// e3: TransferEvent("110-001", "110-002", "홍길동", "이영희", 300_000)
// e4: InternationalTransfer("110-001", "US-9999", "홍길동", "John",
//                           1_000_000, "USD", 1350.0, 5_000)
		BankEvent e1 = new DepositEvent("110-001", "홍길동", 500_000, "김철수");
		BankEvent e2 = new WithdrawEvent("110-001", "홍길동", 1_500_000, WithdrawEventType.ATM);
		BankEvent e3 = new TransferEvent("110-001", "110-002", "홍길동", "이영희", 300_000);
		BankEvent e4 = new InternationalTransfer("110-001", "US-9999", "홍길동", "John", 1_000_000, "USD", 1350.0, 5_000);

		System.out.println("\n========== [3] 이벤트 출력 ==========");
// TODO [★☆☆] BankEvent 타입 배열로 일괄 출력 (3분)
		BankEvent[] events = { e1, e2, e3, e4 };
		for (int i = 0; i < events.length; i++) {
			events[i].printEvent();
		}

		System.out.println("\n========== [4] EventBus 등록 & 발행 ==========");
// TODO [★★★] EventBus 구성 후 이벤트 발행 (5분)
//
		EventBus bus = EventBus.getInstance();

		bus.registerProcessor(new AuditProcessor());
		bus.registerProcessor(new AlertProcessor(1_000_000)); // 100만원 이상 알림

		bus.publish(e1); // 입금 50만원 → 알림 기준 미달 → AuditProcessor만 처리
		bus.publish(e2); // 출금 150만원 → 대량출금 경보 + 알림 발송
		bus.publish(e4); // 해외송금 → 외환팀 통보 + 알림 발송

		System.out.println("\n========== [5] 일괄 발행 ==========");
// TODO [★☆☆] publishAll()로 한 번에 발행 (2분)
		bus.publishAll(e1, e2, e3, e4);

		System.out.println("\n========== [6] instanceof 직접 처리 ==========");
// TODO [★★★] instanceof + 강제 타입 변환으로 타입별 처리 (7분)
//
		BankEvent[] all = { e1, e2, e3, e4 };
		for (int i = 0; i < all.length; i++) {
			BankEvent event = all[i];

			if (event instanceof DepositEvent) {
				DepositEvent de = (DepositEvent) event;
				System.out.println("입금자: " + de.getDepositorName());

			} else if (event instanceof InternationalTransfer) {
				// ⚠️ InternationalTransfer를 TransferEvent보다 먼저!
				InternationalTransfer it = (InternationalTransfer) event;
				it.calculateConvertedAmount();

			} else if (event instanceof WithdrawEvent) {
				WithdrawEvent we = (WithdrawEvent) event;
				if (we.isLargeWithdraw()) {
					System.out.println("대량출금 감지: " + we.getAmount() + "원");
				}

			} else if (event instanceof TransferEvent) {
				TransferEvent te = (TransferEvent) event;
				System.out.println("일반이체 수신자: " + te.getToOwnerName());
			}
		}

		System.out.println("\n========== [7] 싱글톤 동일 인스턴스 확인 ==========");
// TODO [★☆☆] 싱글톤 검증 (2분)
		EventBus bus2 = EventBus.getInstance();
		System.out.println("같은 인스턴스? " + (bus == bus2)); // true 여야 함

	}
}
