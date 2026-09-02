package com.shinhan.bananaapp.security;

import com.shinhan.bananaapp.security.jwt.JwtAuthFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final CorsConfigurationSource corsConfigurationSource;

  // 인증 없이 접근 가능한 경로 목록
  private final String[] WHITE_LIST = {
      "/security/all",
      "/auth/signup",
      "/auth/joinProc",
      "/images/**",
      "/css/**",
      "/js/**",
      "/swagger-ui/**",
      "/favicon.ico",
      "/api-docs/**",
      "/swagger-resources/**",
      "/webjars/**",
      "/swagger-ui.html", "/freeboard/**"
  };

  // Spring MVC 매핑 정보를 가진 Bean 주입
  private final RequestMappingHandlerMapping requestMappingHandlerMapping;

  // ① API 체인 — JWT 인증 (Order 1, 먼저 매칭)
  @Bean
  @Order(1)
  SecurityFilterChain apiSecurity(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
    http
        .cors(cors -> cors.configurationSource(corsConfigurationSource))
        .securityMatcher("/api/**"/*, "/freeboard/**"*/)     // /api/** 경로만 이 체인 적용
        .csrf(csrf -> csrf.disable())
        .formLogin(form -> form.disable())
        .httpBasic(basic -> basic.disable())
        // JWT 필터를 폼 로그인 필터보다 먼저 등록
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(
                "/api/auth/login",
                "/api/auth/refresh",
                "/api/auth/joinProc",
                "/api/freeboard/**",
                "/swagger-ui/**",
                "/api-docs/**",
                "/swagger-resources/**",
                "/webjars/**"
            ).permitAll()
            .requestMatchers("/actuator/**").permitAll()
            .anyRequest().authenticated()
        );
    return http.build();
  }

  @Bean
  @Order(2)
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // 1. 인증, 인가 설정 — URL별 접근 권한
    //requestMatchers는 URL pattern
    http.authorizeHttpRequests(auth -> auth
        .requestMatchers(WHITE_LIST).permitAll() //무조건허용
        .requestMatchers("/admin/**").hasRole("ADMIN")
        .requestMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER")
        .anyRequest().authenticated()  //나머지는 반드시 인증되어야 자원사용가능
    );

    // 2. CSRF 비활성화 (REST API + JWT 방식)
    http.csrf(csrf -> csrf.disable());

    // 3. 폼 로그인 설정
    // ...default로 security 제공하는 페이지아닌 개발된 page로 변경
    http.formLogin(login -> login
        //post는 자동처리
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
        //security 기본제공 주소를 변경
        .logoutUrl("/auth/logout")
        .logoutSuccessUrl("/auth/login")
        .invalidateHttpSession(true)    // 세션 무효화
        .deleteCookies("JSESSIONID")   // 쿠키 삭제
    );

    // 5. 403 접근 거부 페이지
    //    404, .....
    //http.exceptionHandling(a->a.accessDeniedPage(""))
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
