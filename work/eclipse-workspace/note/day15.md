## stream

결과가 필요 없이 반복 -> forEach
결과가 필요한 반복 -> feek

리스트를 맵 자료구조로 바꾸기

```java
		Map<String, Integer> newMap = memList.stream().collect(Collectors.toMap(Member::getName, Member::getScore));
		System.out.println(newMap);
```

다시보기:

```java
	private static void f6() {
		List<Member> manList = memList.stream().filter(m -> m.getGender().equals("남자")).peek(System.out::println)
				.toList();
		manList.stream().forEach(System.out::println);

		Map<String, Integer> newMap = memList.stream().collect(Collectors.toMap(Member::getName, Member::getScore));
		System.out.println(newMap);
	}

	private static void f7() {
		Map<String, List<Member>> groupMember = memList.stream().collect(Collectors.groupingBy(Member::getGender));

		groupMember.keySet().forEach(data -> {
			System.out.println(data);
			groupMember.get(data).stream().forEach(System.out::println);
		});
	}
```

## 데이터 입출력

java의 경우 기본 값으론 DataInputStream

objectInputStream  
implements Serializable

## 네트워크 입출력

신뢰도 tcp > udp
