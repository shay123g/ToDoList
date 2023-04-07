package NGSoft.assignment.Shaytaskmanager.controller;

import NGSoft.assignment.Shaytaskmanager.db.Comment;
import NGSoft.assignment.Shaytaskmanager.db.Task;
import NGSoft.assignment.Shaytaskmanager.service.CommentService;
import NGSoft.assignment.Shaytaskmanager.service.TaskService;
import NGSoft.assignment.Shaytaskmanager.web.CommentWebRequest;
import NGSoft.assignment.Shaytaskmanager.web.TaskWebRequest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;
    @PostMapping("/add")
    public Comment addCommentToTask(@RequestBody CommentWebRequest comment){
        return commentService.addCommentToTask(comment);
    }

}
