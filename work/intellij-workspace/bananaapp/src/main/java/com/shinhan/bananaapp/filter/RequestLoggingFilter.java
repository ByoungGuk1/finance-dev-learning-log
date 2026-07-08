package com.shinhan.bananaapp.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
//@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String uri = request.getRequestURI();
        long start = System.currentTimeMillis();
        String method = request.getMethod();

//        //크롬 개발자도구 열려있다면 :  Chrome DevTools가 자동으로 보내는 요청 display
//        // Chrome DevTools 자동 요청 제외
//        if (uri.startsWith("/.well-known")) {
//            filterChain.doFilter(request, response);
//            return;
//        }

        log.info("[REQUEST]  {} {}", method, uri);
        filterChain.doFilter(request, response);  // 다음 필터 or 서블릿으로 전달
        long elapsed = System.currentTimeMillis() - start;
        log.info("[RESPONSE] {} {} → {} ({}ms)", method, uri, response.getStatus(), elapsed);

    }
}
