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
    public User updateUserDetails(@PathVariable int id, @RequestBody UserWebRequest userToUpdate) throws Exception {
        return userService.updateUser(id, userToUpdate);
    }

    /**
     * assumption: for simplicity purposes, each user name is unique
     * @param name
     * @return
     * @throws Exception
     */
    @DeleteMapping("/{name}")
    public User deleteUser(@PathVariable String name) throws Exception {
        return userService.deleteUser(name);
    }
}
