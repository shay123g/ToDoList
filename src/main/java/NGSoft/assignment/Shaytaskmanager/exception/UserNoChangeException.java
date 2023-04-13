package NGSoft.assignment.Shaytaskmanager.exception;

public class UserNoChangeException extends ApplicativeException{
    public UserNoChangeException() {
    }

    public UserNoChangeException(String message) {
        super(message);
    }

    public UserNoChangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNoChangeException(Throwable cause) {
        super(cause);
    }

    public UserNoChangeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
