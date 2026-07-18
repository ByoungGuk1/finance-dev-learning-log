package com.shinhan.posting.service;

import com.shinhan.posting.dto.request.PostRequestDTO;
import com.shinhan.posting.dto.response.PostResponseDTO;

import java.util.List;

public interface PostService {
  List<PostResponseDTO> getAllPostList();
  PostResponseDTO getPost(Long postId);
  PostResponseDTO createPost(PostRequestDTO request);
  PostResponseDTO updatePost(Long postId, PostRequestDTO request);
  void deletePost(Long postId);
}
