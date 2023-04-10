package NGSoft.assignment.Shaytaskmanager.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserWebRequest {

    public static final String DEFAULT_USER = "SYS";

    @JsonProperty
    private String name;
    @JsonProperty
    private String email;
    @JsonProperty
    private Boolean isAdmin;
    @JsonProperty
    private Boolean isActive;
    @JsonProperty
    private String password;
    @JsonProperty
    private String requester;


}
