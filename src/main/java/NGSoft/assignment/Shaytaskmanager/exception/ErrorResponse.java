package NGSoft.assignment.Shaytaskmanager.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private Timestamp timestamp;
    private String message;
    private String uri;

}
