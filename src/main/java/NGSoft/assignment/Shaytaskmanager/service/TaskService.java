package NGSoft.assignment.Shaytaskmanager.service;


import NGSoft.assignment.Shaytaskmanager.db.TaskRepository;
import NGSoft.assignment.Shaytaskmanager.utils.Utils;
import NGSoft.assignment.Shaytaskmanager.concrete.Status;
import NGSoft.assignment.Shaytaskmanager.db.Task;
import NGSoft.assignment.Shaytaskmanager.web.TaskWebRequest;
import NGSoft.assignment.Shaytaskmanager.exception.ExceptionMessages;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Data
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks(String token){
        return taskRepository.findAll();
    }

    public Task saveTask(TaskWebRequest task){
        if (task == null){
            throw new NullPointerException(ExceptionMessages.OBJECT_IS_NULL);
        }
            return taskRepository.save(Utils.buildTaskDBObject(task));
    }

    //TODO: per user
    public Task updateExistingTask(int id, TaskWebRequest task) throws Exception {

        Task existingTask;
        try{
            existingTask = taskRepository.findById(String.valueOf(id)).orElseThrow();
        }
        catch (NoSuchElementException e){
            throw new Exception(ExceptionMessages.TASK_NOT_EXIST);
        }
        existingTask.setAssignee(task.getAssignee());
        existingTask.setDescription(task.getDescription());
        existingTask.setTitle(task.getTitle());
        existingTask.setStatus(Status.getStatusById(task.getStatus()));
        return taskRepository.save(existingTask);
    }
    public Task changeTaskStatus(int id, int newStatus) throws Exception {
        Task existingTask = taskRepository.findById(String.valueOf(id)).orElseThrow();
        if (newStatus == existingTask.getStatus().getStatus()){
            throw new Exception(ExceptionMessages.TASK_NEW_STATUS_SAME_OLD_STATUS);
        }
        existingTask.setStatus(Status.getStatusById(newStatus));
        return taskRepository.save(existingTask);
    }

    public Task deleteTask(int id) {
        Task existingTask = taskRepository.findById(String.valueOf(id)).orElseThrow();
        taskRepository.delete(existingTask);
        return existingTask;
    }
}

