package com.shinhan.bananaapp.di;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

//@Component 는 나의 소스이므로 가능, 다른 라이브러리의 경우 xml 방식 또는 configuration 방식 사용
@Repository
public class ShinhanReposiotry {
    // 1. 필드를 통해 주입
/*
    @Autowired
    private ShinhanDTO dto;
 */
    private final ShinhanDTO dto;

    // 2. 생성자를 통해 의존성 주입
    public ShinhanReposiotry(@Qualifier("shin2") ShinhanDTO dto) {
        this.dto = dto;
        System.out.println("ShinhanReposiotry constructor");
        System.out.println("dto = " + dto);
    }

    public String f1() {
        System.out.println("ShinhanReposiotry f1");
        return dto.toString();
    }
}
