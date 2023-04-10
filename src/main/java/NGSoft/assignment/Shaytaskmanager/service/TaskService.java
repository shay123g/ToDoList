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
import java.util.stream.Collectors;

@Service
@Data
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ApplicationContext context;

    @Autowired
    private MiscService service;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksByUser(String userName) {
        return taskRepository.findByAssignee(userName).stream().filter(Task::isVisible).collect(Collectors.toList());
    }

    public Task saveTask(TaskWebRequest task) {
        if (task == null) {
            throw new RuntimeException(ExceptionMessages.OBJECT_IS_NULL);
        }
        boolean isAssignorAdmin = context.getBean(UserRepository.class).findByName(task.getAssignor()).getIsAdmin();
        if (!isAssignorAdmin){
            throw new RuntimeException(ExceptionMessages.TASK_OPERATION_ADD_NOT_ALLOWED);
        }

        return taskRepository.save(service.buildTaskDBObject(task));
    }

    /**
     * update general task data: description and title
     * @param id - task id
     * @param task
     * @return
     */
    public Task updateGeneralTaskData(int id, TaskWebRequest task){

        Task existingTask;
        try {
            existingTask = taskRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new RuntimeException(ExceptionMessages.OBJECT_NOT_EXIST);
        }
        boolean isAssignorAdmin = context.getBean(UserRepository.class).findByName(task.getAssignor()).getIsAdmin();
        if (!isAssignorAdmin){
            throw new RuntimeException(ExceptionMessages.TASK_OPERATION_ADD_NOT_ALLOWED);
        }
        existingTask.setDescription(task.getDescription() != null ? task.getDescription() : existingTask.getDescription());
        existingTask.setTitle(task.getTitle() != null ? task.getTitle() : existingTask.getTitle());
        return taskRepository.save(existingTask);
    }

    /**
     * change task status. if the task already in COMPLETE state, it can only move to ARCHIVED, and only user
     * with Admin permission can perform this operation
     * @param id task id
     * @param statusChangeRequest
     * @param requester - the user who perform the operation
     * @return
     */
    public Task changeTaskStatus(int id, StatusChaneRequest statusChangeRequest, String requester) {
        User requesterUser  = context.getBean(UserRepository.class).findByName(requester);
        Task existingTask = taskRepository.findById(id).orElseThrow();
        if (statusChangeRequest.getNewStatus() == existingTask.getStatus().getStatus()) {
            throw new RuntimeException(ExceptionMessages.TASK_NEW_STATUS_SAME_OLD_STATUS);
        }

        if (existingTask.getStatus() == Status.COMPLETED) {
            if (!(Status.getStatusById(statusChangeRequest.getNewStatus()).equals(Status.ARCHIVED))) {
                throw new RuntimeException(ExceptionMessages.TASK_INVALID_STATUS_FLOW);
            }
            else {
                service.isUserPermittedForTaskOperation(requesterUser, existingTask);
                existingTask.setStatus(Status.ARCHIVED);
                existingTask.setVisible(false);
            }
        }
        existingTask.setStatus(Status.getStatusById(statusChangeRequest.getNewStatus()));
        return taskRepository.save(existingTask);
    }

    /**
     * delete task, only if user has admin permission.
     * @param id task id
     * @return
     */
    public Task deleteTask(int id) {
        Task existingTask = taskRepository.findById(id).orElseThrow();
        boolean isAssignorAdmin = context.getBean(UserRepository.class).findByName(existingTask.getAssignor()).getIsAdmin();
        if (!isAssignorAdmin){
            throw new RuntimeException(ExceptionMessages.TASK_OPERATION_ADD_NOT_ALLOWED);
        }
        taskRepository.delete(existingTask);
        return existingTask;
    }

    /**
     * update task assignee
     * @param id task id
     * @param taskToUpdate
     * @param requester  the user who perform the operation
     * @return
     */
    public Task updateTaskAssignee(int id, TaskWebRequest taskToUpdate, String requester) {
        User requesterUser  = context.getBean(UserRepository.class).findByName(requester);
        Task existingTask = taskRepository.findById(id).orElseThrow();

        service.isUserPermittedForTaskOperation(requesterUser, existingTask);

        existingTask.setAssignee(taskToUpdate.getAssignee());
        return taskRepository.save(existingTask);
    }
}