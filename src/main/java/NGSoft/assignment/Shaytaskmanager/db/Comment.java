package NGSoft.assignment.Shaytaskmanager.db;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Builder
/**
 * This class represent Comment table in DB
 * Assumption: a comment is associated only with one user and one task.
 */
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private String ID;
     @OneToOne
     @PrimaryKeyJoinColumn(name = "comment_id", referencedColumnName = "user_id")
    private User userId;
    @OneToOne
    @PrimaryKeyJoinColumn(name = "comment_id", referencedColumnName = "task_id")
    private Task taskId;
    private Timestamp date;
    private String comment;
}
