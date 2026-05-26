package com.shinhan.day08.lab;

/**
 * =====================================================
 * [중급 LAB 4] 추상 클래스 + 다형성 — 금융 알림 시스템
 * 모듈: Module 02 - 7장 상속
 * 난이도: ★★☆
 * 목표: 1. 추상 클래스 템플릿 메서드 패턴 적용
 *       2. protected 메서드로 공통 로직 공유
 *       3. 매개변수 다형성으로 알림 시스템 설계
 *       4. final 메서드로 프로세스 고정
 * 선행지식: abstract, @Override, protected, 다형성
 * 예상시간: 55분
 * =====================================================
 *
 * 클래스 구조:
 *   Notification (추상 부모) — 알림 공통 프로세스
 *   ├── SmsNotification   (SMS 알림)
 *   ├── EmailNotification (이메일 알림)
 *   └── PushNotification  (앱 푸시 알림)
 *
 *   NotificationService — 알림 발송 서비스 (매개변수 다형성)
 *
 * 핵심 패턴: ```Template Method Pattern```
 *   → 부모가 프로세스 순서를 final로 고정
 *   → 자식은 세부 내용만 구현
 */

// =====================================================
// Notification — 추상 부모 (템플릿 메서드 패턴)
//------------템플릿 메서드 패턴 — 상속으로 해결--------------
// 부모가 프로세스 순서를 final로 고정
// =====================================================
abstract class Notification {

    // TODO [★☆☆] 필드를 선언하세요 (3분)
    // - recipient   : String   (수신자, protected)
    // - title       : String   (제목, protected)
    // - message     : String   (내용, protected)
    // - isDelivered : boolean  (발송 완료 여부, private, 초기값 false)
	protected String recipient;
	protected String title;
	protected String message;
	private boolean isDelivered = false;
	
	
    // TODO [★☆☆] 생성자를 작성하세요 (3분)
    // 매개변수: recipient, title, message
	public Notification(String recipient, String title, String message) {
		this.recipient = recipient;
		this.title = title;
		this.message = message;
	}
	

    // TODO [★★★] 템플릿 메서드 send()를 final로 구현하세요 (10분)
    // → 발송 프로세스를 고정 (자식이 순서를 바꿀 수 없음)
    //
    // public final void send() {
    //     System.out.println("\n[발송시작] " + getChannelName());
    //     if (!validate()) {                    // ① 유효성 검사
    //         System.out.println("발송 실패: 유효성 검사 오류");
    //         return;
    //     }
    //     String formatted = formatMessage();   // ② 메시지 포맷
    //     deliver(formatted);                   // ③ 실제 발송 (자식 구현)
    //     isDelivered = true;
    //     logDelivery();                        // ④ 발송 기록
    // }
    //
    // ⚠️ final → 자식이 send() 자체를 바꿀 수 없음
    //            자식은 deliver()만 구현하면 됨
	public final void send() {
		isDelivered = false;
		 System.out.println("\n[발송시작] " + getChannelName());
		 if (!validate()) {
		     System.out.println("발송 실패: 유효성 검사 오류");
		     return;
		 }
		 String formatted = formatMessage();
		 deliver(formatted);
		 isDelivered = true;
		 logDelivery();
	}


    // TODO [★★☆] protected 공통 메서드를 구현하세요 (8분)
    // → 자식이 재사용할 수 있는 공통 로직
    //
    // validate() : boolean
    //   - recipient가 null이거나 비어있으면 false
    //   - message가 null이거나 비어있으면 false
    //   - 정상이면 true
    //   (protected — 자식이 super.validate() 호출 가능)
	protected boolean validate() {
		boolean result = true;
		if(recipient == null || recipient.equals("")) {
			result = false;
		}
		if(message == null || message.equals("")) {
			result = false;
		}
		return result;
	}

    // formatMessage() : String
    //   - "[제목] title\n[내용] message" 형태로 반환
    //   (protected)
	protected String formatMessage() {
		return "[제목] "+title+"\n[내용] "+message;
	}
	
    // logDelivery()
    //   - "[발송완료] recipient | getChannelName() | title" 출력
    //   (private — 자식이 바꾸면 안 됨)
	private void logDelivery() {
		System.out.println("[발송완료] "+recipient+" | "+getChannelName()+" | "+title);
	}

    // TODO [★★☆] 추상 메서드 2개를 선언하세요 (3분)
    // deliver(String formattedMessage) — 실제 발송 (자식마다 방식 다름)
    // getChannelName() : String        — 채널명 반환 ("SMS" / "이메일" / "앱푸시")
	public abstract void deliver(String formattedMessage);
	public abstract String getChannelName();


    // TODO [★☆☆] Getter를 작성하세요 (2분)
    // isDelivered(), getRecipient(), getTitle()
	public String getRecipient() {
		return recipient;
	}
	public String getTitle() {
		return title;
	}
	public boolean isDelivered() {
		return isDelivered;
	}	
}


