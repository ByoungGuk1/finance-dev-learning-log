팀 기록지:
|버그 | 위치(클래스/메서드) | 유형 | 원인 | 수정 방법|
|------ | ------------------ | ---- | ---- | --------|
|1|패키지|--|--|--|
|2|BankBranch/addProduct|논리|배열의 크기 넘어가면 인덱스에러| (productCount >= 10) 예외 처리

3.

```java
if (p instanceof FinancialProduct) {
    System.out.println("[공통처리] " + p.getProductName());

} else if (p instanceof SavingsAccount) {
    SavingsAccount sa = (SavingsAccount) p;
    int interest = sa.calculateReturn(1);
    sa.deposit(interest);

} else if (p instanceof LoanAccount) {
      LoanAccount la = (LoanAccount) p;
      System.out.printf("[이자청구] %,d원%n", la.calculateReturn(1));
}
```

->

```java
  //업캐스팅 된 p 객체는 항상 true이기 때문에 문구를 따로 밖으로 빼기

```

4.

```java
public void processLoanOnly() {
  System.out.println("\n=== 대출 계좌 처리 ===");
  for (int i = 0; i < productCount; i++) {
    // instanceof 확인 없이 바로 강제 타입 변환
    LoanAccount la = (LoanAccount) products[i]; // ← 여기: ClassCastException 발생!
    System.out.printf("[대출처리] %s | 잔액: %,d원%n",
    la.getProductName(), la.getLoanAmount());
  }
}
```

```java
// instanceof 분기 처리
for (int i = 0; i < productCount; i++) {
  if(products[i] instanceof LoanAccount la) {
    System.out.printf("[대출처리] %s | 잔액: %,d원%n",
    la.getProductName(), la.getLoanAmount());
  }
}
```

---

jdbc
java database connect

interfae => 생략 / public

추상 클래스와 인터페이스의 차이

인터페이스끼리의 상속은 다중 상속이 가능하다 (extends)

인터페이스 구현도 다중 구현이 가능하다. (implements)

인터페이스 -> ocp원칙 -> 구현체 변경만으로 다중 구현

---

## 중첩 선언과 익명 객체

인스턴스 멤버 클래스 -> 클래스 내부의 인스턴스 클래스
정적 멤버 클래스 -> 클래스 내부의 static 클래스
로컬 클래스 -> 함수 내부의 클래스
