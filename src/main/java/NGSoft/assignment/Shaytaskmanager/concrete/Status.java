package NGSoft.assignment.Shaytaskmanager.concrete;

import lombok.Getter;

import java.util.Map;

public enum Status  {
    PENDING(1),
    COMPLETED(2),
    ARCHIVED(3),
    UNKNOWN(4);

    @Getter
    private int status;

    private static final Map<Integer,Status> statusByIdMap = Map.of(
            1, PENDING,
            2,COMPLETED,
            3,ARCHIVED);

    Status(int status) {
        this.status = status;
    }

    public static Status getStatusById(int id){
        return  statusByIdMap.getOrDefault(id,UNKNOWN);
    }
}
