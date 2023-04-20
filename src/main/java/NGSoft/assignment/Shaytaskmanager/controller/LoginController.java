package NGSoft.assignment.Shaytaskmanager.controller;

import NGSoft.assignment.Shaytaskmanager.service.LoginService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public String login(Authentication auth){
        return loginService.generateToken(auth);
    }
}
