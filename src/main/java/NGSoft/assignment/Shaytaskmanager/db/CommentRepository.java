package NGSoft.assignment.Shaytaskmanager.db;

import NGSoft.assignment.Shaytaskmanager.concrete.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
