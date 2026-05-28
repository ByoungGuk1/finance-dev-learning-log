package firstzone;

import java.sql.Date; //	-> module-info에 require 후 사용 (java.base가 아니라서 require 후 사용)

import pack1.A;
//import pack2.B;	//	pack2를 export하지 않아서 사용 불가
import pack3.C;
import pack4.D;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 5. 28. 오후 12:31:43 설명 : ModuleExample
 */
//	java.base 모듈인 경우 : 모듈 설정파일에 추가하지 않아도 사용 가능
//	java.base 모듈이 아닌 경우 : 모듈 설정파일(module-info)에 require 후 사용

//	java.lang (java.base.java.lang) 모듈 설정파일에 추가할 필요 없음
//		import 필요 없이 그냥 사용 가능

//	내가 이해한 내용
//		default import => java.base.java.lang
//			java.base 이면서 이외의 패키지인 경우 import 후 사용
//		default module => java.base
//			이외의 모듈인 경우 module-info 에 require 후 import 후 사용

public class ModuleExample {
	void f1() {
		Date d1 = new Date(123456789);
		System.out.println(d1.getTime());
	}

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
