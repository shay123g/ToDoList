package NGSoft.assignment.Shaytaskmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import java.sql.Timestamp;
import java.util.Calendar;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> missingParameterHandler(MissingParameterException exception){
        ErrorResponse response = new ErrorResponse(new Timestamp(Calendar.getInstance().getTimeInMillis()),ExceptionMessages.MISSING_PARAMETER + exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.SEE_OTHER);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> objectNotFoundHandler(ObjectNotFoundException exception){
        ErrorResponse response = new ErrorResponse(new Timestamp(Calendar.getInstance().getTimeInMillis()),ExceptionMessages.OBJECT_NOT_EXIST + exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.SEE_OTHER);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> commentTaskNotAllowedHandler(CommentTaskNotAllowedException exception){
        ErrorResponse response = new ErrorResponse(new Timestamp(Calendar.getInstance().getTimeInMillis()),ExceptionMessages.COMMENT_TASK_OPERATION_NOT_ALLOWED + exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.SEE_OTHER);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> commentTaskNotAllowedHandler(TaskViewNotAllowedException exception){
        ErrorResponse response = new ErrorResponse(new Timestamp(Calendar.getInstance().getTimeInMillis()),ExceptionMessages.TASK_VIEW_NOT_ALLOWED + exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.SEE_OTHER);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> userNoChangeHandler(UserNoChangeException exception){
        ErrorResponse response = new ErrorResponse(new Timestamp(Calendar.getInstance().getTimeInMillis()),ExceptionMessages.USER_NO_CHANGES + exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.SEE_OTHER);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> invalidStatusFlowHandler(InvalidStatusFlowException exception){
        ErrorResponse response = new ErrorResponse(new Timestamp(Calendar.getInstance().getTimeInMillis()),ExceptionMessages.TASK_INVALID_STATUS_FLOW + exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.SEE_OTHER);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> sameStatusHandler(SameStatusException exception){
        ErrorResponse response = new ErrorResponse(new Timestamp(Calendar.getInstance().getTimeInMillis()),ExceptionMessages.TASK_NEW_STATUS_SAME_OLD_STATUS + exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.SEE_OTHER);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> operationNotAllowedHAndler(OperationNotAllowedException exception){
        ErrorResponse response = new ErrorResponse(new Timestamp(Calendar.getInstance().getTimeInMillis()),ExceptionMessages.OPERATION_NOT_ALLOWED + exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.SEE_OTHER);
    }

}
