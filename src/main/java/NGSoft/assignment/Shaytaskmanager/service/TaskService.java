package NGSoft.assignment.Shaytaskmanager.service;


import NGSoft.assignment.Shaytaskmanager.concrete.StatusChaneRequest;
import NGSoft.assignment.Shaytaskmanager.db.*;
import NGSoft.assignment.Shaytaskmanager.concrete.Status;
import NGSoft.assignment.Shaytaskmanager.exception.*;
import NGSoft.assignment.Shaytaskmanager.web.TaskWebRequest;
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
    private MiscService utilService;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    public List<Task> getTasksByUser(String userName, TaskWebRequest requester) {
        if (context.getBean(UserRepository.class).findByName(userName) != null && userName.equals(requester.getRequester())){
            return taskRepository.findByAssignee(userName).stream().filter(Task::isVisible).collect(Collectors.toList());
        }
        else{
            throw new RuntimeException(ExceptionMessages.TASK_VIEW_NOT_ALLOWED);
        }
    }
    public Task saveTask(TaskWebRequest task) {
        if (task == null) {
            throw new MissingParameterException(ExceptionMessages.MISSING_PARAMETER);
        }
        utilService.isUserPermittedForOperation(context.getBean(UserRepository.class).findByName(task.getRequester()));

        return taskRepository.save(utilService.buildTaskDBObject(task));
    }
    public Task updateGeneralTaskData(int id, TaskWebRequest task) {
        if (task != null) {
            Task existingTask;
            User requester = context.getBean(UserRepository.class).findByName(task.getRequester());
            try {
                existingTask = taskRepository.findById(id).orElseThrow();
            } catch (NoSuchElementException e) {
                throw new ObjectNotFoundException(ExceptionMessages.OBJECT_NOT_EXIST);
            }
            utilService.isUserPermittedForOperation(requester);

            existingTask.setDescription(task.getDescription() != null ? task.getDescription() : existingTask.getDescription());
            existingTask.setTitle(task.getTitle() != null ? task.getTitle() : existingTask.getTitle());
            return taskRepository.save(existingTask);
        }
        else{
            throw new MissingParameterException(ExceptionMessages.MISSING_PARAMETER);
        }
    }

    /**
     * change task status. if the task already in COMPLETE state, it can only move to ARCHIVED, and only user
     * with Admin permission can perform this operation
     */
    public Task changeTaskStatus(int id, StatusChaneRequest statusChangeRequest) {
        if (statusChangeRequest != null) {
            User requesterUser = context.getBean(UserRepository.class).findByName(statusChangeRequest.getRequester());
            Task existingTask = taskRepository.findById(id).orElseThrow();
            if (requesterUser == null){
                throw new ObjectNotFoundException(ExceptionMessages.OBJECT_NOT_EXIST);
            }
            if (statusChangeRequest.getNewStatus() == existingTask.getStatus().getStatus()) {
                throw new SameStatusException(ExceptionMessages.TASK_NEW_STATUS_SAME_OLD_STATUS);
            }
            boolean isStatusFlowValid = checkStatusFlowValidity(statusChangeRequest, existingTask.getStatus());
            if (!isStatusFlowValid) {
                throw new InvalidStatusFlowException(ExceptionMessages.TASK_INVALID_STATUS_FLOW);
            }
            if (Status.getStatusById(statusChangeRequest.getNewStatus()) == Status.ARCHIVED) {
                handleTransitionToArchive(requesterUser, existingTask);
            } else if (existingTask.getAssignee().equals(requesterUser.getName()) || requesterUser.getIsAdmin()) {
                existingTask.setStatus(Status.getStatusById(statusChangeRequest.getNewStatus()));
            } else {
                throw new CommentTaskNotAllowedException(ExceptionMessages.COMMENT_TASK_OPERATION_NOT_ALLOWED);
            }
            return taskRepository.save(existingTask);
        }
        else{
            throw new MissingParameterException(ExceptionMessages.MISSING_PARAMETER);
        }
    }
    /**
     * delete task, only if user has admin permission.
     */
    public Task deleteTask(int id, TaskWebRequest requester) {
        Task existingTask = taskRepository.findById(id).orElseThrow();
        utilService.isUserPermittedForOperation(context.getBean(UserRepository.class).findByName(requester.getRequester()));
        taskRepository.delete(existingTask);
        return existingTask;
    }

    /**
     * update task assignee
     */
    public Task updateTaskAssignee(int id, TaskWebRequest taskToUpdate) {
        User requesterUser = context.getBean(UserRepository.class).findByName(taskToUpdate.getRequester());
        Task existingTask = taskRepository.findById(id).orElseThrow();

        if (requesterUser == null){
            throw new ObjectNotFoundException(ExceptionMessages.OBJECT_NOT_EXIST);
        }
        utilService.isUserPermittedForOperation(requesterUser);

        existingTask.setAssignee(taskToUpdate.getAssignee());
        return taskRepository.save(existingTask);
    }
    private void handleTransitionToArchive(User requesterUser, Task existingTask) {
        utilService.isUserPermittedForOperation(requesterUser);
        existingTask.setStatus(Status.ARCHIVED);
        existingTask.setVisible(false);
    }
    private boolean checkStatusFlowValidity(StatusChaneRequest newStatus, Status existingStatus) {
        return switch (existingStatus) {
            case PENDING -> Status.getStatusById(newStatus.getNewStatus()) == Status.COMPLETED;
            case COMPLETED -> Status.getStatusById(newStatus.getNewStatus()) == Status.ARCHIVED;
            default -> false;
        };
    }
}