package com.shinhan.bananaapp.exception;

import com.shinhan.bananaapp.dto.AccountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exception")
public class AccountExceptionTestController {

    private final AccountExceptionTestService service;

    @GetMapping("/account/{id}")
    public ResponseEntity<?> func1(@PathVariable Long id) {
        AccountDTO result = service.throwException(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
    }
}
