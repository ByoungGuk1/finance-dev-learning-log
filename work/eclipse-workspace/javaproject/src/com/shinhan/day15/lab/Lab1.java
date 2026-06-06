package com.shinhan.day15.lab;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 5. 오후 12:38:42 설명 : Lab1
 */
interface MakeStudent {
	Student make(String name, int korScore, int engScore, int mathScore);
}

@AllArgsConstructor
@Getter
@Setter
@ToString
class Student implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private int korScore;
	private int engScore;
	private int mathScore;
}

public class Lab1 {
	public static void main(String[] args) throws IOException {
		solution1();
//		solution2();
	}

	private static void solution1() throws IOException {
		FileReader fr = new FileReader("src/com/shinhan/day15/lab/data.txt");
		List<Student> stdList = new ArrayList<>();
		String title = null;
		MakeStudent maker = Student::new;
		try (BufferedReader br = new BufferedReader(fr)) {
			title = br.readLine();
			String data = null;
			while ((data = br.readLine()) != null) {
				String[] dataArr = data.split("/");
				Student student = maker.make(dataArr[0], Integer.parseInt(dataArr[1]), Integer.parseInt(dataArr[2]),
						Integer.parseInt(dataArr[3]));
				stdList.add(student);
			}
		}
		String[] titleArr = title.split("/");
		Arrays.stream(titleArr).forEach(str -> {
			System.out.print(str + "\t");
		});
		System.out.println();
		stdList.stream().forEach(data -> {
			System.out.println(data.getName() + '\t' + data.getKorScore() + '\t' + data.getEngScore() + '\t'
					+ data.getMathScore());
		});
	}
}
