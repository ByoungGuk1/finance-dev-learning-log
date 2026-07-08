package com.shinhan.bananaapp.filter;

import com.shinhan.bananaapp.dto.MemberDTO;
import com.shinhan.bananaapp.service.MemberService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginCheckInterceptor implements HandlerInterceptor {
    private final MemberService memberService;  // DB 조회 가능
    //    private final BlackListRepository blackListRepo; // 차단 목록 조회 가능

    @Override
    public boolean preHandle(HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler) throws Exception {
        MemberDTO memberDTO = memberService.login(MemberDTO.builder().email("test@test.com").password("1234").build());
        log.info("찾은 멤버(Interceptor): {}", memberDTO);

        String uri = request.getRequestURI();
        log.debug("[Interceptor] 요청 URI: {}", uri);

        HttpSession session = request.getSession(false);
        boolean isLoggedIn = (session != null && session.getAttribute("loginMember") != null);

        if (!isLoggedIn) {
            log.info("[Interceptor] 미로그인 접근 차단: {}", uri);
            response.sendRedirect("/auth/login?redirectURL=" + uri);
            return false;  // 요청 차단
        }
        return true;  // 진행
    }
}
