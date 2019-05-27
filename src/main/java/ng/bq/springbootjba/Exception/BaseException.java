package ng.bq.springbootjba.Exception;

public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }
}
