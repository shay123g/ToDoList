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
    private Boolean isAdmin = false;
    @JsonProperty
    private Boolean isActive = false;
    @JsonProperty
    private String password;


}
