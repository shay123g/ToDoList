package NGSoft.assignment.Shaytaskmanager.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserWebRequest {

    @JsonProperty
    private String name;
    @JsonProperty
    private String email;
    @JsonProperty
    private boolean isAdmin;
    @JsonProperty
    private boolean isActive;
    @JsonProperty
    private String Password;


}
