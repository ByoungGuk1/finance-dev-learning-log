package com.shinhan.day03;

import java.util.Scanner;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 11. 오후 2:37:09 설명 : BoardController
 */
public class BoardController {
	private static Scanner sc = new Scanner(System.in);
	private static BoardService boardService = new BoardService();

	public static void main(String[] args) {
		boolean isStop = false;

		while (!isStop) {
			BoardView.menuDisplay();
			int job = sc.nextInt();
			sc.nextLine();
			switch (job) {
			case 1 -> {
				f_selectAll();
			}
			case 2 -> {
				f_selectOne();
			}
			case 3 -> {
				f_insert();
			}
			case 4 -> {
				f_update();
			}
			case 5 -> {
				f_delete();
			}
			case 0 -> {
				isStop = true;
			}
			default -> {
				System.err.println("다시 입력해주세요.");
			}
			}
		}
		System.out.println("정상 종료");
	}

	private static void f_delete() {
		System.out.print("삭제할 글 번호 > ");
		int id = sc.nextInt();
		sc.nextLine();
		int result = boardService.deleteService(id);
		BoardView.print("삭제 완료", result);
	}

	private static void f_update() {
		System.out.print("수정할 글 번호 > ");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.print("title > ");
		String title = sc.nextLine();
		System.out.print("contants > ");
		String contants = sc.nextLine();
		System.out.print("writer > ");
		String writer = sc.nextLine();

		BoardDTO board = BoardDTO.builder().bno(id).title(title).contants(contants).writer(writer).build();
		int result = boardService.updateService(board);
		BoardView.print("입력완료", result);
	}

	private static void f_insert() {
		System.out.print("title > ");
		String title = sc.nextLine();
		System.out.print("contants > ");
		String contants = sc.nextLine();
		System.out.print("writer > ");
		String writer = sc.nextLine();

		BoardDTO board = BoardDTO.builder().title(title).contants(contants).writer(writer).build();
		int result = boardService.insertService(board);
		BoardView.print("입력완료", result);
	}

	private static void f_selectAll() {
		BoardView.print(boardService.selectAllService());
	}

	private static void f_selectOne() {
		System.out.print("조회할 글 번호 > ");
		int id = sc.nextInt();
		sc.nextLine();
		BoardDTO board = boardService.selectOneService(id);
		BoardView.print(board);
	}
}
