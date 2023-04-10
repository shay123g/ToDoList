package NGSoft.assignment.Shaytaskmanager.service;

import NGSoft.assignment.Shaytaskmanager.db.UserRepository;
import NGSoft.assignment.Shaytaskmanager.db.User;
import NGSoft.assignment.Shaytaskmanager.exception.ExceptionMessages;
import NGSoft.assignment.Shaytaskmanager.web.UserWebRequest;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
@Data
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MiscService utilService;

    /**
     * The Application start with default Admin named SYS, created by the system.
     * Before adding user to the system, check if the requester has permission (aka admin) or this is system call, so permitted
     */
    public User addUser(UserWebRequest user) {
        if (user != null) {
            if (!user.getRequester().equals("SYSTEM")) {
                utilService.isUserPermittedForOperation(userRepository.findByName(user.getRequester()));
            }
            return userRepository.save(utilService.buildUserDBObject(user));
        }
        throw new RuntimeException(ExceptionMessages.MISSING_PARAMETER);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User updateUserGeneralDetails(UserWebRequest userDataToUpdate) {
        if (userDataToUpdate != null) {
            User existingUserToUpdate = userRepository.findByName(userDataToUpdate.getName());
            User requester = userRepository.findByName(userDataToUpdate.getRequester());
            if (existingUserToUpdate != null) {
                if (Objects.equals(existingUserToUpdate.getEmail(), userDataToUpdate.getEmail())
                        && Objects.equals(existingUserToUpdate.getName(), userDataToUpdate.getName())
                        && Objects.equals(existingUserToUpdate.getPassword(), userDataToUpdate.getPassword())) {
                    throw new RuntimeException(ExceptionMessages.USER_NO_CHANGES);
                }
                utilService.isUserPermittedForOperation(requester);
                existingUserToUpdate.setName(userDataToUpdate.getName() != null ? userDataToUpdate.getName() : existingUserToUpdate.getName());
                existingUserToUpdate.setEmail(userDataToUpdate.getEmail() != null ? userDataToUpdate.getEmail() : existingUserToUpdate.getEmail());
                existingUserToUpdate.setPassword(userDataToUpdate.getPassword() != null ? userDataToUpdate.getPassword() : existingUserToUpdate.getPassword());

                return userRepository.save(existingUserToUpdate);
            }
            throw new RuntimeException(ExceptionMessages.OBJECT_NOT_EXIST);
        }
        throw new RuntimeException(ExceptionMessages.MISSING_PARAMETER);
    }
    public User deleteUser(String name, UserWebRequest requester) {
        if ( Strings.isEmpty(name) || Strings.isEmpty(requester.getRequester())){
            throw new RuntimeException((ExceptionMessages.MISSING_PARAMETER));
        }
        User requesterUser = userRepository.findByName(requester.getRequester());
        User existingUser = userRepository.findByName(name);
        if (requesterUser != null && existingUser != null){
            utilService.isUserPermittedForOperation(requesterUser);
            userRepository.delete(existingUser);
            return existingUser;
        }
        throw new RuntimeException(ExceptionMessages.OBJECT_NOT_EXIST);
    }
    public User updateUserActivationStatus(UserWebRequest userToUpdate) {
        if ( userToUpdate == null || Strings.isEmpty(userToUpdate.getRequester())){
            throw new RuntimeException((ExceptionMessages.MISSING_PARAMETER));
        }

        User existingUser = userRepository.findByName(userToUpdate.getName());

        if (existingUser != null) {
            utilService.isUserPermittedForOperation(userRepository.findByName(userToUpdate.getRequester()));
            return userRepository.save(existingUser);
        }
        throw new RuntimeException(ExceptionMessages.OBJECT_NOT_EXIST);
    }
    public User updateAdminState(UserWebRequest userToUpdate) {
        if (userToUpdate == null ||Strings.isEmpty(userToUpdate.getRequester()) ){
            throw new RuntimeException((ExceptionMessages.MISSING_PARAMETER));
        }
        User existingUser = userRepository.findByName(userToUpdate.getName());

        utilService.isUserPermittedForOperation(userRepository.findByName(userToUpdate.getRequester()));
        existingUser.setIsAdmin(userToUpdate.getIsAdmin());

        return userRepository.save(existingUser);
    }
}

