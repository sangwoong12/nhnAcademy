package org.example.type;

/**
 * 액체 타입.
 */
public enum LiquidType {
	WATER("물"), MILK("우유");
	String koreaName;
	LiquidType(String str) {
		this.koreaName = str;
	}

	public String getKoreaName() {
		return koreaName;
	}
}
