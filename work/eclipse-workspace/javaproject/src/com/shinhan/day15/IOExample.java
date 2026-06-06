package com.shinhan.day15;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 5. 오전 11:15:01 설명 : IOExample
 */
public class IOExample {
	public static void main(String[] args) {
//		입력
//			inputStream, FileInputStream, FileReader, InputStreamReader, BufferedReader
//		Stream -> 1byte씩 처리
//		키보드 읽기
		f1();
//		파일 읽기
		f2();
		f3();
//		입출력 향상을 위한 보조 스트림 사용
		f4();

//		출력
//			OutputStream, PrintStream, FileOutputStream, FileWriter,
//				OutputStreamReader(보조), BufferedWriter(보조),
//				DataOutputStream(보조) -> 자바의 기본형, ObjectOutputStream(보조) -> 자바의 객체
//		printStream
		f5();
//		file
		f6();
		f7();

//		기본형 통신
		f8(); // 쓰기
		f9(); // 읽기

//		오브젝트 통신
		f10(); // 쓰기
		f11(); // 읽기

//		파일의 정보 가져오기
		f12();
	}

	private static void f1() {
//		Stream -> 1byte씩 처리
//		키보드 입력
		InputStream is = System.in;
		InputStreamReader isr = new InputStreamReader(is); // 보조 스트림
		int i = 0;
		try {
			while ((i = isr.read()) != -1) {
				System.out.println((char) i);
			}
		} catch (IOException e) {
			System.err.println("IOE : " + e.getMessage());
		}
	}

	private static void f2() {
		// 프로젝트가 기준
		try (FileInputStream fis = new FileInputStream("src/com/shinhan/day15/Day14Review.java")) {
			int i = 0;
			while ((i = fis.read()) != -1) {
				System.out.print((char) i);
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	private static void f3() {
		// 프로젝트가 기준
		try (FileReader fr = new FileReader("src/com/shinhan/day15/Day14Review.java")) {
			int i = 0;
			while ((i = fr.read()) != -1) {
				System.out.print((char) i);
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	private static void f4() {
		// 프로젝트가 기준
		try (FileReader fr = new FileReader("src/com/shinhan/day15/Day14Review.java")) {
			BufferedReader br = new BufferedReader(fr);
			String str = null;
			while ((str = br.readLine()) != null) {
				System.out.println(str);
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	private static void f5() {
		PrintStream ps = System.out;
		ps.write(100);
		ps.println();
		ps.println("문자출력");
	}

	private static void f6() {
		try (FileOutputStream fos = new FileOutputStream("FileOutput.txt")) {
			fos.write(97);
			fos.write(98);
			fos.write(99);
			fos.write(100);
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	private static void f7() {
		try (FileWriter fw = new FileWriter("FileOutput.txt")) {
			fw.write("문자열 출력하기");
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	private static void f8() {
		try (FileOutputStream fos = new FileOutputStream("DataOutput.txt");
				DataOutputStream dos = new DataOutputStream(fos);) {
			dos.writeInt(100);
			dos.writeChar('김');
			dos.writeFloat(3.14f);
			dos.writeUTF("문자열 utf");
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	private static void f9() {
		try (FileInputStream fos = new FileInputStream("DataOutput.txt");
				DataInputStream dos = new DataInputStream(fos);) {
			int intData = dos.readInt();
			char charData = dos.readChar();
			float floatData = dos.readFloat();
			String strData = dos.readUTF();

			System.out.println(intData);
			System.out.println(charData);
			System.out.println(floatData);
			System.out.println(strData);
		} catch (FileNotFoundException e) {
			System.err.println("FileNotFoundException : " + e.getMessage());
		} catch (IOException e) {
			System.err.println("IOException : " + e.getMessage());
		}
	}

	private static void f10() {
		List<Member> memList = new ArrayList<>();

		memList.add(new Member("A", "남자", 50, "1234"));
		memList.add(new Member("B", "여자", 60, "1234"));
		memList.add(new Member("C", "여자", 70, "1234"));
		memList.add(new Member("D", "남자", 80, "1234"));
		memList.add(new Member("E", "남자", 90, "1234"));

		try (FileOutputStream fos = new FileOutputStream("Member.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
//			Object의 직렬화 필요 => 해당 클래스가 Serializable 구현
			memList.stream().forEach(data -> {
				try {
					oos.writeObject(data);
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
			});
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	private static void f11() {
		List<Member> memList = new ArrayList<>();
		try (FileInputStream fis = new FileInputStream("Member.dat");
				ObjectInputStream ois = new ObjectInputStream(fis);) {
//			Object의 역직렬화 필요 => 해당 클래스가 Serializable 구현
			Object obj = null;
			try {
				while ((obj = ois.readObject()) != null) {
					if (obj instanceof Member m) {
						memList.add(m);
					}
				}
			} catch (java.io.EOFException e) {
			}
			memList.stream().forEach(System.out::println);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void f12() {
		File f1 = new File("Member.dat");
		System.out.println("읽을 수 있는지 : " + f1.canRead());
		System.out.println("실행 가능한지 : " + f1.exists());
		System.out.println("파일 절대 경로 : " + f1.getAbsolutePath());

		System.out.println("현재 파일 경로의 구분자 : " + File.separator);

		File f2 = new File(".\\src\\com\\shinhan\\day15\\Member.java");
		System.out.println(f2.canRead());

		Path path = Paths.get("Member.dat");
		System.out.println(path.toUri().toString());
	}
}
