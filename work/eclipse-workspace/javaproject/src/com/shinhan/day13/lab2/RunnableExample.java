package com.shinhan.day13.lab2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 2. 오후 2:27:52 설명 : RunableExample
 */
public class RunnableExample {
	public static void main(String[] args) {
		String[][] mails = new String[1000][3];
		for (int i = 0; i < mails.length; i++) {
			mails[i][0] = "bksong121212@gmail.com";
			mails[i][1] = "test" + (123 + i) + "@gmail.com";
			mails[i][2] = "메일 내용입니다~~";
		}

//		이거 기억하기 => 쓰레드 풀의 사용 방법
		ExecutorService executorService = Executors.newFixedThreadPool(5);

		for (String[] mail : mails) {
//			로컬 클래스에서 지역변수가 사용되면, 그 변수는 final 로 동작
			Runnable anImpl = new Runnable() {
				@Override
				public void run() {
					System.out.println("[" + Thread.currentThread().getName() + "]\n" + "from : " + mail[0] + "\nto : "
							+ mail[1] + "\ncontent : " + mail[2]);
				}
			};
			executorService.execute(anImpl);
		}
		executorService.shutdown();
	}
}
