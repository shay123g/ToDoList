package NGSoft.assignment.Shaytaskmanager.service;


import NGSoft.assignment.Shaytaskmanager.db.*;
import NGSoft.assignment.Shaytaskmanager.web.CommentWebRequest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
@Data
public class CommentService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ApplicationContext context;

    @Autowired
    CommentRepository commentRepository;
    @Autowired MiscService service;

    /**
     * assumption: each user have one comment (simplicity)
     *
     * @param taskId
     * @param user
     * @param
     * @return
     */
    public Comment addCommentToTask(CommentWebRequest comment) {
        Task requestedUserTask = taskRepository.findByAssignee(comment.getUser()).stream().filter(task -> task.getID() == comment.getTaskId()).toList().get(0);
        User userFromDB = context.getBean(UserRepository.class).findByName(comment.getUser());
        Pair<Task, User> dataForComment = Pair.of(requestedUserTask, userFromDB);
        return commentRepository.save(service.buildCommentDBObject(dataForComment, comment.getComment()));
    }
}


