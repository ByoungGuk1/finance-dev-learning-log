package com.shinhan.bananaapp.service.jpa;

import com.shinhan.bananaapp.dto.jpa.BoardDTO;

import java.util.List;

public interface Board2Service {
  /*
    전체 조회 - 1 page(5개) , bno sort : DTO 리턴
   */
  List<BoardDTO> selectAllBoards(int pageNumber);
}
