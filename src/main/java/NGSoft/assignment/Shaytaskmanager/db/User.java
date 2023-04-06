package NGSoft.assignment.Shaytaskmanager.db;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "owners")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    private String name;
    private String email;
    private Boolean isAdmin;
    private Boolean isActive;

    //FIXME: change implementation after testing
    private String password;

}