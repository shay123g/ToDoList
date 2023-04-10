package NGSoft.assignment.Shaytaskmanager.web;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CommentWebRequest {
    @JsonProperty
    private Integer taskId;
    @JsonProperty
    @JsonAlias("assignee")
    private String userId;
    @JsonProperty
    private String comment;
    @JsonProperty
    private String requester;


}
