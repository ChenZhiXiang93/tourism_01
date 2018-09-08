package com.bonc.util;

/**
 * 失效时间
 * 
 */
public enum Expires {

	ONEMIN(1000 * 60),

	TWOMIN(1000 * 60 * 2), 
	FIVEMIN(1000 * 60 * 5), 
	TENMIN(1000 * 60 * 10),
	FIFTENMIN(1000 * 60 * 15),

	ONEHOUR(1000 * 60 * 60),

	HALFHOUR(1000 * 60 * 30),

	TWOHOUR(1000 * 60 * 60 * 2),

	ONEDAY(1000 * 60 * 60 * 24),

	ONEMONTH(1000 * 60 * 60 * 24 * 30),

	TIMELESS(0);

	private int value;

	private Expires(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public static void main(String[] args) {
		// System.out.println(new Date(Expires.ONEHOUR.millis));
	}

	public String getName() {
		return name();
	}

}
