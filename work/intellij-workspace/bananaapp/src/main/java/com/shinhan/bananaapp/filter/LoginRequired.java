package com.shinhan.bananaapp.filter;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface LoginRequired {
  // 필요 권한 (기본값: 빈 문자열 = 로그인만 확인)
  String role() default "";
}
