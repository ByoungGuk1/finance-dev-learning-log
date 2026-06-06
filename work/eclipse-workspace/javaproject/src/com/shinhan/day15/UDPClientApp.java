package com.shinhan.day15;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 5. 오후 3:08:23 설명 : UDPClientApp
 */
public class UDPClientApp {

	public static void main(String[] args) {
		System.out.println("====나는 UDP 클라이언트====");

		// ⚠️ 주의: TCP Socket 생성 시 연결(3-way handshake)이 발생하지만,
		// UDP DatagramSocket은 연결 없이 생성만 합니다.
		try (DatagramSocket socket = new DatagramSocket()) {

			InetAddress serverAddress = InetAddress.getByName("localhost");
			int serverPort = 50002;

			// [1] 보냄 -------------------------------------------------------
			// TCP: os.write(data.getBytes("UTF-8"))
			// UDP: 보낼 데이터 + 목적지(서버 IP/포트)를 패킷에 담아서 전송
			String sendData = "클라이언트가 보내는 데이터 (UDP)";
			byte[] sendBuffer = sendData.getBytes("UTF-8");

			DatagramPacket sendPacket = new DatagramPacket(sendBuffer, // 보낼 데이터
					sendBuffer.length, serverAddress, // 서버 IP
					serverPort // 서버 포트
			);

			socket.send(sendPacket);
			System.out.println("서버로 전송 완료 (연결 확인 없음 — UDP 특성)");

			// [2] 받음 -------------------------------------------------------
			// TCP: DataInputStream.readUTF() 로 수신
			// UDP: 수신 버퍼를 준비하고 패킷을 기다림
			byte[] receiveBuffer = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

			// ⚠️ 주의: 서버가 응답을 보내지 않으면 여기서 무한 대기합니다.
			// 실무에서는 socket.setSoTimeout(ms)으로 타임아웃을 설정합니다.
			socket.receive(receivePacket);

			String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");

			System.out.println("서버에게서 받음: " + receivedData);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
