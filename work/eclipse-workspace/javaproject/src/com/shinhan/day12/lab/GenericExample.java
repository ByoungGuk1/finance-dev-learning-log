package com.shinhan.day12.lab;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 1. 오후 2:50:04 설명 : GenericExample
 */
public class GenericExample {
	public static void main(String[] args) {
		Applicant<Person> personType = new Applicant<>(new Person());
		System.out.println(personType);

		Applicant<Worker> workerType = new Applicant<>(new Worker());
		System.out.println(workerType);

		Applicant<Student> studentType = new Applicant<>(new Student());
		System.out.println(studentType);

		Applicant<HighStudent> highStudentType = new Applicant<>(new HighStudent());
		System.out.println(highStudentType);

		Applicant<MiddleStudent> middleStudentType = new Applicant<>(new MiddleStudent());
		System.out.println(middleStudentType);
		System.out.println();

		f1(personType, workerType, studentType, highStudentType, middleStudentType);
//		f2(personType, workerType, studentType, highStudentType, middleStudentType);
//		f3(personType, workerType, studentType, highStudentType, middleStudentType);

	}

	public static void f1(Applicant<?>... types) {
//		모든 사람이 신청 가능		
		for (Applicant<?> type : types) {
			Course.registerCourse1(type);
		}
	}

//	public static void f2(Applicant<?>... types) {
////		학생만 신청 가능
//		for (Applicant<?> type : types) {
//			try {
//				Course.registerCourse2(type);
//			} catch (Exception e) {
//				System.err.println(e.getMessage() + "err");
//			}
//		}
//	}
//
//	public static void f3(Applicant<?>... types) {
////		직장인 및 일반인만 신청 가능
//		for (Applicant<?> type : types) {
//			try {
//				Course.registerCourse3(type);
//			} catch (Exception e) {
//				System.err.println(e.getMessage() + "err");
//			}
//		}
//	}
}
