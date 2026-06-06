package com.shinhan.day15;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 5. 오후 2:23:27 설명 : TCPClientApp
 */
public class TCPClientApp {
	public static void main(String[] args) {
		System.out.println("Client main");
		try (Socket socket = new Socket()) {
			socket.connect(new InetSocketAddress("192.168.0.11", 50001));
			System.out.println("연결 상태 : " + socket.isConnected());

//			발신 (string)
			String message = "클라이언트가 서버로 전송";
			OutputStream os = socket.getOutputStream();
			os.write(message.getBytes("UTF-8"));
			os.flush();

//			수신 (byte)
			InputStream is = socket.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			message = dis.readUTF();
			System.out.println("클라이언트로 수신 : " + message);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
