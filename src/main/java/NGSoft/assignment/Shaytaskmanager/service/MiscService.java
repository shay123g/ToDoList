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
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;


@Component
public class MiscService {

    @Autowired
    public UserRepository userRepo;

    @Autowired
    public ApplicationContext context;
    public Task buildTaskDBObject(TaskWebRequest taskRequest) {
        return Task.builder()
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .status(Status.getStatusById(taskRequest.getStatus()))
                .assignee(taskRequest.getAssignee())
                .assignor(taskRequest.getAssignor())
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
                .creator(userRequest.getCreator() == null ? UserWebRequest.DEFAULT_USER : userRequest.getCreator())
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

    public void isUserPermittedForUserOperation(User currentUser){

        User creator =  context.getBean(UserRepository.class).findByName(currentUser.getName());
        boolean permitted = creator != null && (creator.getIsAdmin() || creator.getName().equals(UserWebRequest.DEFAULT_USER));
        if (!permitted){
            throw new RuntimeException(ExceptionMessages.USER_OPERATION_NOT_ALLOWED);
        }
    }

    public void isUserPermittedForTaskOperation(User currentUser, Task taskToCheck) {
        if (currentUser != null) {
            boolean isAssignorAdmin = userRepo.findByName(taskToCheck.getAssignor()).getIsAdmin();
            if (!isAssignorAdmin) {
                throw new RuntimeException(ExceptionMessages.TASK_OPERATION_ADD_NOT_ALLOWED);
            }
        }
    }
}
