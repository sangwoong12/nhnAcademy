

/**
 * Orange 색을 칠한 토큰.
 */
public class OrangeToken implements Token {
	String str;

	public OrangeToken(String str) {
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
