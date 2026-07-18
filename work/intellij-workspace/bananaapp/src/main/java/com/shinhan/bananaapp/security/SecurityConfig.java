package com.shinhan.bananaapp.security;

import com.shinhan.bananaapp.entity3.MemberRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
  // Spring MVC 매핑 정보를 가진 Bean 주입
  private final RequestMappingHandlerMapping requestMappingHandlerMapping;

  // 인증 없이 접근 가능한 경로 목록
  private final String[] WHITE_LIST = {
      "/security/all",
      "/auth/signup",
      "/auth/joinProc",
      "/images/**",
      "/css/**",
      "/js/**"
  };

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    // 1. 인가 설정 — URL별 접근 권한
    http.authorizeHttpRequests(auth ->
        auth
            .requestMatchers(WHITE_LIST).permitAll()
            .requestMatchers("/admin/**").hasRole(MemberRole.ADMIN.toString())
            .requestMatchers("/manager/**").hasAnyRole(MemberRole.ADMIN.toString(), MemberRole.MANAGER.toString())
            .anyRequest().authenticated()
    );

    // 2. CSRF 비활성화 (REST API + JWT 방식)
    http.csrf(csrf -> csrf.disable());

    // 3. 폼 로그인 설정
    http.formLogin(login -> login
        .loginPage("/auth/login")
        .usernameParameter("mid")        // 폼의 name 속성값
        .passwordParameter("mpassword")  // 폼의 name 속성값
        .successHandler((request, response, authentication) -> {
          response.sendRedirect("/auth/loginSuccess");
        })
        .failureHandler((request, response, exception) -> {
          request.getSession().setAttribute("loginError", "로그인 실패");
          response.sendRedirect("/auth/login");
        })
        .permitAll()
    );

    // 4. 로그아웃 설정
    http.logout(out -> out
        .logoutUrl("/auth/logout")
        .logoutSuccessUrl("/auth/login")
        .invalidateHttpSession(true)    // 세션 무효화
        .deleteCookies("JSESSIONID")   // 쿠키 삭제
    );

    // 5. 403 접근 거부 페이지
    http.exceptionHandling(handling -> handling
        .accessDeniedHandler((request, response, ex) -> {
          String uri = request.getRequestURI();
          // 실제 URL이 존재하는지 확인
          boolean urlExists = isUrlMapped(uri, request);
          if (!urlExists) {
            // URL 자체가 없음 → 404
            response.sendError(
                HttpServletResponse.SC_NOT_FOUND);
          } else {
            // URL은 있는데 권한 없음 → 403
            response.sendRedirect("/auth/accessDenied");
          }
        })
    );

    return http.build();
  }

  // URL이 Controller에 매핑되어 있는지 확인
  private boolean isUrlMapped(
      String uri, HttpServletRequest request) {
    try {
      return requestMappingHandlerMapping.getHandler(request) != null;
    } catch (Exception e) {
      return false;  // 매핑 없음 → false
    }
  }
}
