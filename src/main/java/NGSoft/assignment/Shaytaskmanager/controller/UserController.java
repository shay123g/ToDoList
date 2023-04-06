package NGSoft.assignment.Shaytaskmanager.controller;

import NGSoft.assignment.Shaytaskmanager.db.User;
import NGSoft.assignment.Shaytaskmanager.service.UserService;
import NGSoft.assignment.Shaytaskmanager.web.UserWebRequest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    public User updateUserDetails(@PathVariable int id, UserWebRequest userToUpdate) throws Exception {
        return userService.updateUser(id, userToUpdate);
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable int id) throws Exception {
        return userService.deleteUser(id);
    }
}
