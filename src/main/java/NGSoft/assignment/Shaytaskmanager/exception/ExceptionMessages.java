package NGSoft.assignment.Shaytaskmanager.exception;

public final class ExceptionMessages {

    public static final String MISSING_PARAMETER = "Missing parameter in payload";

    public static final String OBJECT_IS_NULL = "Empty payload. You have to provide some parameters";
    public static final String OBJECT_NOT_EXIST = "Object not exist in DB";
    public static final String TASK_NEW_STATUS_SAME_OLD_STATUS = "New task status same as old status, no changes were made";
    public static final String TASK_INVALID_STATUS_FLOW = "Not allowed to move from COMPLETED status to other status except ARCHIVED";
    public static final String TASK_OPERATION_ADD_NOT_ALLOWED = "only ADMIN can add tasks";
    public static final String USER_NO_CHANGES = "No changes have been made since the data in the request and in the DB is the same";
    public static final String USER_OPERATION_NOT_ALLOWED = "only ADMIN can execute this operation";



}
