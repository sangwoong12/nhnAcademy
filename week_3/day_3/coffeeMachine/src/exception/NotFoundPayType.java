package exception;
/**
 * 결제 서비 여부에 따른 예외.
 */
public class NotFoundPayType extends RuntimeException {

    private static final String MESSAGE = "해당 결제 서비스를 지원하지 않습니다.";

    public NotFoundPayType() {
        super(MESSAGE);
    }
}
