package NGSoft.assignment.Shaytaskmanager.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class TaskWebRequest {
    @JsonProperty
    private String title;
    @JsonProperty
    private String description;
    @JsonProperty
    private int status;
    @JsonProperty
    private String assignee;


}
