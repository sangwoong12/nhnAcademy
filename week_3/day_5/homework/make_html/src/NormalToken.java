

/**
 * 기본 색을 칠한 토큰.
 */
public class NormalToken implements Token {

	String str;

	public NormalToken(String str) {
		this.str = str;
	}
	@Override
	public String getStr() {
		return null;
	}

	@Override
	public String accept(Visitor visitor) {
		return visitor.visit(str);
	}
}
