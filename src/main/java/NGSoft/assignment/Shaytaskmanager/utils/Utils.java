package NGSoft.assignment.Shaytaskmanager.utils;

import NGSoft.assignment.Shaytaskmanager.concrete.Status;
import NGSoft.assignment.Shaytaskmanager.db.Task;
import NGSoft.assignment.Shaytaskmanager.db.User;
import NGSoft.assignment.Shaytaskmanager.db.UserRepository;
import NGSoft.assignment.Shaytaskmanager.web.TaskWebRequest;
import NGSoft.assignment.Shaytaskmanager.web.UserWebRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Utils {

    @Autowired
    private static UserRepository userRepository;


    public static Task buildTaskDBObject(TaskWebRequest taskRequest){
        return Task.builder()
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .status(Status.getStatusById(taskRequest.getStatus()))
                .assignee(taskRequest.getAssignee())
                .build();
    }
    public static User buildUserDBObject(UserWebRequest userRequest){
        return User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .isAdmin(userRequest.getIsAdmin())
                .isActive(userRequest.getIsActive())
                .password(userRequest.getPassword())
                .build();

    }
}
