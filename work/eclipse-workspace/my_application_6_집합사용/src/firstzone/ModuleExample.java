package firstzone;

import pack1.A;
//import pack2.B;
import pack3.C;
import pack4.D;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 5. 28. 오후 2:25:50 설명 : ModuleExample
 */
public class ModuleExample {
	public static void main(String[] args) {
		A a = new A();
		System.out.println(a.field);
		a.method();

//		B b = new B();	//	my_module_a 프로젝트에서 pack2 패키지를 export 하지 않아서 사용 불가

		C c = new C();
		System.out.println(c.field);
		c.method();

		D d = new D();
		System.out.println(d.field);
		d.method();
	}
}
