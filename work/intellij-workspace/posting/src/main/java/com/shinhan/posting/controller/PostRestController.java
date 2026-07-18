package com.shinhan.posting.controller;

import com.shinhan.posting.domain.request.PostRequestDTO;
import com.shinhan.posting.domain.response.PostResponseDTO;
import com.shinhan.posting.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.net.URI;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostRestController {
  private final PostService postService;

  @GetMapping
  public ResponseEntity<List<PostResponseDTO>> getAllPostList() {
    return ResponseEntity.ok(postService.getAllPostList());
  }

  @GetMapping("/{postId}")
  public ResponseEntity<PostResponseDTO> getPost(@PathVariable Long postId) {
    return ResponseEntity.ok(postService.getPost(postId));
  }

  @PostMapping
  public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostRequestDTO request) {
    PostResponseDTO created = postService.createPost(request);
    return ResponseEntity.created(URI.create("/api/post/" + created.getId())).body(created);
  }

  @PutMapping("/{postId}")
  public ResponseEntity<PostResponseDTO> updatePost(@PathVariable Long postId, @RequestBody PostRequestDTO request) {
    return ResponseEntity.ok(postService.updatePost(postId, request));
  }

  @DeleteMapping("/{postId}")
  public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
    postService.deletePost(postId);
    return ResponseEntity.noContent().build();
  }
}
