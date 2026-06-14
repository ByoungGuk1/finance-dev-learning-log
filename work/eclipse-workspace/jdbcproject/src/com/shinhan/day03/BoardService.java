package com.shinhan.day03;

import java.util.List;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 11. 오후 2:19:23 설명 : BoardService
 */
// Service : 비즈니스 로직을 담당
// A 계좌에서 B 계좌로 이체
public class BoardService {
	private BoardDAO boardDAO = new BoardDAO();

	public int deleteService(int id) {
		return boardDAO.deleteBoard(id);
	}

	public int updateService(BoardDTO boardDTO) {
		return boardDAO.updateBoard(boardDTO);
	}

	public int insertService(BoardDTO boardDTO) {
		return boardDAO.insertBoard(boardDTO);
	}

	public BoardDTO selectOneService(int id) {
		return boardDAO.selectById(id);
	}

	public List<BoardDTO> selectAllService() {
		return boardDAO.selectAll();
	}
}
