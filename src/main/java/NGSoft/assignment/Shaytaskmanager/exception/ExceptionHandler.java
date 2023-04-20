package NGSoft.assignment.Shaytaskmanager.exception;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import java.sql.Timestamp;
import java.util.Calendar;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> handler(ApplicativeException exception, HttpServletRequest req){
        ErrorResponse response = new ErrorResponse(new Timestamp(Calendar.getInstance().getTimeInMillis()),
                exception.getMessage(),
                req.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.SEE_OTHER);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> handleSecurityException(AuthenticationException exception, HttpServletRequest req){
        ErrorResponse response = new ErrorResponse(new Timestamp(Calendar.getInstance().getTimeInMillis()),
                exception.getMessage(),
                req.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