// =====================================================
// SmsNotification — SMS 알림
// =====================================================
class SmsNotification extends Notification {

    private String phoneNumber; // 수신 전화번호


    // TODO [★☆☆] 생성자 (3분)
    // 매개변수: phoneNumber, title, message
    // super(phoneNumber, title, message) 호출
    // this.phoneNumber = phoneNumber
	public SmsNotification(String phoneNumber, String title, String message) {
		super(phoneNumber, title, message);
		this.phoneNumber = phoneNumber;
	}


    // TODO [★★☆] validate() 오버라이딩 (5분)
    // 조건1: super.validate() 먼저 호출 → false면 return false
    // 조건2: phoneNumber가 숫자 10~11자리인지 확인
    //   힌트: phoneNumber.matches("[0-9]{10,11}")
    // 조건3: 모두 통과하면 true 반환
    // ⚠️ 주의: 접근 제한을 더 강하게 오버라이딩 불가
    //          protected → private 불가, protected 유지
	@Override
	protected boolean validate() {
		boolean result = super.validate();
		if(result == false)	return result;
		if(!phoneNumber.matches("[0-9]{10,11}")) {
			result = false;
		}
		return result;
	}


    // TODO [★★☆] deliver() 구현 (5분)
    // 출력:
    //   "[SMS 발송]"
    //   "수신번호: 010-XXXX-XXXX"
    //   "내용: formattedMessage (90자 이내로 자르기)"
    //   힌트: formattedMessage.length() > 90 ? formattedMessage.substring(0,90)+"..." : formattedMessage
	@Override
	public void deliver(String formattedMessage) {
		System.out.println("[SMS 발송]");
		System.out.println("수신번호: "+recipient);
		String shortMessage = formattedMessage.length() > 90 ? formattedMessage.substring(0, 90) + "..." : formattedMessage;
		System.out.println("내용: "+ shortMessage);
	}


	// TODO [★☆☆] getChannelName() 구현 (1분)
    // 반환: "SMS"
	@Override
	public String getChannelName() {
		return "SMS";
	}
}


// =====================================================
// EmailNotification — 이메일 알림
// =====================================================
class EmailNotification extends Notification {

    private String emailAddress;
    private String senderName;


    // TODO [★☆☆] 생성자 (3분)
    // 매개변수: emailAddress, senderName, title, message
    // super(emailAddress, title, message) 호출
	public EmailNotification(String emailAddress, String senderName, String title, String message) {
		super(emailAddress, title, message);
		this.emailAddress = emailAddress;
		this.senderName = senderName;
	}


	// TODO [★★☆] validate() 오버라이딩 (5분)
    // super.validate() 후
    // 이메일 형식 검사: emailAddress.contains("@") && emailAddress.contains(".")
    // 형식 불일치 시 "[유효성오류] 이메일 형식 불일치" 출력 후 false
	@Override
	protected boolean validate() {
		boolean result =  super.validate();
		if(result == false)	return result;
		if(!(emailAddress.contains("@") && emailAddress.contains("."))) {
	        System.out.println("[유효성오류] 이메일 형식 불일치");
			result = false;
		}
		return result;
	}


    // TODO [★★☆] deliver() 구현 (5분)
    // 출력:
    //   "[이메일 발송]"
    //   "From: senderName <noreply@shinhanbank.com>"
    //   "To: emailAddress"
    //   formattedMessage 전체 출력 (SMS와 달리 길이 제한 없음)
	@Override
	public void deliver(String formattedMessage) {
		System.out.println("[이메일 발송]");
		System.out.println("From: "+senderName+" <noreply@shinhanbank.com>");
		System.out.println("To: "+recipient);
		System.out.println(formattedMessage);
	}


    // TODO [★☆☆] getChannelName() 구현 (1분)
    // 반환: "이메일"
	@Override
	public String getChannelName() {
		return "이메일";
	}
}


// =====================================================
// PushNotification — 앱 푸시 알림
// =====================================================
class PushNotification extends Notification {

    private String deviceToken;
    private String appName;
    
    // TODO [★☆☆] 생성자 (3분)
    // 매개변수: deviceToken, appName, title, message
    // super(deviceToken, title, message) 호출
	public PushNotification(String deviceToken, String appName,String title, String message) {
		super(deviceToken, title, message);
		this.deviceToken = deviceToken;
		this.appName = appName;
	}

	
    // TODO [★★☆] deliver() 구현 (5분)
    // 출력:
    //   "[앱 푸시 발송]"
    //   "앱: appName | 디바이스: deviceToken 앞 8자리***"
    //   "알림: title — message (50자 이내)"
	@Override
	public void deliver(String formattedMessage) {
		message = message.length() > 50 ? message.substring(0,50)+"..." : message;
		System.out.println("[앱 푸시 발송]");
		System.out.println("앱: "+appName+" | 디바이스: "+deviceToken.substring(0, 8)+"***");
		System.out.println("알림: "+title+" — "+message);
	}


