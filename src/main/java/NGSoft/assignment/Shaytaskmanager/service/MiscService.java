package NGSoft.assignment.Shaytaskmanager.service;

import NGSoft.assignment.Shaytaskmanager.concrete.Status;
import NGSoft.assignment.Shaytaskmanager.db.Comment;
import NGSoft.assignment.Shaytaskmanager.db.Task;
import NGSoft.assignment.Shaytaskmanager.db.User;
import NGSoft.assignment.Shaytaskmanager.web.TaskWebRequest;
import NGSoft.assignment.Shaytaskmanager.web.UserWebRequest;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;

@Service
public class MiscService {

//    @Autowired
//    private static UserRepository userRepository;
    public static Task buildTaskDBObject(TaskWebRequest taskRequest) {
        return Task.builder()
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .status(Status.getStatusById(taskRequest.getStatus()))
                .assignee(taskRequest.getAssignee())
                .build();
    }

    public static User buildUserDBObject(UserWebRequest userRequest) {
//        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .isAdmin(userRequest.getIsAdmin())
                .isActive(userRequest.getIsActive())
                .password(userRequest.getPassword())
             //   .password(encodePassword(userRequest.getPassword(), encoder))
                .build();
    }

    public static Comment buildCommentDBObject(Pair<Task, User> metaData, String commentText) {
        return Comment.builder()
                .date(new Timestamp(Calendar.getInstance().getTimeInMillis()))
                .taskId(metaData.getFirst())
                .comment(commentText)
                .userId(metaData.getSecond())
                .build();
    }
//    private static String encodePassword(String plainTextPass, BCryptPasswordEncoder encoder){
//        return encoder.encode(plainTextPass);
//    }
}
