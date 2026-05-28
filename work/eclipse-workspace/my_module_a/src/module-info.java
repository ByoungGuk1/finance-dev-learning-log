module my_module_a {
	exports pack1;
//	모듈과 라이브러리의 차이
//	exports pack2;
//	정보은닉 여부

//	전이
	requires transitive my_module_b;
}