package NGSoft.assignment.Shaytaskmanager.service;


import NGSoft.assignment.Shaytaskmanager.concrete.Status;
import NGSoft.assignment.Shaytaskmanager.db.*;
import NGSoft.assignment.Shaytaskmanager.exception.ExceptionMessages;
import NGSoft.assignment.Shaytaskmanager.utils.Utils;
import NGSoft.assignment.Shaytaskmanager.web.TaskWebRequest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Data
public class CommentService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ApplicationContext context;

    @Autowired
    CommentRepository commentRepository;

    /**
     * assumption: each user have one comment (simplicity)
     *
     * @param taskId
     * @param user
     * @param
     * @return
     */
    public Comment addCommentToTask(Integer taskId, String user, String commentText) {
        Task requestedUserTask = taskRepository.findByAssignee(user).stream().filter(task -> task.getID() == taskId).toList().get(0);
        User userFromDB = context.getBean(UserRepository.class).findByName(user);
        Pair<Task, User> dataForComment = Pair.of(requestedUserTask, userFromDB);
        return commentRepository.save(Utils.buildCommentDBObject(dataForComment, commentText));
    }
}


