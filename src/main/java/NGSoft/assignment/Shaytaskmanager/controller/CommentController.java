package NGSoft.assignment.Shaytaskmanager.controller;

import NGSoft.assignment.Shaytaskmanager.db.Comment;
import NGSoft.assignment.Shaytaskmanager.db.Task;
import NGSoft.assignment.Shaytaskmanager.service.CommentService;
import NGSoft.assignment.Shaytaskmanager.service.TaskService;
import NGSoft.assignment.Shaytaskmanager.web.CommentWebRequest;
import NGSoft.assignment.Shaytaskmanager.web.TaskWebRequest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @PostMapping("/add")
    public Comment addCommentToTask(@RequestBody CommentWebRequest comment){
        return commentService.addCommentToTask(comment);
    }

    @GetMapping("/get-all/{user}")
    public List<Comment> retrieveAllCommentsPerUser(@PathVariable String user, @RequestBody CommentWebRequest requester){
        return commentService.getCommentsByUser(user, requester);
    }

}
