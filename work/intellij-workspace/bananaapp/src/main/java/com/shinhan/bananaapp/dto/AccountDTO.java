package com.shinhan.bananaapp.dto;

import lombok.*;

// AccountDTO.java — 신한은행 계좌 DTO
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountDTO {
    private String accountNo;   // 계좌번호
    private String ownerName;   // 예금주
    private Long balance;       // 잔액
    private String accountType; // SAVINGS / CHECKING
}
