package com.shinhan.bananaapp.service.jpa;

import com.shinhan.bananaapp.dto.jpa.BoardDTO;
import com.shinhan.bananaapp.entity1.BoardEntity2;
import com.shinhan.bananaapp.repository.jpa.BoardRepository2;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Board2ServiceImpl implements Board2Service {
  private final ModelMapper modelMapper;
  private final BoardRepository2 boardRepository;

  public List<BoardDTO> selectAllBoards(int pageNumber) {
    List<BoardEntity2> entityList = boardRepository.findAll(PageRequest.of(pageNumber, 5, Sort.Direction.ASC, "bno")).getContent();
    List<BoardDTO> DTOList = entityList.stream().map(entity -> modelMapper.map(entity, BoardDTO.class)).toList();
    return DTOList.stream().peek(dto -> dto.setComment(dto.getBno() + "번 게시글, 타입은 DTO 입니다.")).toList();
  }
}
