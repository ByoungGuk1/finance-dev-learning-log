package com.shinhan.day15.lab;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 5. 오후 12:38:42 설명 : Lab1
 */
interface MakeStudent {
	Score make(String name, int korScore, int engScore, int mathScore);
}

@AllArgsConstructor
@Getter
@Setter
@ToString
class Score implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private int korScore;
	private int engScore;
	private int mathScore;
}

public class Lab1 {
	public static void main(String[] args) throws IOException {
		solution1();
		solution2();
	}

	private static void solution1() throws IOException {
		List<Score> stdList = new ArrayList<>();
		String title = null;
		MakeStudent maker = Score::new;
		try (FileReader fr = new FileReader("src/com/shinhan/day15/lab/data.txt");
				BufferedReader br = new BufferedReader(fr)) {
			title = br.readLine();
			String data = null;
			while ((data = br.readLine()) != null) {
				String[] dataArr = data.split("/");
				Score student = maker.make(dataArr[0], Integer.parseInt(dataArr[1]), Integer.parseInt(dataArr[2]),
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

	private static void solution2() throws IOException {
		Vector<Score> stdVector = new Vector<>();
		try (FileReader fr = new FileReader("src/com/shinhan/day15/lab/data2.txt");
				BufferedReader br = new BufferedReader(fr);) {
			String inputdata = null;
			while ((inputdata = br.readLine()) != null) {
				List<String> dataList = Arrays.stream(inputdata.split(":|\\s+")).filter((data) -> !data.isBlank())
						.toList();
				Score student = new Score(dataList.get(0), Integer.parseInt(dataList.get(1)),
						Integer.parseInt(dataList.get(2)), Integer.parseInt(dataList.get(3)));
				stdVector.add(student);
			}
		}
		stdVector.stream().forEach(System.out::println);
	}
}
