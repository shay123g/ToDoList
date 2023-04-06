package NGSoft.assignment.Shaytaskmanager.db;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Entity
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    private String name;
    private String email;
    private boolean isAdmin;
    private boolean isActive;

    //FIXME: change implementation after testing
    private String Password;

}
