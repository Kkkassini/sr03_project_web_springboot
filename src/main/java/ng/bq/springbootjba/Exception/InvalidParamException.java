package ng.bq.springbootjba.Exception;


public class InvalidParamException extends BaseException {

    private static final long serialVersionUID = 1L;

    public InvalidParamException() {
        super();
    }

    public InvalidParamException(String message) {
        super(message);
    }
}
