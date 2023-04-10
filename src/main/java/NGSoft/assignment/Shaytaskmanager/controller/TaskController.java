package NGSoft.assignment.Shaytaskmanager.controller;

import NGSoft.assignment.Shaytaskmanager.concrete.StatusChaneRequest;
import NGSoft.assignment.Shaytaskmanager.db.Task;
import NGSoft.assignment.Shaytaskmanager.web.TaskWebRequest;
import NGSoft.assignment.Shaytaskmanager.service.TaskService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Data
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private final TaskService taskService;

    @PostMapping("/add")
    public Task addTask(@RequestBody TaskWebRequest taskToSave) throws Exception {
        return taskService.saveTask(taskToSave);
    }

    /**
     * for testing purposes
     */
    @GetMapping("/get-all")
    public List<Task> retrieveAllTasks(){
        return taskService.getAllTasks();
    }

    /**
     * retrieve only the visible tasks for given user
     */
    @GetMapping("/get-all/{user}")
    public List<Task> retrieveAllTasksPerUser(@PathVariable String user, @RequestBody TaskWebRequest requester){
        return taskService.getTasksByUser(user, requester);
    }

    /**
     * update general task data: description and title
     * @return
     */
    @PatchMapping("/general/{id}")
    public Task updateGeneralTaskData(@PathVariable int id, @RequestBody TaskWebRequest taskToUpdate) throws Exception {
        return taskService.updateGeneralTaskData(id, taskToUpdate);
    }

    @PatchMapping("/assignee-update/{id}")
    public Task updateAssignee(@PathVariable int id, @RequestBody TaskWebRequest taskToUpdate) throws Exception {
        return taskService.updateTaskAssignee(id, taskToUpdate);
    }

    /**
     * update task status. in case the input status is not according to enum  {@link NGSoft.assignment.Shaytaskmanager.concrete.Status}
     * return default STATUS.UNKNOWN
     * @param id the task id whom status need to be changed
     * @param statusRequest new status
     * @return
     * @throws Exception
     */
    @PatchMapping("/status-update/{id}")
    public Task updateTaskStatus(@PathVariable int id, @RequestBody StatusChaneRequest statusRequest) throws Exception {
        return taskService.changeTaskStatus(id, statusRequest);
    }

    @DeleteMapping("/{id}")
    public Task deleteTask(@PathVariable int id, @RequestBody TaskWebRequest requester) throws Exception {
        return taskService.deleteTask(id, requester);
    }
}
