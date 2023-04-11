

/**
 * Yellow 색을 칠한 토큰.
 */
public class YellowToken implements Token {
	String str;
	public YellowToken(String str) {
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
