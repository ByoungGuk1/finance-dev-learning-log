package com.shinhan.bananaapp.controller;


import com.shinhan.bananaapp.annotation.LoginRequired;
import com.shinhan.bananaapp.di2.EmpDTO;
import com.shinhan.bananaapp.dto.AccountDTO;
import com.shinhan.bananaapp.service.AccountService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


//사용자요청-->Controller-->Service-->Repository--->DB
//        <---응답(template/html파일
//Thymeleaf는 서버 사이드 템플릿 엔진으로, HTML 파일을 브라우저에서 그대로 열어도 깨지지 않는다는 특징이 있습니다. (Natural Template)
@Controller
@RequestMapping(("/account"))
@RequiredArgsConstructor
public class AccountController {

    // @Qualifier("shinhanAccount")
    final AccountService accService;
    private final AccountService accountService;

    @PostMapping("/update.do")
    public String f_update(@ModelAttribute AccountDTO account, RedirectAttributes attr) {
        boolean result = accountService.updateAccount(account);
        attr.addFlashAttribute("msg", result + "건 수정되었습니다.");
        return "redirect:list.do"; //재요청:browser로 내려가서 다시 조회
    }

    @LoginRequired(role = "MANAGER")
    @PostMapping("/insert.do")
    public String f_insert(@ModelAttribute AccountDTO account, RedirectAttributes attr) {
        boolean result = accountService.insertAccount(account);
        attr.addFlashAttribute("msg", result + "건 입력되었습니다.");
        return "redirect:list.do"; //재요청:browser로 내려가서 다시 조회
    }

    @LoginRequired(role = "MANAGER")
    @GetMapping("/insert.do")
    public String insertGet(Model model) {
        model.addAttribute("account", new AccountDTO());
        return "account/insert";
    }

    @GetMapping("/detail.do")
    public String f_detail(@RequestParam("id") Long id, Model model, HttpServletResponse hsr) {
        model.addAttribute("account", accService.selectById(id));
        setCookie("lastViewAccount", String.valueOf(id), hsr);
        return "account/detail";
    }

    @GetMapping("/{accId}")
    public String f_detail2(@PathVariable("accId") Long id, Model model, HttpServletResponse hsr) {
        model.addAttribute("account", accService.selectById(id));
        setCookie("lastViewAccount", String.valueOf(id), hsr);
        return "account/detail";
    }

    @GetMapping("/list.do")
    public String f_selectAll(Model model, @CookieValue(value = "lastViewAccount", defaultValue = "") String cookie1, @CookieValue(value = "myname", defaultValue = "이름이 없어요") String cookie2, HttpServletResponse hsr) {
        model.addAttribute("acclist", accService.selectAllAccounts());
        model.addAttribute("lastViewAccount", cookie1);
        model.addAttribute("myname", cookie2);
        deleteCookie("lastViewAccount", hsr);
        deleteCookie("myname", hsr);
        return "account/list";
    }

    @GetMapping("/thtest.do")
    public String retrieve(Model model) {
        //model : controller와 HTML간의 공유공간
        model.addAttribute("myname", "jin");
        model.addAttribute("emp", new EmpDTO(1, "병국", 1000L));
        model.addAttribute("comment1", "<h1>성실!!!!</h1>");
        model.addAttribute("comment2", "<script>alert('배고파');</script>");
        return "account/thymeleaf_basic";   //templates/account/list.html로 forward
    }

    private void setCookie(String name, String value, HttpServletResponse response) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(60 * 60 * 2);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        response.addCookie(cookie);
    }

    private void deleteCookie(String name, HttpServletResponse response) {
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
