package com.shinhan.bananaapp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "email")
@Builder
public class MemberDTO {
    private String email;
    private String password;
    private String name;
    private String role; // ADMIN, MANAGER, USER
}
