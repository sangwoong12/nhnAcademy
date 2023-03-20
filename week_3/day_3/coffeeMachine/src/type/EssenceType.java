package type;

/**
 * 원료 타입.
 */
public enum EssenceType {
	ESPRESSO("에스프레소"), CHOCE("초코"), PEACH_ESSENCE("복숭아원액");

	String koreaName;

	EssenceType(String koreaName) {
		this.koreaName = koreaName;
	}

	public String getKoreaName() {
		return koreaName;
	}
}
