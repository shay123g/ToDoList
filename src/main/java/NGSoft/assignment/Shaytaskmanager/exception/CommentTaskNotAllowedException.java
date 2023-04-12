package NGSoft.assignment.Shaytaskmanager.exception;

public class CommentTaskNotAllowedException extends RuntimeException {
    public CommentTaskNotAllowedException() {
    }

    public CommentTaskNotAllowedException(String message) {
        super(message);
    }

    public CommentTaskNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommentTaskNotAllowedException(Throwable cause) {
        super(cause);
    }

    public CommentTaskNotAllowedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
