package com.shinhan.day13.lab2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 2. 오후 2:48:35 설명 : CallableSubmitExmaple
 */
public class CallableSubmitExmaple {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(5);

		int sum = 0;

		for (int i = 0; i < 100; i++) {
			int value = i + 1;
			Callable<Integer> anCallAbleImpl = new Callable<>() {
				@Override
				public Integer call() throws Exception {
					int result = 0;
					for (int j = 0; j < value; j++) {
						result += (j + 1);
					}
					System.out.println(Thread.currentThread().getName() + " -> result : " + result);
					return result;
				}
			};

			Future<Integer> result = executorService.submit(anCallAbleImpl);
			try {
//				System.out.println(Thread.currentThread().getName() + " -> result : " + result.get());
				sum += result.get();
			} catch (InterruptedException | ExecutionException e) {
				System.err.println("exception CallableSubmitExmaple.main() : " + e.getMessage());
			}
		}
		executorService.shutdown();
		System.out.println(sum);
	}
}
