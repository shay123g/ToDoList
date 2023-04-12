package NGSoft.assignment.Shaytaskmanager.service;


import NGSoft.assignment.Shaytaskmanager.db.*;
import NGSoft.assignment.Shaytaskmanager.exception.CommentTaskNotAllowedException;
import NGSoft.assignment.Shaytaskmanager.exception.ExceptionMessages;
import NGSoft.assignment.Shaytaskmanager.exception.ObjectNotFoundException;
import NGSoft.assignment.Shaytaskmanager.web.CommentWebRequest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class CommentService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ApplicationContext context;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private MiscService utilService;

    public Comment addCommentToTask(CommentWebRequest comment) {
        User requesterUSer = context.getBean(UserRepository.class).findByName(comment.getRequester());

        Task requestedUserTask = taskRepository.findByAssignee(comment.getUserId()).stream().filter(task -> task.getID() == comment.getTaskId()).toList().get(0);
        User taskAssignee = context.getBean(UserRepository.class).findByName(comment.getUserId());
        if (comment.getRequester().equals(requestedUserTask.getAssignee()) || requesterUSer.getIsAdmin()) {
            Pair<Task, User> dataForComment = Pair.of(requestedUserTask, taskAssignee);
            return commentRepository.save(utilService.buildCommentDBObject(dataForComment, comment.getComment()));
        }
        throw new CommentTaskNotAllowedException(ExceptionMessages.COMMENT_TASK_OPERATION_NOT_ALLOWED);
    }
    public List<Comment> getCommentsByUser(String userName, CommentWebRequest requester) {
        User requsterUser = context.getBean(UserRepository.class).findByName(requester.getRequester());
        User userForComments = context.getBean(UserRepository.class).findByName(userName);

        if (userForComments != null  && requsterUser != null){
            return new ArrayList<>(commentRepository.findByUserId(userForComments));
        }
        else{
            throw new ObjectNotFoundException(ExceptionMessages.OBJECT_NOT_EXIST);
           }
    }
}


