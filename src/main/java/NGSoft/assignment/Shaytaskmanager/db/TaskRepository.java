package NGSoft.assignment.Shaytaskmanager.db;

import NGSoft.assignment.Shaytaskmanager.concrete.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Task, Integer> {

    public List<Task> findByTitle(String title);
    public List<Task> findByStatus(Status status);
    public List<Task> findByAssignee(String assignee);

    public List<Task> deleteByStatus(Status status);
    public List<Task> deleteByAssignee(Status status);



}
