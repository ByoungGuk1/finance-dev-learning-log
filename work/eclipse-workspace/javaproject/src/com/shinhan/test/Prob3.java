package com.shinhan.test;

class Prob3 {
	public static void main(String args[]) {
		PhoneCharge skt = new PhoneCharge("김현우", 100, 50, 1);
		PhoneCharge ktf = new PhoneCharge("신희만", 200, 100, 2);
		PhoneCharge lgt = new PhoneCharge("조유성", 150, 500, 10);
		skt.printCharge();
		ktf.printCharge();
		lgt.printCharge();
	}
}

class PhoneCharge {
	private String user;
	private int call;
	private int sms;
	private int data;
	private int total;

	public PhoneCharge(String user, int call, int sms, int data) {
		this.user = user;
		this.call = call;
		this.sms = sms;
		this.data = data;
	}

	public int calcCharge() {
		int callFee = this.call * 10;
		if (this.call >= 200) {
			callFee *= 2;
		}
		int smsFee = this.sms * 20;
		if (this.sms >= 300) {
			smsFee *= 4;
		}
		int dataFee = this.data * 1000;
		if (this.data >= 7) {
			dataFee *= 2;
		}
		this.total = callFee + smsFee + dataFee;
		return this.total;
	}

	public void printCharge() {
		this.calcCharge();
		System.out.println(this.user + " 사용자는 이번달에 사용하신 전화요금이 " + this.total + " 원입니다.");
	}
}
