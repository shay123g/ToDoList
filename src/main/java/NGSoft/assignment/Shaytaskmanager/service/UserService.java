package NGSoft.assignment.Shaytaskmanager.service;

import NGSoft.assignment.Shaytaskmanager.db.UserRepository;
import NGSoft.assignment.Shaytaskmanager.db.User;
import NGSoft.assignment.Shaytaskmanager.exception.ExceptionMessages;
import NGSoft.assignment.Shaytaskmanager.utils.Utils;
import NGSoft.assignment.Shaytaskmanager.web.UserWebRequest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Data
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User addUser(UserWebRequest user) {
        return userRepository.save(Utils.buildUserDBObject(user));
    }

    public User updateUser(int id, UserWebRequest user) throws Exception {
        User existingUser = userRepository.findById(id).orElseThrow();

        if (Objects.equals(existingUser.getEmail(), user.getEmail())
                && Objects.equals(existingUser.getName(), user.getName())
                && Objects.equals(existingUser.getPassword(), user.getPassword())) {
            throw new Exception(ExceptionMessages.USER_NO_CHANGES);
        }
        existingUser.setIsActive(user.getIsActive() != null ? user.getIsActive() : existingUser.getIsActive());
        existingUser.setName(user.getName() != null ? user.getName() : existingUser.getName());
        existingUser.setIsAdmin(user.getIsAdmin() != null ? user.getIsAdmin() : existingUser.getIsAdmin());
        existingUser.setEmail(user.getEmail() != null ? user.getEmail() : existingUser.getEmail());
        existingUser.setPassword(user.getPassword() != null ? user.getPassword() : existingUser.getPassword());
        return userRepository.save(existingUser);
    }

    public User deleteUser(String name) {
        User existingUser = userRepository.findByName(name);
        userRepository.delete(existingUser);
        return existingUser;
    }
}

