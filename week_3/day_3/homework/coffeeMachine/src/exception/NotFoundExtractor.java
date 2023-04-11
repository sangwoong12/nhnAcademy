package exception;
/**
 * 추출 여부에 따른 예외.
 */
public class NotFoundExtractor extends RuntimeException {

    private static final String MESSAGE = "해당 추출기를 지원하지 않습니다.";

    public NotFoundExtractor() {
        super(MESSAGE);
    }
}

