package com.shinhan.day15;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 5. 오후 3:20:54 설명 : JSONExample
 */
public class JSONExample {
	public static void main(String[] args) {
		f1();
		f2();
		f3();
		f4();
	}

	private static void f1() {
		String s = """
				{"name":"홍길동","age":20}
				""";
		System.out.println(s);
	}

	private static void f2() {
		JSONObject jo = new JSONObject();
		jo.put("id", "winter");
		jo.put("name", "홍길동");
		jo.put("age", 25);
		jo.put("student", true);

		JSONObject tel = new JSONObject();
		tel.put("home", "02-1234-5678");
		tel.put("mobile", "010-4567-4567");
		jo.put("tel", tel);

		JSONArray skill = new JSONArray();
		skill.put("Java");
		skill.put("c");
		skill.put("python");
		jo.put("skill", skill);

		System.out.println(jo);
	}

	private static void f3() {
		String jsonData = """
				{"student":true,"skill":["Java","c","python"],"name":"홍길동","tel":{"mobile":"010-4567-4567","home":"02-1234-5678"},"id":"winter","age":25}
				""";
		JSONObject data = new JSONObject(jsonData);
		System.out.println(data);
	}

	private static void f4() {
		String jsonObj = """
				{
				"market": "KRW-BTC",
				"trade_date_utc": "2018-04-18",
				"trade_time_utc": "10:19:58",
				"timestamp": 1524046798000,
				"trade_price": 8616000,
				"trade_volume": 0.03060688,
				"prev_closing_price": 8450000,
				"change_price": 166000,
				"ask_bid": "ASK"
				}
				""";

		JSONObject jsonData = new JSONObject(jsonObj);
//		System.out.println(jsonData);

		jsonData.toMap().entrySet().stream().forEach((data) -> {
			System.out.print(data.getKey() + " => " + data.getValue() + "\n");
		});
	}
}
