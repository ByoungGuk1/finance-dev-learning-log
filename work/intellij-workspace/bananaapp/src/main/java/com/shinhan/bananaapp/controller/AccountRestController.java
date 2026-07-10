package com.shinhan.bananaapp.controller;


import com.shinhan.bananaapp.dto.AccountDTO;
import com.shinhan.bananaapp.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//요청주소 연결
//@RequestMapping, @DeleteMapping, @PutMapping, @PostMapping, @GetMapping

//@Controller  //요청받아서 처리하고 응답은  /templates/?????.html
@RestController //요청받고 처리하고 응답은 Data를 JSON변경에서(Jackson Library) ResponseBody로 보낸다.
@RequestMapping("/api/account") //요청주는 class level에 공통적인 주소를 작성
public class AccountRestController {

    private final AccountService accService;

    public AccountRestController(@Qualifier("shinhanAccount") AccountService accService) {
        this.accService = accService;
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        boolean result = accService.deleteAccount(id);
        return result ? "삭제성공" : "삭제실패";
    }

    @PostMapping
    public String insert(@RequestBody AccountDTO acc) {
        boolean result = accService.insertAccount(acc);
        return result ? "입력성공" : "입력실패(이미존재하는지 확인)";
    }

    @PutMapping
    public String update(@RequestBody AccountDTO acc) {
        boolean result = accService.updateAccount(acc);
        return result ? "수정성공" : "수정실패";
    }


    //RestFul방식 , URL에 data가 들어옴
    @GetMapping("/{id}")
    public AccountDTO selectById(@PathVariable("id") Long accId) {
        AccountDTO acc = accService.selectById(accId);
        return acc;
    }

    //JSP/Servlet==>SpringFramework===>Springboot
    //RestFul방식은 주소가 /로 끝나면 안된다.
    @RequestMapping(value = "", method = RequestMethod.GET) //각각의 개별요청은 Method level작성
    public List<AccountDTO> selectAll(HttpSession session) {
        return accService.selectAllAccounts();
    }
}
