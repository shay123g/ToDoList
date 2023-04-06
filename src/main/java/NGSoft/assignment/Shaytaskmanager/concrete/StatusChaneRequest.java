package NGSoft.assignment.Shaytaskmanager.concrete;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StatusChaneRequest {
    @JsonProperty
    @JsonAlias("status")
    private int newStatus;
}
