package com.shinhan.day09.lab2;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 27. 오후 12:19:02
 * 설명			: ShapeTest
 */
public class ShapeTest {
	public static void main(String[] args) {
		// 1. 객체 생성
		Rectangle    r = new Rectangle(5, 6);    // 밑변 5, 높이 6
		RectTriangle t = new RectTriangle(6, 2); // 밑변 6, 높이 2

		// 2. Shape 배열에 추가
		Shape[] shapes = { r, t };

		// 3. 순환문으로 면적/둘레 출력
		for (Shape s : shapes) {
		    System.out.println("area: "      + s.getArea());
		    System.out.println("perimeter: " + s.getPerimeter());

		    // 4. Resizable이면 0.5로 resize 후 재출력
//		    if (s instanceof Rectangle) {
		    if (s instanceof Resizable) {
		        Resizable rs = (Resizable) s;  // 강제 타입 변환
		        rs.resize(0.5);
		        System.out.println("new area: "      + s.getArea());
		        System.out.println("new perimeter: " + s.getPerimeter());
		    }
		}
	}
}
