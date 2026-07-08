package com.shinhan.bananaapp.controller;


import com.shinhan.bananaapp.di2.EmpDTO;
import com.shinhan.bananaapp.dto.AccountDTO;
import com.shinhan.bananaapp.service.AccountService;
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

    @PostMapping("/insert.do")
    public String f_insert(@ModelAttribute AccountDTO account, RedirectAttributes attr) {
        boolean result = accountService.insertAccount(account);
        attr.addFlashAttribute("msg", result + "건 입력되었습니다.");
        return "redirect:list.do"; //재요청:browser로 내려가서 다시 조회
    }

    @GetMapping("/insert.do")
    public String insertGet(Model model) {
        model.addAttribute("account", new AccountDTO());
        return "account/insert";
    }

    @GetMapping("/detail.do")
    public String f_detail(@RequestParam("id") Long id, Model model) {
        model.addAttribute("account", accService.selectById(id));
        return "account/detail";
    }

    @GetMapping("/{accId}")
    public String f_detail2(@PathVariable("accId") Long id, Model model) {
        model.addAttribute("account", accService.selectById(id));
        return "account/detail";
    }


    @GetMapping("/list.do")
    public String f_selectAll(Model model) {
        model.addAttribute("acclist", accService.selectAllAccounts());
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

}
