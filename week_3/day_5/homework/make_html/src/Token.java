

/**
 * 토큰 인터페이스.
 */
public interface Token {
	String getStr();
	String accept(Visitor visitor);
}
