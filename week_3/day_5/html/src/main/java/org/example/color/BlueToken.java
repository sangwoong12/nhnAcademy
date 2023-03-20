package org.example.color;

import org.example.visitor.Visitor;

/**
 * blue 색을 칠한 토큰.
 */
public class BlueToken implements Token {
	String str;

	public BlueToken(String str) {
		this.str = str;
	}

	public String getStr() {
		return str;
	}

	@Override
	public String accept(Visitor visitor) {
		return visitor.visit(this);
	}
}
