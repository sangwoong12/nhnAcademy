package type;
/**
 * 토핑 타입.
 */
public enum ToppingType {
	NULL(""), CREAM("크림"), CHOCE("초코");

	String koreaName;

	ToppingType(String koreaName) {
		this.koreaName = koreaName;
	}

	public String getKoreaName() {
		return koreaName;
	}
}
