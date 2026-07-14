package com.shinhan.bananaapp;

import com.shinhan.bananaapp.entity1.BoardEntity;
import com.shinhan.bananaapp.repository.jpa.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
public class BoardTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    @Transactional
    @Commit
    void save() {
        IntStream.range(0, 10).forEach(i -> {
            BoardEntity board = BoardEntity.builder()
                    .title("title" + i)
                    .content("content" + i)
                    .writer("writer" + i)
                    .build();
            boardRepository.save(board);
        });
    }

    @Test
    @Transactional
    @Commit
    void selectAll() {
        boardRepository.findAll().stream().map(BoardEntity::toString).forEach(log::info);
    }

    @Test
    @Transactional
    @Commit
    void selectOne() {
        boardRepository.findById(1).ifPresent(d -> {
            log.info(d.toString());
        });
    }

    @Test
    @Transactional
    @Commit
    void update() {
        boardRepository.findById(1).ifPresent(d -> {
            d.setTitle("수정");
            d.setContent("흠");
            boardRepository.save(d);
        });
    }

    @Test
    @Transactional
    @Commit
    void delete() {
        boardRepository.findById(1).ifPresent(d -> {
            boardRepository.delete(d);
        });
    }
}
