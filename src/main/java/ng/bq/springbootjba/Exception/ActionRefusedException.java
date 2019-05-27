package ng.bq.springbootjba.Exception;

public class ActionRefusedException extends BaseException {

    private static final long serialVersionUID = 1L;

    public ActionRefusedException() {
        super();
    }

    public ActionRefusedException(String message) {
        super(message);
    }
}