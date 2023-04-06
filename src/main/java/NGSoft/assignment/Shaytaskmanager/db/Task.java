package NGSoft.assignment.Shaytaskmanager.db;

import NGSoft.assignment.Shaytaskmanager.concrete.Status;
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
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    private String title;
    private String description;
    private Status status;
    private String assignee;


}