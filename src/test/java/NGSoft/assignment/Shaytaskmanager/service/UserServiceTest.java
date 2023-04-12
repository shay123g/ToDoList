package NGSoft.assignment.Shaytaskmanager.service;

import NGSoft.assignment.Shaytaskmanager.db.User;
import NGSoft.assignment.Shaytaskmanager.db.UserRepository;
import NGSoft.assignment.Shaytaskmanager.web.UserWebRequest;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserRepository repository;
    @Autowired
    UserService userService;

    @Autowired
    public UserServiceTest(UserRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }




    @Test
    void addDefaultUser() {
        UserWebRequest sys1 = new UserWebRequest();
        sys1.setName("sys1");
        sys1.setEmail("sys1@gmail.com");
        sys1.setIsAdmin(true);
        sys1.setIsActive(true);
        sys1.setPassword("1234");
        sys1.setRequester("SYSTEM");

        User systemDB = User.builder().email("sys1@gmail.com")
                .name("sys1")
                .isAdmin(true)
                .isActive(true)
                .password("1234")
                .requester("SYSTEM")
                .ID(1)
                .build();

        User result = userService.addUser(sys1);
        assertEquals(systemDB,result);

    }

    @Test
    void getAllUsers() {
    }

    @Test
    void updateUserGeneralDetails() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUserActivationStatus() {
    }

    @Test
    void updateAdminState() {
    }
}