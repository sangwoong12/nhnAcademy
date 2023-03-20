package org.example.item;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * blue 색에 해당하는 토큰의 타입.
 */
public enum BlueType {
	PERIOD("."), SEMICOLON(";"), LEFT_BRACE("{"), LEFT_PARENTHESIS("("),
	RIGHT_BRACE("}"), RIGHT_PARENTHESIS(")"), LEFT_BRACKETS("["), RIGHT_BRACKETS("]");

	public final String str;

	BlueType(String str) {
		this.str = str;
	}

	/**
	 * 토큰이 해당타입에 해당하는 확인하는 메서드.
	 *
	 * @param token : 토큰
	 * @return : 여부에 따라 boolean형 반환
	 */
	public static boolean containToken(String token) {
		return Arrays.stream(BlueType.values()).map(o -> o.str)
				.collect(Collectors.toList()).contains(token);
	}
}