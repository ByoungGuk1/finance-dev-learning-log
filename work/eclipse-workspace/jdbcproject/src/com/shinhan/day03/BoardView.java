package com.shinhan.day03;

import java.util.List;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 11. 오후 2:29:55 설명 : BoardView
 */
// view : 나중에 웹으로 전환
public class BoardView {
	public static void menuDisplay() {
		System.out.println("--------------");
		System.out.println("1. 조회 | 2. 상세조회 | 3. 입력 | 4. 수정 | 5. 삭제 | 0. 종료");
		System.out.println("--------------");
		System.out.print("작업 선택 >");
	}

	public static void print(String message, int resultCount) {
		System.out.println("[알림] " + resultCount + "건 " + message);
	}

	public static void print(BoardDTO board) {
		System.out.println("=======한건 출력========");
		if (board == null) {
			System.out.println("정보가 없습니다.");
		}
		System.out.println("번호 : " + board.getBno());
		System.out.println("제목 : " + board.getTitle());
		System.out.println("내용 : " + board.getContants());
		System.out.println("작성자 : " + board.getWriter());
		System.out.println("등록일 : " + board.getRegdate());
		System.out.println("수정일 : " + board.getUpdatedate());
	}

	public static void print(List<BoardDTO> blist) {
		System.out.println("====== 글 목록 ======");
		blist.stream().forEach(System.out::println);
	}
}
