package NGSoft.assignment.Shaytaskmanager.exception;

public class SameStatusException extends ApplicativeException{
    public SameStatusException() {
    }

    public SameStatusException(String message) {
        super(message);
    }

    public SameStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public SameStatusException(Throwable cause) {
        super(cause);
    }

    public SameStatusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
