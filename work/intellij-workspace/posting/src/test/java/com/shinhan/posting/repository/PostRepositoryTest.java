package com.shinhan.posting.repository;

import com.shinhan.posting.entity.CommentEntity;
import com.shinhan.posting.entity.PostEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@SpringBootTest
class PostRepositoryTest {
  @Autowired
  private PostRepository postRepo;
  @Autowired
  private CommentRepository commentRepo;

  @Test
  void insertData(){
    IntStream.rangeClosed(1,10).forEach(i -> {
      PostEntity postEntity = PostEntity.builder().title("제목"+i).content("내용"+i).writer("writer"+i).build();
      postRepo.save(postEntity);
      IntStream.rangeClosed(1,10).forEach(j -> {
        List<CommentEntity> comments = new ArrayList<>();
        IntStream.rangeClosed(1,10).forEach(k -> {
          CommentEntity comment = CommentEntity.builder().content("댓글 내용").writer("작성자"+k).post(postEntity).build();
          comments.add(comment);
        });
        postEntity.setCommentList(comments);
        postRepo.save(postEntity);
      });
    });
  }
}