package NGSoft.assignment.Shaytaskmanager.exception;

public class TaskViewNotAllowedException extends ApplicativeException{
    public TaskViewNotAllowedException() {
    }

    public TaskViewNotAllowedException(String message) {
        super(message);
    }

    public TaskViewNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskViewNotAllowedException(Throwable cause) {
        super(cause);
    }

    public TaskViewNotAllowedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
