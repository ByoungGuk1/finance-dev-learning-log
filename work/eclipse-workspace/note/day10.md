# 2026-05-28

## 수업 메모

### ENTRY

Map 안의 엔트리

entry -> key 와 value 한 쌍을 지칭

---

### Builder 패턴

정적 내부 클래스의 대표적인 예시:
builder 패턴 -> 자기자신 호출로 체이닝

```java
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

//		기존 사용법
		TransferRequest request2 = new TransferRequest();
		request2.setFromAccount("110-9999-0000");
		request2.setToAccount("110-1234-5678");
		request2.setAmount(new BigDecimal(50000));
	}
}
```

---

### 람다

람다식 사용 => 인터페이스에 메서드가 오직 한개만 있는 경우에만 사용가능

---

### JAR

jar = java archive

외부 라이브러리를 실행할 때 : 메인은 한개만 있어야한다.

jar 만들기:

```text
프로젝트 우클릭
-> export
-> java
-> Runnable JAR file
-> Export destination : 위치 및 이름 설정
  Launch configuration : 최초 실행 위치 설정
```

실행:

```powershell
> java -jar aa.jar
```

---

### 롬복 설치

이클립스에 롬복 설치
ini는 처음 실행시 동작

```text
자바프로젝트
-> configure build path
-> libraries
-> classpath
-> add external jars
-> lombok 추가 후 저장
```

---

### 다른 프로젝트에서 접근하는 방식

라이브러리 단점 : 전체 공개  
모듈 장점 : 필요한것만 공개해서 다른 프로젝트에 전달

---

### import 패키지와 require 모듈

1. default import => java.base.java.lang  
   java.base 이면서 이외의 패키지인 경우 import 후 사용
2. default module => java.base  
   이외의 모듈인 경우 module-info 에 require 후 import 후 사용

---

### 모듈

#### 전이

a -> b -> c
관계에서 a는 c도 requires 해야하고, b는 c를 requires `transitive` 키워드를 사용하여 전이할 수 있는 상태로 만들고 모듈에 추가해야한다.

#### 집합 모듈

자체적인 페키지를 가지지 않고 의존성 설정만을 담당

---

실선으로 클래스 연결 : 상속 관계
점선으로 클래스 연결 : 인터페이스 구현 관계

---

try-with-resources

예외 떠넘기기  
throws 키워드
