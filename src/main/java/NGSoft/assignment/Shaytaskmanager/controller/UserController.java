package NGSoft.assignment.Shaytaskmanager.controller;

import NGSoft.assignment.Shaytaskmanager.db.User;
import NGSoft.assignment.Shaytaskmanager.service.UserService;
import NGSoft.assignment.Shaytaskmanager.web.UserWebRequest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public User addUser(@RequestBody UserWebRequest userToAdd) {
        return userService.addUser(userToAdd);
    }

    /**
     * update user general data: email, name, password
     */
    @PatchMapping("/update/general-data")
    public User updateUserGeneralDetails(@RequestBody UserWebRequest userToUpdate) {
        return userService.updateUserGeneralDetails(userToUpdate);
    }

    @GetMapping("/all")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    /**
     * change user activation status
     * @param userToUpdate
     * @return
     */
    @PatchMapping("/update/activation")
    public User changeUserActivationStatus(@RequestBody UserWebRequest userToUpdate) {
        return userService.updateUserActivationStatus(userToUpdate);
    }

    /**
     * change state of a user: admin or not
     * @param userToUpdate
     * @return
     */
    @PatchMapping("/update/isAdmin")
    public User updateAdminState(@RequestBody UserWebRequest userToUpdate) {
        return userService.updateAdminState(userToUpdate);
    }

    @DeleteMapping("/{name}")
    public User deleteUser(@PathVariable String name, @RequestBody  UserWebRequest requester) throws Exception {
        return userService.deleteUser(name,requester);
    }
}
