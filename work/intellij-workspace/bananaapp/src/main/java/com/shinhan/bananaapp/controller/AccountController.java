package com.shinhan.bananaapp.controller;


import com.shinhan.bananaapp.annotation.LoginRequired;
import com.shinhan.bananaapp.di2.EmpDTO;
import com.shinhan.bananaapp.dto.AccountDTO;
import com.shinhan.bananaapp.dto.AccountSearchDTO;
import com.shinhan.bananaapp.dto.AttachmentDTO;
import com.shinhan.bananaapp.service.AccountServiceImplUsingMyBatis;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;


//사용자요청-->Controller-->Service-->Repository--->DB
//        <---응답(template/html파일
//Thymeleaf는 서버 사이드 템플릿 엔진으로, HTML 파일을 브라우저에서 그대로 열어도 깨지지 않는다는 특징이 있습니다. (Natural Template)
@Controller
@RequestMapping(("/account"))
public class AccountController {

    private final AccountServiceImplUsingMyBatis accountService;

    public AccountController(@Qualifier("accountServiceImplUsingMyBatis") AccountServiceImplUsingMyBatis accountService) {
        this.accountService = accountService;
    }

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
//        model.addAttribute("account", accountService.selectById(id));
//        model.addAttribute("account", accountService.findAllWithAttachmentFlat(id));
        model.addAttribute("account", accountService.findByIdWithAttachment(id));

        setCookie("lastViewAccount", String.valueOf(id), hsr);
        return "account/detail";
    }

    @GetMapping("/{accId}")
    public String f_detail2(@PathVariable("accId") Long id, Model model, HttpServletResponse hsr) {
//        model.addAttribute("account", accountService.selectById(id));
//        model.addAttribute("account", accountService.findAllWithAttachmentFlat(id));
        model.addAttribute("account", accountService.findByIdWithAttachment(id));

        setCookie("lastViewAccount", String.valueOf(id), hsr);
        return "account/detail";
    }

    @PostMapping("/{id}/upload")
    public String upload(@PathVariable Long id, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttrs) throws IOException {
        accountService.uploadAttachment(id, file);
        redirectAttrs.addFlashAttribute("msg", "파일이 업로드되었습니다.");
        return "redirect:/account/" + id;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadAttachment(@PathVariable Long id, RedirectAttributes redirectAttrs) throws MalformedURLException {
        // DB에서 파일 정보 조회
        AttachmentDTO att = accountService.findAttachmentById(id);
        // 파일 Resource 생성
        Resource resource = new UrlResource(
                Paths.get(accountService.getUploadDir(), att.getSavedFilename()).toUri()
        );
        if (!resource.exists())
            throw new RuntimeException("파일을 찾을 수 없습니다.");
        // 한글 파일명 인코딩
        String encodedFilename = URLEncoder.encode(att.getOriginalFilename(), StandardCharsets.UTF_8).replace("+", "%20");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + encodedFilename + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @PostMapping("/attachment/delete/{attachmentId}")
    public String deleteAttachment(@PathVariable Long attachmentId, @RequestParam Long accountId, RedirectAttributes redirectAttrs) throws IOException {
        accountService.deleteAttachment(attachmentId);
        redirectAttrs.addFlashAttribute("msg", "파일이 삭제되었습니다.");
        return "redirect:/account/" + accountId;
    }

    @GetMapping("/list.do")
    public String f_selectAll(Model model, @CookieValue(value = "lastViewAccount", defaultValue = "") String cookie1, @CookieValue(value = "myname", defaultValue = "이름이 없어요") String cookie2, HttpServletResponse hsr) {
        model.addAttribute("acclist", accountService.selectAllAccounts());
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

    @PostMapping("/transfer")
    public String transfer(@RequestParam Long fromId, @RequestParam Long toId, @RequestParam Long amount) {
        Boolean result = accountService.transaction(fromId, toId, amount);
        return "redirect:list.do";
    }

    @GetMapping("/condition")
    public String condition(@ModelAttribute AccountSearchDTO accountSearchDTO, Model model, HttpSession session) {
        session.setAttribute("searchDTO", accountSearchDTO);
        List<AccountDTO> result = accountService.selectByCondition(accountSearchDTO);
        model.addAttribute("acclist", result);
        model.addAttribute("lastViewAccount", 1);
        model.addAttribute("myname", "");

        return "account/list";
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
