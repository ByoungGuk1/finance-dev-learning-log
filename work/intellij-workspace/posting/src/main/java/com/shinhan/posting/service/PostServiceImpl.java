package com.shinhan.posting.service;

import com.shinhan.posting.dto.request.PostRequestDTO;
import com.shinhan.posting.dto.response.CommentResponseDTO;
import com.shinhan.posting.dto.response.PostResponseDTO;
import com.shinhan.posting.entity.PostEntity;
import com.shinhan.posting.exception.ResourceNotFoundException;
import com.shinhan.posting.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
  private final PostRepository postRepository;
  @Override
  @Transactional(readOnly = true)
  public List<PostResponseDTO> getAllPostList() {
    return postRepository.findAll().stream().map(this::toResponse).toList();
  }

  @Override
  @Transactional(readOnly = true)
  public PostResponseDTO getPost(Long postId) {
    return toResponse(findPost(postId));
  }

  @Override
  @Transactional
  public PostResponseDTO createPost(PostRequestDTO request) {
    validateForCreate(request);
    PostEntity post = PostEntity.builder()
        .title(request.getTitle().trim())
        .content(request.getContent().trim())
        .writer(request.getWriter().trim())
        .build();
    return toResponse(postRepository.save(post));
  }

  @Override
  @Transactional
  public PostResponseDTO updatePost(Long postId, PostRequestDTO request) {
    validateForUpdate(request);
    PostEntity post = findPost(postId);
    post.setTitle(request.getTitle().trim());
    post.setContent(request.getContent().trim());
    return toResponse(post);
  }

  @Override
  @Transactional
  public void deletePost(Long postId) {
    postRepository.delete(findPost(postId));
  }

  private PostEntity findPost(Long postId) {
    return postRepository.findById(postId)
        .orElseThrow(() -> new ResourceNotFoundException("게시글을 찾을 수 없습니다. id=" + postId));
  }

  private void validateForCreate(PostRequestDTO request) {
    if (request == null || isBlank(request.getTitle()) || isBlank(request.getContent()) || isBlank(request.getWriter())) {
      throw new IllegalArgumentException("제목, 내용, 작성자를 모두 입력해 주세요.");
    }
  }

  private void validateForUpdate(PostRequestDTO request) {
    if (request == null || isBlank(request.getTitle()) || isBlank(request.getContent())) {
      throw new IllegalArgumentException("제목과 내용을 모두 입력해 주세요.");
    }
  }

  private boolean isBlank(String value) {
    return value == null || value.isBlank();
  }

  private PostResponseDTO toResponse(PostEntity post) {
    List<CommentResponseDTO> comments = post.getCommentList().stream()
        .map(comment -> new CommentResponseDTO(
            comment.getId(), comment.getContent(), comment.getWriter(),
            comment.getCreatedDate(), comment.getUpdatedDate()))
        .toList();
    return new PostResponseDTO(
        post.getId(), post.getTitle(), post.getContent(), post.getWriter(),
        post.getCreatedDate(), post.getUpdatedDate(), comments);
  }
}
