package ng.bq.springbootjba.Exception;

public class UnauthorizedActionException extends BaseException {

    private static final long serialVersionUID = 1L;

    public UnauthorizedActionException() {
        super();
    }

    public UnauthorizedActionException(String message) {
        super(message);
    }
}
