package com.shinhan.day15;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

/**
 * 실습: UDP 패킷 손실 시뮬레이션 - 서버 목표: UDP는 전송 보장이 없음을 직접 눈으로 확인 시나리오: 서버가 30% 확률로 패킷을
 * 무시(DROP) → 클라이언트는 알 수 없음
 *
 * 실행 순서: 이 서버 먼저 실행 → UdpLossClientApp1 실행
 */
public class UDPLossServerApp {

	// ★ 패킷을 버릴 확률 (0.0 ~ 1.0) — 수업 중 조정해 보세요
	private static final double DROP_RATE = 0.3;

	public static void main(String[] args) {

		System.out.println("====UDP 손실 시뮬레이션 서버 (DROP_RATE=" + (int) (DROP_RATE * 100) + "%)====");

		Random random = new Random();

		try (DatagramSocket socket = new DatagramSocket(50003)) {

			System.out.println("UDP 포트 50003 수신 대기...");
			System.out.println("패킷 " + (int) (DROP_RATE * 100) + "%는 의도적으로 버립니다.\n");

			int totalReceived = 0;
			int totalDropped = 0;

			// 클라이언트가 보내는 10개 패킷을 반복 수신
			for (int i = 0; i < 10; i++) {

				// [1] 받음
				byte[] buf = new byte[1024];
				DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
				socket.receive(receivePacket); // 블로킹 대기

				String received = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
				InetAddress clientAddr = receivePacket.getAddress();
				int clientPort = receivePacket.getPort();

				totalReceived++;

				// ★ 핵심: 30% 확률로 응답을 보내지 않음 = 패킷 손실 시뮬레이션
				if (random.nextDouble() < DROP_RATE) {
					totalDropped++;
					System.out.println("[DROP] 패킷 수신했지만 응답 안 함 → \"" + received + "\"");
					// ⚠️ 클라이언트는 이 사실을 전혀 알 수 없습니다!
					continue;
				}

				// [2] 보냄 (응답)
				String response = "ACK: " + received;
				byte[] sendBuf = response.getBytes("UTF-8");
				DatagramPacket sendPacket = new DatagramPacket(sendBuf, sendBuf.length, clientAddr, clientPort);
				socket.send(sendPacket);
				System.out.println("[OK ] 응답 전송 → \"" + response + "\"");
			}

			// 결과 요약
			System.out.println("\n======= 서버 통계 =======");
			System.out.println("수신 총계  : " + totalReceived + "개");
			System.out.println("드롭(손실) : " + totalDropped + "개");
			System.out.println("정상 응답  : " + (totalReceived - totalDropped) + "개");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
