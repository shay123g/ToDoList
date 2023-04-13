package NGSoft.assignment.Shaytaskmanager.exception;

public class InvalidStatusFlowException extends ApplicativeException{
    public InvalidStatusFlowException() {
    }

    public InvalidStatusFlowException(String message) {
        super(message);
    }

    public InvalidStatusFlowException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidStatusFlowException(Throwable cause) {
        super(cause);
    }

    public InvalidStatusFlowException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
