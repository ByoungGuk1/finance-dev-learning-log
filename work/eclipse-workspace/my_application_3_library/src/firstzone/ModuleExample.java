package firstzone;

import pack1.A;
import pack2.B;
import pack3.C;
import pack4.D;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 5. 28. 오후 12:31:43 설명 : ModuleExample
 */
public class ModuleExample {
	public static void main(String[] args) {
		A a = new A();
		System.out.println(a.field);
		a.method();

//		라이브러리 형태로 가져와서 package 의 export 여부와 무관하게 사용 가능
		B b = new B();
//		정보 은닉 불가
		System.out.println(b.field);
		b.method();

		C c = new C();
		System.out.println(c.field);
		c.method();

		D d = new D();
		System.out.println(d.field);
		d.method();
	}
}
