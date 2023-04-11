package exception;

/**
 * 결제 서비 여부에 따른 예외.
 */
public class NotFoundDrinkType extends RuntimeException {

    private static final String MESSAGE = "해당 음류 타입을 지원하지 않습니다.";

    public NotFoundDrinkType() {
        super(MESSAGE);
    }
}
