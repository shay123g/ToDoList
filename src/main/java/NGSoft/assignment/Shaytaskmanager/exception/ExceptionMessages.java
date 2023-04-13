package NGSoft.assignment.Shaytaskmanager.exception;

public final class ExceptionMessages {

    public static final String MISSING_PARAMETER = "Missing parameter in payload";

    public static final String OPERATION_NOT_ALLOWED = "the requester user not authorize to execute the operation";

    public static final String OBJECT_NOT_EXIST = "Object not exist in DB";
    public static final String COMMENT_TASK_OPERATION_NOT_ALLOWED = "only ADMIN or the task's assignee  can add comment to task";
    public static final String TASK_VIEW_NOT_ALLOWED = "user can view only his tasks";
    public static final String TASK_NEW_STATUS_SAME_OLD_STATUS = "New task status same as old status, no changes were made";
    public static final String TASK_INVALID_STATUS_FLOW = "Illegal state transition";
    public static final String USER_NO_CHANGES = "No changes have been made. check your payload parameters";




}
