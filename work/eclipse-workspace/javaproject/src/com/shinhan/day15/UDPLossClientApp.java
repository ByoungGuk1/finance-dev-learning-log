package com.shinhan.day15;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

/**
 * 실습: UDP 패킷 손실 시뮬레이션 - 클라이언트 목표: 서버 응답이 없을 때(손실) 클라이언트가 어떻게 되는지 확인
 *
 * ▶ 실험 1 — 타임아웃 없는 버전 (TIMEOUT_MS = 0) → 서버가 드롭하면 클라이언트는 receive()에서 영원히 대기
 * (프리즈)
 *
 * ▶ 실험 2 — 타임아웃 있는 버전 (TIMEOUT_MS = 1000) → "타임아웃: 응답 없음(패킷 손실 추정)" 출력 후 다음으로
 * 진행
 *
 * ★ 수업 포인트: UDP는 손실을 감지하려면 애플리케이션이 직접 구현해야 합니다. TCP는 이것을 OS/네트워크 스택이 자동으로
 * 처리합니다.
 */
public class UDPLossClientApp {

	// ★ 0 으로 바꾸면 타임아웃 없이 → 손실 시 프리즈 체험
	private static final int TIMEOUT_MS = 1000;

	public static void main(String[] args) {

		System.out.println("====UDP 손실 시뮬레이션 클라이언트 (타임아웃=" + TIMEOUT_MS + "ms)====\n");

		int sentCount = 0;
		int receivedCount = 0;
		int lostCount = 0;

		try (DatagramSocket socket = new DatagramSocket()) {

			// ★ 핵심: 타임아웃 설정 — 이 시간 안에 응답 없으면 SocketTimeoutException 발생
			// ⚠️ 0이면 무한 대기 (타임아웃 없음)
			socket.setSoTimeout(TIMEOUT_MS);

			InetAddress serverAddr = InetAddress.getByName("localhost");
			int serverPort = 50003;

			for (int i = 1; i <= 10; i++) {

				// [1] 보냄
				String message = "메시지-" + i;
				byte[] sendBuf = message.getBytes("UTF-8");
				DatagramPacket sendPacket = new DatagramPacket(sendBuf, sendBuf.length, serverAddr, serverPort);
				socket.send(sendPacket);
				sentCount++;
				System.out.print("[" + i + "/10] 전송: \"" + message + "\" → ");

				// [2] 받음 (타임아웃 적용)
				try {
					byte[] recvBuf = new byte[1024];
					DatagramPacket recvPacket = new DatagramPacket(recvBuf, recvBuf.length);

					socket.receive(recvPacket); // ← TIMEOUT_MS 안에 응답 없으면 예외

					String response = new String(recvPacket.getData(), 0, recvPacket.getLength(), "UTF-8");
					System.out.println("수신 OK: \"" + response + "\"");
					receivedCount++;

				} catch (SocketTimeoutException e) {
					// ★ UDP 손실 감지 포인트
					// ⚠️ 실제로 패킷이 손실됐는지, 서버가 느린 건지 클라이언트는 알 수 없습니다.
					System.out.println("타임아웃: 응답 없음 (패킷 손실 추정)");
					lostCount++;
				}

				// 다음 패킷 전에 살짝 대기 (서버 처리 여유)
				Thread.sleep(200);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 결과 요약
		System.out.println("\n======= 클라이언트 통계 =======");
		System.out.println("전송     : " + sentCount + "개");
		System.out.println("수신 성공: " + receivedCount + "개");
		System.out.println("손실 추정: " + lostCount + "개  ← 서버 DROP과 비교해 보세요!");
		System.out.println();
		System.out.println("[면접 포인트] UDP 손실 대응 방법:");
		System.out.println("  1. 재전송 타이머 (이 예제의 setSoTimeout)");
		System.out.println("  2. 시퀀스 번호로 누락 감지");
		System.out.println("  3. 위 두 가지를 합치면 → 사실상 TCP와 비슷해짐");
		System.out.println("  → 그래서 UDP는 '속도가 중요하고 일부 손실 허용되는' 곳에 씁니다");
		System.out.println("     예) 실시간 스트리밍, 온라인 게임, DNS");
	}
}
