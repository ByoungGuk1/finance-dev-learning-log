package com.shinhan.bananaapp.dto;

import lombok.*;

import java.time.LocalDate;

// AccountDTO.java — 신한은행 계좌 DTO
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountDTO {
    private Long id;    // PK
    private String accountNo;   // 계좌번호
    private String ownerName;   // 예금주
    private Long balance;       // 잔액
    private String accountType; // SAVINGS / CHECKING
    private LocalDate createAt; // 생성일

    public AccountDTO(String accountNo, String ownerName, Long balance, String accountType) {
        this.accountNo = accountNo;
        this.ownerName = ownerName;
        this.balance = balance;
        this.accountType = accountType;
    }
}
