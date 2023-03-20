package exception;

/**
 * 음료 여부에 따른 예외.
 */
public class NotFoundDrink extends RuntimeException {

    private static final String MESSAGE = "해당 음료가 존재하지 않습니다.";

    public NotFoundDrink() {
        super(MESSAGE);
    }
}
