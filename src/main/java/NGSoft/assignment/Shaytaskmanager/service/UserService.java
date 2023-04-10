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
    UserRepository userRepository;
    @Autowired
    MiscService service;

    public User addUser(UserWebRequest user) {
        if (!user.getCreator().equals("SYSTEM")) {
            service.isUserPermittedForUserOperation(userRepository.findByName(user.getCreator()));
        }
        return userRepository.save(service.buildUserDBObject(user));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * update user general data: email, name, password
     * @param userDataToUpdate
     * @return
     */
    public User updateUserGeneralDetails(UserWebRequest userDataToUpdate) {
        User existingUserToUpdate = userRepository.findByName(userDataToUpdate.getName());
        if (existingUserToUpdate != null) {
            if (Objects.equals(existingUserToUpdate.getEmail(), userDataToUpdate.getEmail())
                    && Objects.equals(existingUserToUpdate.getName(), userDataToUpdate.getName())
                    && Objects.equals(existingUserToUpdate.getPassword(), userDataToUpdate.getPassword())) {
                throw new RuntimeException(ExceptionMessages.USER_NO_CHANGES);
            }
            existingUserToUpdate.setName(userDataToUpdate.getName() != null ? userDataToUpdate.getName() : existingUserToUpdate.getName());
            existingUserToUpdate.setEmail(userDataToUpdate.getEmail() != null ? userDataToUpdate.getEmail() : existingUserToUpdate.getEmail());
            existingUserToUpdate.setPassword(userDataToUpdate.getPassword() != null ? userDataToUpdate.getPassword() : existingUserToUpdate.getPassword());

            return userRepository.save(existingUserToUpdate);
        }
        throw new RuntimeException(ExceptionMessages.MISSING_PARAMETER);
    }

    /**
     * delete user, only if the executor user is permitted to do so
     * @param name
     * @param requester
     * @return
     */
    public User deleteUser(String name, UserWebRequest requester) {
        User requesterUser = userRepository.findByName(requester.getRequester());
        User existingUser = userRepository.findByName(name);
        if (requesterUser != null){
            service.isUserPermittedForUserOperation(requesterUser);
            userRepository.delete(existingUser);
            return existingUser;
        }
        throw new RuntimeException(ExceptionMessages.USER_NO_CHANGES);
    }

    /**
     * change user activation status
     * @param userToUpdate
     * @return
     */
    public User updateUserActivationStatus(UserWebRequest userToUpdate) {
        if (Strings.isEmpty(userToUpdate.getRequester()) || userToUpdate == null){
            throw new RuntimeException((ExceptionMessages.MISSING_PARAMETER));
        }
        User existingUser = userRepository.findByName(userToUpdate.getName());

        boolean isActiveChange = userToUpdate.getIsActive() != null && existingUser != null && existingUser.getIsActive() != userToUpdate.getIsActive();
        service.isUserPermittedForUserOperation(userRepository.findByName(userToUpdate.getRequester()));

        if (isActiveChange){
            existingUser.setIsActive(userToUpdate.getIsActive());
        }
        return userRepository.save(existingUser);
    }

    /**
     * change state of a user: admin or not
     * @param userToUpdate
     * @return
     */
    public User updateAdminState(UserWebRequest userToUpdate) {
        if (Strings.isEmpty(userToUpdate.getRequester()) || userToUpdate == null){
            throw new RuntimeException((ExceptionMessages.MISSING_PARAMETER));
        }
        User existingUser = userRepository.findByName(userToUpdate.getName());

        boolean isAdminChange = userToUpdate.getIsAdmin() != null && existingUser != null && existingUser.getIsAdmin() != userToUpdate.getIsAdmin();
        service.isUserPermittedForUserOperation(userRepository.findByName(userToUpdate.getRequester()));
        if (isAdminChange){
            existingUser.setIsAdmin(userToUpdate.getIsAdmin());
        }
        return userRepository.save(existingUser);
    }
}

