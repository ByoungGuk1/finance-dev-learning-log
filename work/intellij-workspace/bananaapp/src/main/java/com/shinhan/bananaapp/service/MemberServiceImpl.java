package com.shinhan.bananaapp.service;

import com.shinhan.bananaapp.dto.MemberDTO;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    public MemberDTO login(MemberDTO memberDTO) {
        MemberDTO foundMember = MemberDTO.builder().email(memberDTO.getEmail()).password(memberDTO.getPassword()).name("사용자 명").role("MANAGER").build();
        return foundMember;
    }
}
