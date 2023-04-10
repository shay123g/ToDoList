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

    @PatchMapping("/update/general")
    public User updateUserGeneralDetails(@RequestBody UserWebRequest userToUpdate) {
        return userService.updateUserGeneralDetails(userToUpdate);
    }

    @GetMapping("/all")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PatchMapping("/update/activation")
    public User updateActivationStatus(@RequestBody UserWebRequest userToUpdate) {
        return userService.updateUserActivationStatus(userToUpdate);
    }

    @PatchMapping("/update/isAdmin")
    public User updateAdminState(@RequestBody UserWebRequest userToUpdate) {
        return userService.updateAdminState(userToUpdate);
    }

    /**
     * assumption: for simplicity purposes, each user name is unique
     * @param name
     * @return
     * @throws Exception
     */
    @DeleteMapping("/{name}")
    public User deleteUser(@PathVariable String name, @RequestBody  UserWebRequest requester) throws Exception {
        return userService.deleteUser(name,requester);
    }
}
