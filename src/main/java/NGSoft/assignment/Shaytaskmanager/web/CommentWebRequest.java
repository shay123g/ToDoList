package NGSoft.assignment.Shaytaskmanager.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CommentWebRequest {
    @JsonProperty
    private Integer taskId;
    @JsonProperty
    private String user;
    @JsonProperty
    private String comment;


}
