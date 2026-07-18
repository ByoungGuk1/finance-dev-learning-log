package com.shinhan.posting.repository;

import com.shinhan.posting.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
  Optional<CommentEntity> findByIdAndPostId(Long id, Long postId);
}
