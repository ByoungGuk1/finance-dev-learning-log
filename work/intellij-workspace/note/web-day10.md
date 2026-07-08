# 다른 패키지의 파일을 스캔하는 방법

```java
@ComponentScan(basePackages = {"com.shinhan.bananaapp", "net.firstzone.other"})
//main에 추가
```

```java
package com.shinhan.bananaapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.shinhan.bananaapp", "net.firstzone.other"})
public class BananaappApplication {
    public static void main(String[] args) {
        SpringApplication.run(BananaappApplication.class, args);
    }
}
```
