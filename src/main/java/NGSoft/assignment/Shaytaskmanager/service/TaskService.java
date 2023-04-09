package NGSoft.assignment.Shaytaskmanager.service;


import NGSoft.assignment.Shaytaskmanager.concrete.StatusChaneRequest;
import NGSoft.assignment.Shaytaskmanager.db.*;
import NGSoft.assignment.Shaytaskmanager.concrete.Status;
import NGSoft.assignment.Shaytaskmanager.web.TaskWebRequest;
import NGSoft.assignment.Shaytaskmanager.exception.ExceptionMessages;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Data
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ApplicationContext context;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksByUser(String userName) {
        return taskRepository.findByAssignee(userName);
    }

    public Task saveTask(TaskWebRequest task) {
        if (task == null) {
            throw new RuntimeException(ExceptionMessages.OBJECT_IS_NULL);
        }
        boolean isAssignorAdmin = context.getBean(UserRepository.class).findByName(task.getAssignor()).getIsAdmin();
        if (!isAssignorAdmin){
            throw new RuntimeException(ExceptionMessages.TASK_OPERATION_ADD_NOT_ALLOWED);
        }
        return taskRepository.save(MiscService.buildTaskDBObject(task));
    }

    public Task updateExistingTask(int id, TaskWebRequest task){

        Task existingTask;
        try {
            existingTask = taskRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new RuntimeException(ExceptionMessages.TASK_NOT_EXIST);
        }
        existingTask.setAssignee(task.getAssignee() != null ? task.getAssignee() : existingTask.getAssignee());
        existingTask.setDescription(task.getDescription() != null ? task.getDescription() : existingTask.getDescription());
        existingTask.setTitle(task.getTitle() != null ? task.getTitle() : existingTask.getTitle());
        existingTask.setStatus(Status.getStatusById(task.getStatus()));
        return taskRepository.save(existingTask);
    }

    public Task changeTaskStatus(int id, StatusChaneRequest statusChangeRequest) {
        Task existingTask = taskRepository.findById(id).orElseThrow();
        if (statusChangeRequest.getNewStatus() == existingTask.getStatus().getStatus()) {
            throw new RuntimeException(ExceptionMessages.TASK_NEW_STATUS_SAME_OLD_STATUS);
        }

        if (existingTask.getStatus() == Status.COMPLETED) {
            if (!(Status.getStatusById(statusChangeRequest.getNewStatus()).equals(Status.ARCHIVED))) {
                throw new RuntimeException(ExceptionMessages.TASK_INVALID_STATUS_FLOW);
            }
        }
        existingTask.setStatus(Status.getStatusById(statusChangeRequest.getNewStatus()));
        return taskRepository.save(existingTask);
    }

    public Task deleteTask(int id) {
        Task existingTask = taskRepository.findById(id).orElseThrow();
        taskRepository.delete(existingTask);
        return existingTask;
    }
}