package com.shinhan.day15;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 5. 오후 2:09:52 설명 : NetworkExample
 */
public class NetworkExample {
	public static void main(String[] args) {
		try {
			InetAddress ia = InetAddress.getLocalHost();

			System.out.println(ia);
			System.out.println(ia.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
