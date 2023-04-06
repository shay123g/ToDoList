package NGSoft.assignment.Shaytaskmanager.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByName(String name);
    public User findByEmail(String email);
    public List<User> findByIsAdmin(boolean isAdmin);
    public List<User> findByIsActive(boolean isActive);
    public User deleteByName(String name);


}
