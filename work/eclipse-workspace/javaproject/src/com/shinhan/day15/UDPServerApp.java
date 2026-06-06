package com.shinhan.day15;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 5. 오후 3:09:08 설명 : UDPServerApp
 */
public class UDPServerApp {
	public static void main(String[] args) {
		System.out.println("====나는 UDP 서버====");

		// ⚠️ 주의: TCP는 ServerSocket + Socket 두 단계였지만,
		// UDP는 DatagramSocket 하나로 송수신 모두 처리합니다.
		try (DatagramSocket socket = new DatagramSocket(50002)) {

			System.out.println("UDP 포트 50002 수신 대기 중...");

			// [1] 받음 -------------------------------------------------------
			// 수신 버퍼 준비 (TCP의 byte[] arr = new byte[1024] 와 동일 역할)
			byte[] receiveBuffer = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

			// ⚠️ 주의: TCP의 serverSocket.accept()처럼 블로킹됩니다.
			// 클라이언트가 패킷을 보낼 때까지 여기서 대기합니다.
			socket.receive(receivePacket);

			// 패킷에서 실제 수신된 바이트 수만큼 문자열로 변환
			String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");

			// 클라이언트 주소 정보 (TCP의 socket.getRemoteSocketAddress() 역할)
			InetAddress clientAddress = receivePacket.getAddress();
			int clientPort = receivePacket.getPort();

			System.out.println(clientAddress.getHostAddress() + ":" + clientPort + " 에서 수신");
			System.out.println("서버에서 받음: " + receivedData);

			// [2] 보냄 -------------------------------------------------------
			// ⚠️ 주의: UDP는 상대방 주소/포트를 패킷에 직접 지정해야 합니다.
			// TCP처럼 연결이 유지되지 않기 때문입니다.
			String responseData = "Server가 보내는 데이터 (UDP)";
			byte[] sendBuffer = responseData.getBytes("UTF-8");

			DatagramPacket sendPacket = new DatagramPacket(sendBuffer, // 보낼 데이터
					sendBuffer.length, clientAddress, // 받는 사람 IP (클라이언트에서 추출)
					clientPort // 받는 사람 포트
			);

			socket.send(sendPacket);
			System.out.println("클라이언트에게 응답 전송 완료");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
