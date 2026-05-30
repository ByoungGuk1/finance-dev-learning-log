package com.shinhan.day11;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 5. 29. 오후 3:16:44 설명 : PrintAnnotation
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PrintAnnotation {
	String value() default "-";
	int number() default 7;
}
