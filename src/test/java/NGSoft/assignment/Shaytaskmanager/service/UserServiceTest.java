package NGSoft.assignment.Shaytaskmanager.service;

import NGSoft.assignment.Shaytaskmanager.ShayTaskManagerApplication;
import NGSoft.assignment.Shaytaskmanager.db.User;
import NGSoft.assignment.Shaytaskmanager.exception.OperationNotAllowedException;
import NGSoft.assignment.Shaytaskmanager.web.UserWebRequest;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ShayTaskManagerApplication.class)
class UserServiceTest {
    @Autowired
    UserService userService;

    static UserWebRequest sys1;
    static UserWebRequest noAdminUser;

    @BeforeEach
    void init(){
        addDefaultAdminUser();
    }
    @Test
    void addDefaultAdminUser() {
        UserWebRequest sys1 = getAdminUser();

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
    void addUserNoPermission() {
        UserWebRequest noAdminUser = getANonAdminUser();
        userService.addUser(noAdminUser);
        noAdminUser.setName("new");
        noAdminUser.setRequester("noAdminUser");
        assertThrows(OperationNotAllowedException.class, ()->userService.addUser(noAdminUser));
    }

    @Test
    void getAllUsers() {
        userService.addUser(getANonAdminUser());
        assertEquals(2, userService.getAllUsers().size());
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

    private static UserWebRequest getAdminUser() {

        if (sys1 == null) {
            sys1 = new UserWebRequest();
            sys1.setName("sys1");
            sys1.setEmail("sys1@gmail.com");
            sys1.setIsAdmin(true);
            sys1.setIsActive(true);
            sys1.setPassword("1234");
            sys1.setRequester("SYSTEM");
        }
        return sys1;
    }

    private static UserWebRequest getANonAdminUser() {

        if (noAdminUser == null) {
            noAdminUser = new UserWebRequest();
            noAdminUser.setName("noAdminUser");
            noAdminUser.setEmail("noAdminUser@gmail.com");
            noAdminUser.setIsAdmin(false);
            noAdminUser.setIsActive(true);
            noAdminUser.setPassword("1234");
            noAdminUser.setRequester("sys1");
        }
        return noAdminUser;
    }
}