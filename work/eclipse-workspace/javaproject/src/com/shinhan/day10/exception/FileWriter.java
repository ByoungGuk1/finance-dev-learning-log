package com.shinhan.day10.exception;

import java.io.IOException;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 5. 28. 오후 4:13:37 설명 : FileWriter
 */
public class FileWriter implements AutoCloseable {

	public FileWriter(String filePath) throws IOException {
		System.out.println(filePath + " 파일을 엽니다.");
	}

	public void write(String data) throws IOException {
		System.out.println(data + "를 파일에 저장");
	}

	// 자원 반납
	@Override
	public void close() throws IOException {
		System.out.println("파일 닫습니다.");
	}
}
