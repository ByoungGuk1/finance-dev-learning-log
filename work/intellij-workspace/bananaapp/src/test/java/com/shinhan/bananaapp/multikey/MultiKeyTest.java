package com.shinhan.bananaapp.multikey;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class MultiKeyTest {
  @Autowired
  private MultiKeyRepository aRepo;

  @Autowired
  private MultiKeyBRepository bRepo;

  @Test
  void aTest() {
    MultiKeyEntity key1 = MultiKeyEntity.builder().id1(1).id2(1).name("kim").phoneNumber("010-1234-5678").build();
    MultiKeyEntity key2 = MultiKeyEntity.builder().id1(1).id2(2).name("lee").phoneNumber("010-2345-6789").build();

    aRepo.save(key1);
    aRepo.save(key2);
  }

  @Test
  void bTest() {
    MultiKeyBEntity key1 = MultiKeyBEntity.builder().id(MultiKeyB.builder().id1(1).id2(1).build()).name("kim").phoneNumber("010-1234-5678").build();
    MultiKeyBEntity key2 = MultiKeyBEntity.builder().id(MultiKeyB.builder().id1(1).id2(2).build()).name("lee").phoneNumber("010-2345-6789").build();

    bRepo.save(key1);
    bRepo.save(key2);
  }

}