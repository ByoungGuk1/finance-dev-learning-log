try-with-resources
명칭 기억하기

---

## Exception

catch문에서 return을 하더라도 finally는 반드시 수행

---

## record

`public record Person(String name, int age) {}`
constructor, getter, toString, equals 메소드를 자동 생성

---

## lombok

@RequiredArgsConstructor

---

## String Class

한글 1자를 UTF-8로 인코딩하면 3바이트가 되고, EUC-KR로 인코딩하면 2바이트가 됨
이클립스로 확인했을 땐 UTF-8 사용중

---

## StringBuilder

append, insert, delete, replace, toString

---

## StringTokenizer

쓰이는 이유

---

## 날짜 표현 타입과 DecimalFormat

```java
	private static void f1() {
//		레거시에서 주로 사용
		System.out.println("java.util.Date");
		Date d1 = new Date();
		System.out.println(d1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		System.out.println(sdf.format(d1));
	}

	private static void f2() {
//		레거시에서 주로 사용, DB에 있는 date타입과 연동할 때
		System.out.println("java.sql");
		java.sql.Date day = new java.sql.Date(new Date().getTime());
		System.out.println(day);
	}

	private static void f3() {
		System.out.println("Calendar");
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE));
	}

	private static void f4() {
//		LocalDate, LocalDateTime
		System.out.println("LocalDate");
		LocalDate ld = LocalDate.now();
		System.out.println(ld);
		LocalDate ld2 = LocalDate.of(1999, 3, 23);
		System.out.println(ld2);
		System.out.println("LocalDateTime");
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);

		System.out.println("Timestamp");
		Timestamp t = Timestamp.valueOf(ldt);
		System.out.println(t);
		Timestamp t2 = Timestamp.valueOf("2026-12-25 12:00:00");
		System.out.println(t2);
	}

	private static void check() {
		Date date = java.sql.Date.valueOf("2026-12-25");
		System.out.println(date);
	}

	private static void f5() {
		String[] patterns = { "0", // 정수
				"0.0", // 소수 1자리 강제
				"#.#", // 소수 1자리 (없으면 생략)
				"0000.0000", // 자릿수 0으로 채움
				"#,###", // 천단위 구분
				"#,###.##", // 천단위 + 소수
				"₩#,###", // 원화 기호
				"#.##E0", // 지수 표현
				"0.00%", // 퍼센트
		};

		Double money = 1234567.816;
		for (String pattern : patterns) {
			DecimalFormat df = new DecimalFormat(pattern);
			System.out.println(pattern + "패턴 사용");
			System.out.println(df.format(money));
		}
	}

	private static void f6() {
		Date date = new Date();
		String[] patterns = {
				"yyyy-MM-dd",
				"yyyy/MM/dd",
				"yyyy년 MM월 dd일",
				"yy.MM.dd",
				"HH:mm:ss",
				"hh:mm:ss a",
				"yyyy-MM-dd HH:mm:ss",
				"yyyy년 MM월 dd일 E요일",
				"yyyy-MM-dd HH:mm:ss.SSS",
				"yyyy-MM-dd'T'HH:mm:ss"// ISO 8601
		};
		for (String pattern : patterns) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			String stringDate = sdf.format(date);
			System.out.println(pattern + " 패턴 적용");
			System.out.println(stringDate);
		}
	}
```

## 정규 표현식

```java
	private static void f7() {
		String phoneNumber = "010-1234-5678";
		String regExp = "[01]{3}-\\d{3,4}-[0-9]{4}";

		boolean result = Pattern.matches(regExp, phoneNumber);
		System.out.println(phoneNumber + "는 조건`" + regExp + "`에 " + result);
	}

	private static void f8() {
		String text = "고객센터010-1234-1234 또는 02-5678-5678 팩스는 031-9000-9111";
		String regExp = "(\\d{2,3})-(\\d{3,4})-(\\d{4})";

		Pattern pattern = Pattern.compile(regExp);
		Matcher matcher = pattern.matcher(text);
		System.out.println("----------");
		while (matcher.find()) {
			System.out.println(matcher.group());
			System.out.println("지역/통신사: " + matcher.group(1));
			System.out.println("중간번호: " + matcher.group(2));
			System.out.println("끝번호: " + matcher.group(3));
			System.out.println("----------");
		}
	}
```

---

## 리플렉션

특정 파일의 메타 정보를 프로그램에 가져오는 것
해당 파일의 경로 가져오기, 파일 읽기, 쓰기 ...

---

## 어노테이션

코드에서 @으로 작성되는 요소. 클래스 또는 인터페이스를 컴파일하거나 실행할 때 어떻게 처리해야 할 것인지를 알려주는 설정 정보
① 컴파일 시 사용하는 정보 전달
② 빌드 툴이 코드를 자동으로 생성할 때 사용하는 정보 전달
③ 실행 시 특정 기능을 처리할 때 사용하는 정보 전달

선언:

```java
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PrintAnnotation {
	String value() default "-";
	int number() default 7;
}
```

사용:

```java
public class PrintAnnotationExample {
	public static void main(String[] args) throws Exception {
		Method[] declaredMethods = PrintService.class.getDeclaredMethods();
		for (Method method : declaredMethods) {
			// PrintAnnotation 얻기
			PrintAnnotation printAnnotation = method.getAnnotation(PrintAnnotation.class);
			System.out.println(printAnnotation.number());
			System.out.println(printAnnotation.value());
			// 설정 정보를 이용해서 선 출력
			printLine(printAnnotation);
			// 메소드 호출
			method.invoke(new PrintService());
			// 설정 정보를 이용해서 선 출력
			printLine(printAnnotation);
		}
	}

	public static void printLine(PrintAnnotation printAnnotation) {
		if (printAnnotation == null) {
			return;
		}
		// number 속성값 얻기
		int number = printAnnotation.number();
		for (int i = 0; i < number; i++) {
			// value 속성값 얻기
			String value = printAnnotation.value();
			System.out.print(value);
		}
		System.out.println();
	}
}
```
