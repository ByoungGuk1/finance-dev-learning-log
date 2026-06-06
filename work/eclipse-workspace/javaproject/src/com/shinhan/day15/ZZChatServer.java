package com.shinhan.day15;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * 실습: 스레드풀 기반 멀티채팅 서버 목표: ExecutorService(스레드풀)로 다수 클라이언트 동시 처리 이해 ★ 핵심 개념 -
 * 스레드풀 없음 : 클라이언트마다 new Thread() → 100명이면 100개 스레드 생성 (자원 낭비) - 스레드풀 있음 : 미리
 * 만들어 둔 스레드를 재사용 → 자원 제어 가능
 *
 * 실행 전 확인 1. 강사 PC IP 확인 : cmd → ipconfig → IPv4 주소 메모 2. 방화벽 허용 : Windows
 * 방화벽에서 포트 50005 인바운드 허용
 */
public class ZZChatServer {

	// ── 설정 상수 ───────────────────────────────────────────
	private static final int PORT = 50005;
	private static final int POOL_SIZE = 3; // 스레드풀 크기
	private static final String DATE_FORMAT = "HH:mm:ss";
	// ────────────────────────────────────────────────────────

	// 접속 중인 모든 클라이언트 핸들러 보관 (브로드캐스트용)
	// ⚠️ 주의: 여러 스레드가 동시에 접근하므로 반드시 thread-safe 컬렉션 사용
	private static final Set<ClientHandler> clients = Collections.synchronizedSet(new HashSet<>());

	public static void main(String[] args) {

		// 스레드풀 생성
		// ⚠️ 주의: newCachedThreadPool()은 무제한 생성 → 교육장처럼 인원이 정해진 경우
		// newFixedThreadPool()로 상한선을 정하는 것이 안전합니다.
		ExecutorService threadPool = Executors.newFixedThreadPool(POOL_SIZE);

		System.out.println("┌─────────────────────────────────────────┐");
		System.out.println("│   멀티채팅 서버 시작 (포트 " + PORT + ")      │");
		System.out.println("│   스레드풀 크기: " + POOL_SIZE + "개        │");
		System.out.println("└─────────────────────────────────────────┘");

		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			while (true) { // 클라이언트 무한 대기
				// accept()는 클라이언트가 올 때까지 블로킹
				Socket clientSocket = serverSocket.accept();
				// 스레드풀에서 스레드 하나를 꺼내 ClientHandler 실행
				// ★ new Thread(handler).start() 와 결과는 같지만,
				// 스레드를 매번 생성/소멸하지 않고 재사용한다는 것이 차이점
				ClientHandler handler = new ClientHandler(clientSocket);
				threadPool.execute(handler);

				log("새 접속 요청 수락 → 현재 접속자: " + clients.size() + "명");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			threadPool.shutdown();
		}
	}

	/** 모든 클라이언트에게 메시지 전송 (브로드캐스트) */
	static void broadcast(String message, ClientHandler sender) {
		// ⚠️ 주의: synchronized 블록 — 동시에 여러 스레드가 clients를 순회하면서
		// ConcurrentModificationException 방지
		synchronized (clients) {
			for (ClientHandler client : clients) {
				if (client.getNickname().equals(sender.getNickname()))
					continue;
				client.sendMessage(message);
			}
		}
	}

	/** 클라이언트 등록 */
	static void register(ClientHandler handler) {
		clients.add(handler);
	}

	/** 클라이언트 제거 */
	static void unregister(ClientHandler handler) {
		clients.remove(handler);
	}

	/** 현재 접속자 수 */
	static int getClientCount() {
		return clients.size();
	}

	static void log(String msg) {
		String time = new SimpleDateFormat(DATE_FORMAT).format(new Date());
		System.out.println("[" + time + "] " + msg);
	}

	// ══════════════════════════════════════════════════════════
	// ClientHandler — 클라이언트 1명을 담당하는 Runnable
	// 스레드풀의 스레드 하나가 이 run()을 실행합니다.
	// ══════════════════════════════════════════════════════════
	static class ClientHandler implements Runnable {

		private final Socket socket;
		private BufferedReader reader;
		private PrintWriter writer;
		private String nickname = "unknown";

		ClientHandler(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {

			try {
				// 입출력 스트림 설정
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
				writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);

				// ── 1) 닉네임 수신 ──────────────────────────────
				// writer.print("닉네임을 입력하세요>>");
				nickname = reader.readLine();
				if (nickname == null || nickname.isBlank())
					nickname = "익명";

				// 서버에 등록 + 입장 알림
				ZZChatServer.register(this);
				String joinMsg = "[입장] " + nickname + " 님이 들어왔습니다. " + "(현재 " + ZZChatServer.getClientCount() + "명)";
				ZZChatServer.broadcast(joinMsg, this);
				ZZChatServer.log(joinMsg);

				// ── 2) 메시지 수신 루프 ─────────────────────────
				String line;
				while ((line = reader.readLine()) != null) {

					if (line.equalsIgnoreCase("/quit"))
						break; // 종료 명령

					// ★ 수업 포인트: 이 코드가 여러 스레드에서 동시에 실행됩니다.
					// 각 스레드는 자신의 클라이언트 메시지만 읽고,
					// broadcast()를 통해 전체에게 뿌립니다.
					String chatMsg = "[" + nickname + "] " + line;
					ZZChatServer.broadcast(chatMsg, this);
					ZZChatServer.log(chatMsg);
				}

			} catch (IOException e) {
				// 클라이언트가 갑자기 끊긴 경우 (비정상 종료)
				ZZChatServer.log(nickname + " 연결 끊김 (비정상)");
			} finally {
				// ── 3) 정리 ─────────────────────────────────────
				ZZChatServer.unregister(this);
				String leaveMsg = "[퇴장] " + nickname + " 님이 나갔습니다. " + "(현재 " + ZZChatServer.getClientCount() + "명)";
				ZZChatServer.broadcast(leaveMsg, this);
				ZZChatServer.log(leaveMsg);
				close();
			}
		}

		/** 이 클라이언트에게 메시지 전송 */
		void sendMessage(String message) {
			if (writer != null)
				writer.println(message);
		}

		private void close() {
			try {
				if (socket != null)
					socket.close();
			} catch (IOException ignored) {
			}
		}

		String getNickname() {
			return nickname;
		}
	}
}
