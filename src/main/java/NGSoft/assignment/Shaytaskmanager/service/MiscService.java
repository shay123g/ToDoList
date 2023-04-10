package NGSoft.assignment.Shaytaskmanager.service;

import NGSoft.assignment.Shaytaskmanager.concrete.Status;
import NGSoft.assignment.Shaytaskmanager.db.Comment;
import NGSoft.assignment.Shaytaskmanager.db.Task;
import NGSoft.assignment.Shaytaskmanager.db.User;
import NGSoft.assignment.Shaytaskmanager.db.UserRepository;
import NGSoft.assignment.Shaytaskmanager.exception.ExceptionMessages;
import NGSoft.assignment.Shaytaskmanager.web.TaskWebRequest;
import NGSoft.assignment.Shaytaskmanager.web.UserWebRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import java.util.Calendar;


@Component
public class MiscService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ApplicationContext context;

    public Task buildTaskDBObject(TaskWebRequest taskRequest) {
        return Task.builder()
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .status(Status.PENDING)
                .assignee(taskRequest.getAssignee())
                .isVisible(true)
                .build();
    }
    public User buildUserDBObject(UserWebRequest userRequest) {
        return User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .isAdmin(userRequest.getIsAdmin())
                .isActive(userRequest.getIsActive())
                .password(userRequest.getPassword())
                .requester(userRequest.getRequester() == null ? UserWebRequest.DEFAULT_USER : userRequest.getRequester())
                .build();
    }
    public Comment buildCommentDBObject(Pair<Task, User> metaData, String commentText) {
        return Comment.builder()
                .date(new Timestamp(Calendar.getInstance().getTimeInMillis()))
                .taskId(metaData.getFirst())
                .comment(commentText)
                .userId(metaData.getSecond())
                .build();
    }
    public void isUserPermittedForOperation(User user){
        User userFromDB =  context.getBean(UserRepository.class).findByName(user.getName());
        boolean permitted = userFromDB != null && (userFromDB.getIsAdmin() || userFromDB.getName().equals(UserWebRequest.DEFAULT_USER));
        if (!permitted){
            throw new RuntimeException(ExceptionMessages.OPERATION_NOT_ALLOWED);
        }
    }
}
