package com.shinhan.posting.service;

import com.shinhan.posting.domain.request.CommentRequestDTO;
import com.shinhan.posting.domain.response.CommentResponseDTO;
import com.shinhan.posting.entity.CommentEntity;
import com.shinhan.posting.entity.PostEntity;
import com.shinhan.posting.exception.ResourceNotFoundException;
import com.shinhan.posting.repository.CommentRepository;
import com.shinhan.posting.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
  private final PostRepository postRepository;
  private final CommentRepository commentRepository;

  @Override
  @Transactional
  public CommentResponseDTO createComment(Long postId, CommentRequestDTO request) {
    validateForCreate(request);
    PostEntity post = postRepository.findById(postId)
        .orElseThrow(() -> new ResourceNotFoundException("게시글을 찾을 수 없습니다. id=" + postId));
    CommentEntity comment = CommentEntity.builder()
        .content(request.getContent().trim())
        .writer(request.getWriter().trim())
        .build();
    post.addComment(comment);
    return toResponse(commentRepository.saveAndFlush(comment));
  }

  @Override
  @Transactional
  public CommentResponseDTO updateComment(Long postId, Long commentId, CommentRequestDTO request) {
    validateContent(request);
    CommentEntity comment = findComment(postId, commentId);
    comment.setContent(request.getContent().trim());
    return toResponse(comment);
  }

  @Override
  @Transactional
  public void deleteComment(Long postId, Long commentId) {
    commentRepository.delete(findComment(postId, commentId));
  }

  private CommentEntity findComment(Long postId, Long commentId) {
    return commentRepository.findByIdAndPostId(commentId, postId)
        .orElseThrow(() -> new ResourceNotFoundException("댓글을 찾을 수 없습니다. id=" + commentId));
  }

  private void validateForCreate(CommentRequestDTO request) {
    if (request == null || request.getContent() == null || request.getContent().isBlank()
        || request.getWriter() == null || request.getWriter().isBlank()) {
      throw new IllegalArgumentException("댓글 내용과 작성자를 모두 입력해 주세요.");
    }
  }

  private void validateContent(CommentRequestDTO request) {
    if (request == null || request.getContent() == null || request.getContent().isBlank()) {
      throw new IllegalArgumentException("댓글 내용을 입력해 주세요.");
    }
  }

  private CommentResponseDTO toResponse(CommentEntity comment) {
    return new CommentResponseDTO(comment.getId(), comment.getContent(), comment.getWriter(),
        comment.getCreatedDate(), comment.getUpdatedDate());
  }
}
