package org.example.item;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Orange 색에 해당하는 토큰의 타입.
 */
public enum OrangeType {
	PUBLIC("public"), IMPORT("import"), STATIC("static"),
	VOID("void"), THROWS("throws"), NEW("new"),
	PACKAGE("package"), TRY("try"), CATCH("catch")
	,FOR("for"), IF("if"), ELSE("else"),RETURN("return");

	private final String str;

	OrangeType(String str) {
		this.str = str;
	}

	/**
	 * 토큰이 해당타입에 해당하는 확인하는 메서드.
	 *
	 * @param token : 토큰
	 * @return : 여부에 따라 boolean형 반환
	 */
	public static boolean containToken(String token) {
		return Arrays.stream(OrangeType.values()).map(o -> o.str)
				.collect(Collectors.toList()).contains(token);
	}
}