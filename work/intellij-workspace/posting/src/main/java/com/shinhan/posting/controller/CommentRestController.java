package com.shinhan.posting.controller;

import com.shinhan.posting.domain.request.CommentRequestDTO;
import com.shinhan.posting.domain.response.CommentResponseDTO;
import com.shinhan.posting.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/post/{postId}/comments")
@RequiredArgsConstructor
public class CommentRestController {
  private final CommentService commentService;

  @PostMapping
  public ResponseEntity<CommentResponseDTO> createComment(@PathVariable Long postId,
                                                           @RequestBody CommentRequestDTO request) {
    CommentResponseDTO created = commentService.createComment(postId, request);
    return ResponseEntity.created(URI.create("/api/post/" + postId + "/comments/" + created.getId())).body(created);
  }

  @PutMapping("/{commentId}")
  public ResponseEntity<CommentResponseDTO> updateComment(@PathVariable Long postId,
                                                           @PathVariable Long commentId,
                                                           @RequestBody CommentRequestDTO request) {
    return ResponseEntity.ok(commentService.updateComment(postId, commentId, request));
  }

  @DeleteMapping("/{commentId}")
  public ResponseEntity<Void> deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
    commentService.deleteComment(postId, commentId);
    return ResponseEntity.noContent().build();
  }
}
