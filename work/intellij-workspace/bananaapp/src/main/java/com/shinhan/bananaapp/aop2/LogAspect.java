package com.shinhan.bananaapp.aop2;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect       // AOP 클래스 선언 = @Pointcut + 시점 + Advice(보조업무)
@Component    // Spring Bean 등록
public class LogAspect {
    // ── Pointcut 정의 ──────────────────────────────────────────────
    // execution() : service 패키지의 모든 클래스·모든 메서드
    @Pointcut("execution(* com.shinhan.bananaapp.di.*.*(..))")
//    @Pointcut("execution(* *(..))")
    public void allMethods() {
    }

    // within() : CalculatorImpl 클래스 안의 모든 메서드
    @Pointcut("within(com.shinhan.bananaapp.aop2.CalculatorImpl)")
    public void calculatorOnly() {
    }

    // ── @Before ────────────────────────────────────────────────────
    @Before("allMethods()")
    public void beforeLog(JoinPoint jp) {
//        System.out.println("[Before] 메서드 : " + jp.getSignature().getName());
//        System.out.println("[Before] 파라미터: " + Arrays.toString(jp.getArgs()));
    }

    // ── @AfterReturning ────────────────────────────────────────────
    @AfterReturning(pointcut = "allMethods()", returning = "result")
    public void afterReturningLog(JoinPoint jp, Object result) {
//        System.out.println("[AfterReturning] 반환값 : " + result);
    }

    // ── @AfterThrowing ─────────────────────────────────────────────
    @AfterThrowing(pointcut = "allMethods()", throwing = "ex")
    public void afterThrowingLog(JoinPoint jp, Exception ex) {
//        System.out.println("[AfterThrowing] 예외 : " + ex.getMessage());
    }

    // ── @Around (within 사용) ──────────────────────────────────────
    @Around("calculatorOnly()")
    public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("[Around-Before] 실행 시작");
        Object result = pjp.proceed();  // 실제 메서드 실행
        long end = System.currentTimeMillis();
        System.out.println("[Around-After] 실행 종료");
        System.out.println("[Around-After] 실행 시간 : " + (end - start) + "ms");
        return result;
    }
}
