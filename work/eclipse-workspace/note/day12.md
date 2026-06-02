## Integer 참조형 변수

127까지는 상수pool에 저장
이후는 AutoBoxing ==> new Integer(num);

따라서 127 이하의 값을 Integer로 생성했을 때 동등 비교(==) 를 하면 참이 나오지만
128 이상의 값을 비교하는 경우 false가 나온다 ==>> `hashCode()`, `equals()` 재정의가 되지 않음

---

## while문 라벨링

```java
aa:while(){
  if(true){
    continue aa;
  }
  start();
}
```

continue aa를 만나면 aa라벨링이 된 최상단으로 이동
즉 start() 함수를 실행하지 않음.

---

## 제네릭

### 제네릭 클래스

제네릭 메서드와 제네릭 클래스 차이 구분하기

### 제네릭 메서드

```java
	public <T> /* 매개변수에서 사용하는 제네릭의 위치는 반환타입 앞에 작성 */void swap(T[] arr, int i, int j) {
		T temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
```

```java
class BankUtil {
	<K, S> Box<String, Integer> boxing(K kind, S size) {
		Box<String, Integer> box = null;
//		box = new Box<>();
//		box.setKind("A");
//		box.setSize(100);
		if (kind instanceof String && size instanceof Integer) {
			box = new Box<>((String) kind, (Integer) size);
		}
		return box;
	}
  // ...
}
```

#### 제네릭 메서드의 타입 제한

```java
<? extends ... >
<? super ...>
```

## 스레드

### 프로세스

실행중인 프로그램

### 스레드

코드의 실행 흐름

### 자바의 경우

메인스레드
진행 -> 분할 (멀티 스레드 처럼 동작)
:: 메인스레드가 작업 스레드 보다 먼저 끝나도 종료되지 않음

---

찾아보기
go ~ to ~ ???

---

흐름을 나누는 방법

1. thread 클래스를 이용 -> run() Override -> start()로 사용
2. Runable 인터페이스를 구현 후 Thread 객체로 생성 -> start()로 사용
3. 익명객체로 클래스 정의 -> run() Override -> start()로 사용
