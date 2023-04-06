package NGSoft.assignment.Shaytaskmanager.service;


import NGSoft.assignment.Shaytaskmanager.db.*;
import NGSoft.assignment.Shaytaskmanager.utils.Utils;
import NGSoft.assignment.Shaytaskmanager.concrete.Status;
import NGSoft.assignment.Shaytaskmanager.web.TaskWebRequest;
import NGSoft.assignment.Shaytaskmanager.exception.ExceptionMessages;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Data
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ApplicationContext context;

    public List<Task> getAllTasks(String token) {
        return taskRepository.findAll();
    }

    public Task saveTask(TaskWebRequest task) {
        if (task == null) {
            throw new NullPointerException(ExceptionMessages.OBJECT_IS_NULL);
        }
        return taskRepository.save(Utils.buildTaskDBObject(task));
    }

    //TODO: per user
    public Task updateExistingTask(int id, TaskWebRequest task) throws Exception {

        Task existingTask;
        try {
            existingTask = taskRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new Exception(ExceptionMessages.TASK_NOT_EXIST);
        }
        existingTask.setAssignee(task.getAssignee() != null ? task.getAssignee() : existingTask.getAssignee());
        existingTask.setDescription(task.getDescription() != null ? task.getDescription() : existingTask.getDescription());
        existingTask.setTitle(task.getTitle() != null ? task.getTitle() : existingTask.getTitle());
        existingTask.setStatus(Status.getStatusById(task.getStatus()));
        return taskRepository.save(existingTask);
    }

    public Task changeTaskStatus(int id, int newStatus) throws Exception {
        Task existingTask = taskRepository.findById(id).orElseThrow();
        if (newStatus == existingTask.getStatus().getStatus()) {
            throw new Exception(ExceptionMessages.TASK_NEW_STATUS_SAME_OLD_STATUS);
        }
        existingTask.setStatus(Status.getStatusById(newStatus));
        return taskRepository.save(existingTask);
    }

    public Task deleteTask(int id) {
        Task existingTask = taskRepository.findById(id).orElseThrow();
        taskRepository.delete(existingTask);
        return existingTask;
    }
}