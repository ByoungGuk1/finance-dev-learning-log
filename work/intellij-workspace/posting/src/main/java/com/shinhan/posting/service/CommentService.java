package com.shinhan.posting.service;

import com.shinhan.posting.domain.request.CommentRequestDTO;
import com.shinhan.posting.domain.response.CommentResponseDTO;

public interface CommentService {
  CommentResponseDTO createComment(Long postId, CommentRequestDTO request);
  CommentResponseDTO updateComment(Long postId, Long commentId, CommentRequestDTO request);
  void deleteComment(Long postId, Long commentId);
}
