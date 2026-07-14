package com.shinhan.bananaapp;

import com.shinhan.bananaapp.entity1.SampleEntity;
import com.shinhan.bananaapp.repository.jpa.SampleRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// JUnit을 활용한 단위 테스트
@SpringBootTest
@Slf4j
public class SampleTest {
    @Autowired
    private SampleRepository sampleRepository;

    @Test
    void insertSample() {
//        SampleEntity sampleEntity = SampleEntity.builder()
//                .userName("gildong")
//                .name("홍길동")
//                .email("gildong@test.com")
//                .title("타이틀인데 무슨 타이틀인지")
//                .amount(BigDecimal.valueOf(1000))
//                .regDate(Timestamp.valueOf(LocalDateTime.now()))
//                .build();

        IntStream.range(0, 10).forEach(i -> {
            SampleEntity sampleEntity = SampleEntity.builder()
                    .userName("gildong" + i)
                    .name("홍길동" + i)
                    .email("gildong" + i + "@test.com")
                    .title("타이틀인데 무슨 타이틀인지" + i)
                    .amount(BigDecimal.valueOf(1000 + i))
                    .regDate(Timestamp.valueOf(LocalDateTime.now()))
                    .build();
            log.info(sampleEntity.toString());

            sampleRepository.save(sampleEntity);
        });
    }

    @Test
    void selectSampleList() {
        sampleRepository.findAll().stream().map(SampleEntity::toString).forEach(log::info);
    }

    @Test
    void countSample() {
        sampleRepository.count();
    }

    @Test
    void selectSampleById() {
        SampleEntity sampleEntity = sampleRepository.findById(1).orElse(null);
        if (sampleEntity != null) {
            log.info(sampleEntity.toString());
        }
    }

//    @Test
//    void updateSample() {
//        SampleEntity sampleEntity = sampleRepository.findById(1).
//                ifPresent(d -> {
//                    d.setAmount(BigDecimal.valueOf(Long.parseLong(d.getAmount().toString()) + 10000L));
//                    return d;
//                });
//    }

    @Test
    void work() {
        Class<?> cls = sampleRepository.getClass();
        log.info("repo 이름 :{}", cls.getName());
        Stream.of(cls.getInterfaces()).map(Class::getName).forEach(log::info);
    }

    // 쓰기 지연 : 영속성 Context에 있는 데이터를 수정, DB에는 Transaction이 종료되면 DB에 반영
    // 변경 감지 (Dirty Checking)
    @Test
    @Transactional
    @Commit
    void updateSample() {
        SampleEntity sampleEntity = sampleRepository.findById(10).orElse(null);
        if (sampleEntity == null) {
            return;
        }
        sampleEntity.setTitle("제 목 변 경");
        sampleRepository.save(sampleEntity);
    }

    @Test
    @Transactional
    void selectById() {
        SampleEntity sampleEntity1 = sampleRepository.findById(10).orElse(null);
        SampleEntity sampleEntity2 = sampleRepository.findById(10).orElse(null);
        System.out.println(sampleEntity1 == sampleEntity2);
    }
}
