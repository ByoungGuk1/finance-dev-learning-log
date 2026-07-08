package com.shinhan.bananaapp.di;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShinhanService {
//    @Autowired
    private final ShinhanReposiotry repo;

    /*
    public ShinhanService(ShinhanReposiotry repo) {
        String result = repo.f1();
        System.out.println("ShinhanService constructor" + result);
    }
    */

    public String f_getDTO(){
        return repo.f1();
    }
}
