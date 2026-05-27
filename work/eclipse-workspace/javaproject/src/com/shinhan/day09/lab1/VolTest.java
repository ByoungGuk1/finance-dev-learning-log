package com.shinhan.day09.lab1;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 27. 오전 11:32:29
 * 설명			: VolTest
 */

//Volume에 speaker, radio, tv로 만듣 객체 할당
//추상클래스이거나 인터페이스
//구현된 메서드는 각각의 클래스에서 다르게 동작
//추상메서드만 있고 생성자가 없다 => 인터페이스 추천
public class VolTest {
	public static void main(String[] args) {
		Volume[] v = new Volume[3];
        v[0] = new Speaker();
        v[1] = new Radio();
        v[2] = new TV();
        for (int i = 0; i < v.length; i++) {
            for (int j = 1; j <= 3; j++) {
                v[i].volumeUp(200);    // 200씩 3회 증가
            }
            for (int j = 1; j <= 3; j++) {
                v[i].volumeDown(300);  // 300씩 3회 감소
            }
        }
	}
}
