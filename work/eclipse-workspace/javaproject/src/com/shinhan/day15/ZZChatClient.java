package com.shinhan.day15;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * 실습: 멀티채팅 클라이언트 목표: 서버와 동시 송수신을 위해 수신 전용 스레드를 별도로 운용하는 구조 이해 ★ 핵심 개념: 송신과 수신을
 * 왜 스레드로 분리하는가? - 메인 스레드가 scanner.nextLine()에서 대기하면 서버 메시지를 못 받음 - 수신 전용
 * 스레드(ReceiverThread)가 백그라운드에서 계속 읽어줌 → 채팅처럼 "언제 올지 모르는 메시지"를 받을 때 필수 패턴
 */
public class ZZChatClient {
//	private static final String SERVER_IP = "192.168.0.114";
	private static final String SERVER_IP = "192.168.0.11";
	private static final int PORT = 50005;

	public static void main(String[] args) {
		System.out.println("채팅 서버(" + SERVER_IP + ":" + PORT + ")에 접속 중...");
		try (Socket socket = new Socket(SERVER_IP, PORT); Scanner scanner = new Scanner(System.in);) {
			System.out.println("채팅 서버(" + SERVER_IP + ":" + PORT + ")에 접속 성공!\n");
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);

			// ── 수신 전용 스레드 시작 ────────────────────────────
			// ★ 핵심: 이 스레드가 서버 메시지를 계속 읽어서 콘솔에 출력
			// 메인 스레드는 키보드 입력만 담당
			Thread receiverThread = new Thread(new ReceiverTask(reader, socket));
			receiverThread.setDaemon(true); // ⚠️ 데몬 스레드: 메인 스레드 종료 시 같이 종료
			receiverThread.start();

			// ── 송신 루프 (메인 스레드) ──────────────────────────
//			Scanner scanner = new Scanner(System.in);
			System.out.print("닉네임 입력하세요>> ");
			String nickname = scanner.nextLine();
			writer.println(nickname);
			while (true) {

				String input = scanner.nextLine();

				if (input.equalsIgnoreCase("/quit")) {
					writer.println("/quit");
					System.out.println("채팅방을 나갑니다.");
					break;
				}

				if (!input.isBlank()) {
					writer.println(input);
				}
			}

		} catch (ConnectException e) {
			System.out.println("서버에 연결할 수 없습니다.");
			System.out.println("  → SERVER_IP(" + SERVER_IP + ")와 포트(" + PORT + ")를 확인하세요.");
			System.out.println("  → 서버가 실행 중인지 확인하세요.");
		} catch (IOException e) {
			System.out.println("서버와 연결이 끊겼습니다.");
		}
	}

	// ══════════════════════════════════════════════════════════
	// ReceiverTask — 서버에서 오는 메시지를 전담하는 Runnable
	// ★ 수업 포인트: 메인 스레드와 이 스레드가 동시에 실행됩니다.
	// 메인 스레드 → 키보드 입력 읽고 서버로 전송
	// 이 스레드 → 서버 메시지 읽고 콘솔 출력
	// ══════════════════════════════════════════════════════════
	static class ReceiverTask implements Runnable {

		private final BufferedReader reader;
		private final Socket socket;

		ReceiverTask(BufferedReader reader, Socket socket) {
			this.reader = reader;
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				String line;

				while ((line = reader.readLine()) != null) {
					System.out.println(line);
				}
			} catch (IOException e) {
				// 서버가 종료되거나 연결이 끊긴 경우
				if (!socket.isClosed()) {
					System.out.println("\n서버와 연결이 끊겼습니다.");
				}
			}
		}
	}
}
