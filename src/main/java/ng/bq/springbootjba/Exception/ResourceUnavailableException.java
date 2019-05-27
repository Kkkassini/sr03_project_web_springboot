package ng.bq.springbootjba.Exception;

public class ResourceUnavailableException extends BaseException {

    private static final long serialVersionUID = 1L;

    public ResourceUnavailableException() {
        super();
    }

    public ResourceUnavailableException(String message) {
        super(message);
    }
}