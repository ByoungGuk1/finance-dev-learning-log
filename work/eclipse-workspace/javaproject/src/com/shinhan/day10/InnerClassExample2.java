package com.shinhan.day10;

import java.math.BigDecimal;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 5. 28. 오전 10:21:20 설명 : InnerClassExample2
 */
// java.lang 패키지에 없는 class들은 반드시 import하기
//실무에서 가장 많이 보는 패턴 Builder 패턴 (정적 내부 클래스) 
class TransferRequest {
	private String fromAccount;
	private String toAccount;
	private BigDecimal amount;

	// 직접 생성 막기
//	private TransferRequest() {}
	// 정적 내부 클래스로 Builder
	static class Builder {
		private TransferRequest req = new TransferRequest();

		Builder from(String acc) {
			req.fromAccount = acc;
			return this;
		}

		Builder to(String acc) {
			req.toAccount = acc;
			return this;
		}

		Builder amount(BigDecimal amt) {
			req.amount = amt;
			return this;
		}

		TransferRequest build() {
			return req;
		}
	}

//	직접 사용용 setter
	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}

public class InnerClassExample2 {
	public static void main(String[] args) {
//		빌더 사용법
		TransferRequest request = new TransferRequest.Builder().from("110-1234-5678").to("110-9999-0000")
				.amount(new BigDecimal("500000")).build();
		System.out.println(request);

//		기존 사용법
		TransferRequest request2 = new TransferRequest();
		request2.setFromAccount("110-9999-0000");
		request2.setToAccount("110-1234-5678");
		request2.setAmount(new BigDecimal(50000));
	}
}
