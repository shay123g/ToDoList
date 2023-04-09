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

    @GetMapping("/get-all")
    public List<Task> retrieveAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/get-all/{user}")
    public List<Task> retrieveAllTasks(@PathVariable String user){
        return taskService.getTasksByUser(user);
    }
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable int id, @RequestBody TaskWebRequest taskToUpdate) throws Exception {
        return taskService.updateExistingTask(id, taskToUpdate);
    }

    /**
     * update task status. in case the input status is not according to enum  {@link NGSoft.assignment.Shaytaskmanager.concrete.Status}
     * return default STATUS.UNKNOWN
     * @param id the task id whom status need to be changed
     * @param statusRequest new status
     * @return
     * @throws Exception
     */
    @PutMapping("/status-update/{id}")
    public Task updateTaskStatus(@PathVariable int id, @RequestBody StatusChaneRequest statusRequest) throws Exception {
        return taskService.changeTaskStatus(id, statusRequest);
    }

    @DeleteMapping("/{id}")
    public Task deleteTask(@PathVariable int id) throws Exception {
        return taskService.deleteTask(id);
    }
}
