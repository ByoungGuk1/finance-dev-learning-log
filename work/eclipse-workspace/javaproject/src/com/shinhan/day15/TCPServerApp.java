package com.shinhan.day15;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 5. 오후 2:16:46 설명 : TCPServerApp
 */
public class TCPServerApp {
	public static void main(String[] args) {
		System.out.println("Server main");
		try (ServerSocket ss = new ServerSocket()) {
			ss.bind(new InetSocketAddress(50001));
			System.out.println("서버 오픈");
			Socket socket = ss.accept(); // 블로킹, client 요청이 올 때까지 대기
//			client 요청이 오면 블로킹 해제
			if (socket == null) {
				System.err.println("socket 연결 오류");
				return;
			}
			InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
			System.out.println("클라이언트 요청 : " + isa.getHostString());

//			수신 (byte)
			InputStream is = socket.getInputStream();
			byte[] dataArr = new byte[1024];
			int dataLength = is.read(dataArr);
			String data = new String(dataArr, 0, dataLength, "UTF-8");
			System.out.println("서버 수신 : " + data);

//			발신 (string)
			String message = "서버가 클라이언트로 전송";
			OutputStream os = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			dos.writeUTF(message);
			dos.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