	// TODO [★☆☆] getChannelName() 구현 (1분)
    // 반환: "앱푸시"
	@Override
	public String getChannelName() {
		return "앱푸시";
	}
}


// =====================================================
// NotificationService — 알림 서비스 (매개변수 다형성)
// =====================================================
class NotificationService {
    // TODO [★★★] sendNotification(Notification notification) (8분)
    // 매개변수를 Notification 타입으로 받아서 send() 호출
    // → 어떤 자식 알림이 와도 동일하게 처리 (매개변수 다형성!)
    // 발송 후 isDelivered() 결과에 따라 성공/실패 출력
	public boolean sendNotification(Notification notification) {
		notification.send();
		return notification.isDelivered();
	}

    // TODO [★★★] sendBulk(Notification... notifications) (10분)
    // 가변길이 매개변수로 여러 알림을 한 번에 발송
    // 결과 요약 출력:
    //   "=== 일괄발송 결과 ==="
    //   "총 N건 | 성공 N건 | 실패 N건"
	public void sendBulk(Notification ... notifications) {
		int totalCount = notifications.length;
		int successCount = 0;
		int failCount = 0;
		for(Notification notification : notifications) {
			notification.send();
			if(notification.isDelivered()) {
				successCount++;
			} else {
				failCount++;
			}
		}
		System.out.println("=== 일괄발송 결과 ===");
		System.out.println("총 "+totalCount+"건 | 성공 "+successCount+"건 | 실패 "+failCount+"건");
	}


    // TODO [★★☆] sendByType(Notification[] notifications, String channelName) (8분)
    // channelName과 일치하는 알림만 골라서 발송
    // 힌트: notification.getChannelName().equals(channelName)
    // 출력: "[채널필터] SMS 채널 N건 발송"
	public int sendByType(Notification[] notifications, String channelName) {
		int sendCount = 0;
		for(Notification notification : notifications) {
			if(notification.getChannelName().equals(channelName)) {
				notification.send();
				sendCount++;
			}
		}
		System.out.println("[채널필터] "+channelName+" 채널 "+sendCount+"건 발송");
		return sendCount;
	}
}


// =====================================================
// Lab4Main — 실행
// =====================================================
public class Lab4_TemplateMethod {

    public static void main(String[] args) {

        System.out.println("========== [1] 알림 객체 생성 ==========");
        // TODO [★☆☆] 알림 3종류를 각각 생성하세요 (3분)
        // sms1  : SmsNotification   ("01012345678", "입금알림", "홍길동 계좌에 30,000원 입금")
        // email1: EmailNotification ("hong@email.com", "신한은행", "거래명세서", "3월 거래내역...")
        // push1 : PushNotification  ("ABC123TOKEN", "신한SOL", "이체완료", "김철수에게 50,000원 이체")
        SmsNotification sms = new SmsNotification("01012345678", "입금알림", "홍길동 계좌에 30,000원 입금");
        EmailNotification email = new EmailNotification("hong@email.com", "신한은행", "거래명세서", "3월 거래내역...");
        PushNotification app = new PushNotification("ABC123TOKEN", "신한SOL", "이체완료", "김철수에게 50,000원 이체");
        

        System.out.println("\n========== [2] 템플릿 메서드 send() ==========");
        // TODO: 각 알림 객체의 send() 호출
        // → 프로세스 순서(validate→format→deliver→log)가 동일함을 확인
         sms.send();
         email.send();
         app.send();


        System.out.println("\n========== [3] 유효성 검사 실패 케이스 ==========");
        // TODO [★★☆] 잘못된 데이터로 생성 후 send() 호출 (3분)
        SmsNotification bad1 = new SmsNotification("INVALID", "테스트", "내용");
        bad1.send();  // validate() 실패 → 발송 안 됨
        
        EmailNotification bad2 = new EmailNotification("이메일아님", "신한", "제목", "내용");
        bad2.send();  // 이메일 형식 오류


        System.out.println("\n========== [4] 매개변수 다형성 ==========");
        // TODO [★★☆] NotificationService로 일괄 처리 (3분)
         NotificationService service = new NotificationService();
         service.sendNotification(sms);    // Notification 타입으로 받음
         service.sendNotification(email);
         service.sendNotification(app);


        System.out.println("\n========== [5] 일괄 발송 ==========");
        // TODO [★★★] sendBulk()로 한 번에 발송 (3분)
         service.sendBulk(
             new SmsNotification("01098765432", "출금알림", "20,000원 출금"),
             new EmailNotification("kim@email.com", "신한은행", "월명세서", "2월 내역"),
             new PushNotification("XYZ789TOKEN", "신한SOL", "혜택알림", "이번달 캐시백 5,000원")
         );


        System.out.println("\n========== [6] 채널별 필터 발송 ==========");
        // TODO [★★★] SMS 채널만 골라서 발송 (3분)
         Notification[] all = { sms, email, app, bad1 };
         service.sendByType(all, "SMS");
    }
}
