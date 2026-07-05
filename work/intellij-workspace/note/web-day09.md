# 웹 서버 타입 변화

cgi > thread

# connection pool

미리 연결된 connection 객체를 통해 데이터 베이스에 접근하여 속도를 향상

# Spring Boot

SpringBoot = Spring Framework + Tomcat – 복잡한 설정

## DI (Dependency Injection)

의존 관게를 끊고 Spring Container가 Injection(주입)함 => 제어의 역전

```xml
<bean id="" class="" />
```

```java
@Component
// 클래스 level : @Controller, @Service, @Repository
// method level : @Configuration 안에 @Bean
```

1. Field:

```java
@Autowired
```

2. Constructor :

```java
Lombok => @requiredConstructor + final field
```

주입할 클래스가 여러개인 경우  
ex) `serviceInterface`, `serviceImpl1`, `serviceImpl2`

구분을 위해 생성자의 매개변수 앞에 `@Qualifier("serviceImpl1");`

예시:

```java
public controller(@Qualifier("serviceImpl1") ServiceInterface service){
  this.service = service;
}
```

## IOC

제어의 역전

## AOP
