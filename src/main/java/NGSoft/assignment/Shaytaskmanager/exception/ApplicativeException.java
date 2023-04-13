package NGSoft.assignment.Shaytaskmanager.exception;

public class ApplicativeException extends RuntimeException {
    public ApplicativeException() {
    }

    public ApplicativeException(String message) {
        super(message);
    }

    public ApplicativeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicativeException(Throwable cause) {
        super(cause);
    }

    public ApplicativeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
