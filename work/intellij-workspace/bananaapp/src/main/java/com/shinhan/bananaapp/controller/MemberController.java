package com.shinhan.bananaapp.controller;

import com.shinhan.bananaapp.dto.MemberDTO;
import com.shinhan.bananaapp.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
//@Slf4j
public class MemberController {
  private final MemberService memberService;

  @GetMapping("/login")
  public String login() {
    return "auth/login";
  }

  @PostMapping("/login")
  public String login(@ModelAttribute MemberDTO memberDTO, Model model, RedirectAttributes attr, HttpSession session, @RequestParam(name = "redirectURL", defaultValue = "/") String uri) {
//        log.info("inputMember: {}", memberDTO.toString());
    MemberDTO foundMember = memberService.login(memberDTO);
//        log.info("foundMember: {}", foundMember);
    model.addAttribute("member", foundMember);
    if (foundMember == null) {
      attr.addFlashAttribute("error", "아이디 또는 비밀번호가 일치하지 않습니다.");
      return "redirect:/auth/login";
    }
    session.setAttribute("loginMember", foundMember);
    session.setMaxInactiveInterval(30 * 60); // 30분 동안 미사용시 만료
    return "redirect:" + uri;
  }

  @GetMapping("/my-page")
  public String myPage(HttpSession session, RedirectAttributes attr, Model model, @SessionAttribute(name = "loginMember") MemberDTO member2) {
    MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginMember");
        /*
        // 매 요청마다 검사 => filter or interceptor 사용하기
        if (memberDTO == null) {
            attr.addFlashAttribute("error", "로그인이 필요합니다.");
            return "redirect:/auth/login";
        }
        */
    model.addAttribute("member", memberDTO);
    model.addAttribute("member2", member2);
    return "auth/mypage";
  }

  @PostMapping("/logout")
  public String logout(HttpSession session) {
//        session.removeAttribute("loginMember"); //세션의 해당 값 제거
    session.invalidate(); // 세션의 전체 정보 모두 삭제
    return "redirect:/";
  }
}
