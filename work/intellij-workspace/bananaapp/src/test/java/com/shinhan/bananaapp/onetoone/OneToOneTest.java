package com.shinhan.bananaapp.onetoone;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
class OneToOneTest {
  @Autowired
  private UserRepository userRepo;

  @Autowired
  private UserCellPhoneRepository1 userCellphoneRepo;

  @Autowired
  private UserRepository2 userRepo2;
  @Autowired
  private UserCellPhoneRepository2 userCellphoneRepo2;

  // 주 -> 부 접근
  @Test
  void insertDataFromUser() {
    UserCellPhoneEntity userPhone = UserCellPhoneEntity.builder().model("애플").phoneNumber("010-1234-5678").build();
    UserEntity user = UserEntity.builder().name("홍길동").cellphone(userPhone).build();
    userRepo.save(user);
  }

  @Test
  void findDataFromUser() {
    userRepo.findAll().stream().map(u -> u.toString() + "-" + u.getCellphone().toString()).forEach(log::info);
  }

  // 부 -> 주 접근
  @Test
  void insertDataFromCellphone() {
    UserEntity1 user = UserEntity1.builder().name("이순신").build();
    UserCellPhoneEntity1 phone = UserCellPhoneEntity1.builder().model("애플").phoneNumber("010-2345-6789").user(user).build();
    userCellphoneRepo.save(phone);
  }

  @Test
  @Transactional
  void findDataFromCellphone() {
    userCellphoneRepo.findAll().stream().map(p -> p.getUser().toString() + "--" + p).forEach(log::info);
  }


  // 주 -> 부(의존)
  @Test
  void insertDataFromUser1() {
    UserEntity2 user = UserEntity2.builder().name("장보고").build();
    UserCellPhoneEntity2 phone = UserCellPhoneEntity2.builder().model("삼성").phoneNumber("010-9999-8888").build();
    user.setCellphone(phone);
    phone.setUser(user);
    userRepo2.save(user);
  }
}