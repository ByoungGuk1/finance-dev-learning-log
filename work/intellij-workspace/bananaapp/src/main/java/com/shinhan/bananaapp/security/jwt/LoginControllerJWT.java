package com.shinhan.bananaapp.security.jwt;

import com.shinhan.bananaapp.entity3.MemberEntity;
import com.shinhan.bananaapp.security.redis.AuthServiceLoginRedis;
import com.shinhan.bananaapp.security.redis.RefreshTokenServiceRedis;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class LoginControllerJWT {

  //Redis 저장
  private final RefreshTokenServiceRedis refreshTokenService;
  private final AuthServiceLoginRedis authServiceLogin;
  //  private final RefreshTokenService refreshTokenService;
  //  private final AuthServiceLogin authServiceLogin;
  // RDB 저장
  private final ModelMapper modelMapper;
  @Value("${jwt.refresh_expiration_day}")
  private int refreshExpirationDay;

  // 로그인 → Access Token + Refresh Token 발급
  @PostMapping("/login")
  public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest request) {
    MemberEntity member = modelMapper.map(request, MemberEntity.class);
    Map<String, TokenResponse> tokenResponseMap = authServiceLogin.login(member);
    ResponseCookie cookie = ResponseCookie.from("refreshToken", tokenResponseMap.get("refreshToken").getAccessToken())
        .httpOnly(true)
        .secure(false)
        .path("/")
        .maxAge(refreshExpirationDay * 24L * 60L * 60L)
        .build();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(tokenResponseMap.get("accessToken"));
  }

  // Refresh Token으로 Access Token 재발급
  @PostMapping("/refresh")
  public ResponseEntity<?> refresh(@CookieValue("refreshToken") String refreshToken) {
    Map<String, TokenResponse> data = refreshTokenService.reissueAccessToken(refreshToken);
    ResponseCookie cookie = ResponseCookie.from("refreshToken", data.get("refreshToken").getAccessToken())
        .httpOnly(true)
        .secure(false)
        .path("/")
        .maxAge(refreshExpirationDay * 24L * 60L * 60L)
        .build();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(data.get("accessToken"));
  }

  // 현재 로그인 사용자 정보 확인
  @GetMapping("/me")
  public ResponseEntity<?> me(Authentication authentication) {
    return ResponseEntity.ok(Map.of(
        "mid", authentication.getName(),
        "role", authentication.getAuthorities()
    ));
  }

  // 로그아웃
  @PostMapping("/logout")
  public String logout(Principal principal) {
    authServiceLogin.logout(principal.getName());
    return "OK";
  }
}
