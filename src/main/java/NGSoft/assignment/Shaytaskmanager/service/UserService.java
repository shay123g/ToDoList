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

    public User addUser(UserWebRequest user){
        return userRepository.save(Utils.buildUserDBObject(user));
    }

    public User updateUser(int id, UserWebRequest user) throws Exception {
        User existingUser = userRepository.findById(String.valueOf(id)).orElseThrow();

        if (Objects.equals(existingUser.getEmail(), user.getEmail())
        && Objects.equals(existingUser.getName(), user.getName())
        && Objects.equals(existingUser.getPassword(), user.getPassword())) {
            throw new Exception(ExceptionMessages.USER_NO_CHANGES);
        }
        return userRepository.save(Utils.buildUserDBObject(user));
    }

    public User deleteUser(int id) {
        User existingUser = userRepository.findById(String.valueOf(id)).orElseThrow();
        userRepository.delete(existingUser);
        return existingUser;
    }
}
