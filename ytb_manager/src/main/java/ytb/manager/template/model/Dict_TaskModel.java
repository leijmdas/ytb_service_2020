package ytb.manager.template.model;


public class Dict_TaskModel {

    private int taskId;
    private int workJobId;
    private String taskName;
    private String taskNameAlias;
    private int docType;
    private int isDefault;
    private int isOptional;
    private int orderNo;

    private String workJobName;

    public Dict_TaskModel() {

    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getWorkJobId() {
        return workJobId;
    }

    public void setWorkJobId(int workJobId) {
        this.workJobId = workJobId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskNameAlias() {
        return taskNameAlias;
    }

    public void setTaskNameAlias(String taskNameAlias) {
        this.taskNameAlias = taskNameAlias;
    }

    public int getDocType() {
        return docType;
    }

    public void setDocType(int docType) {
        this.docType = docType;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public int getIsOptional() {
        return isOptional;
    }

    public void setIsOptional(int isOptional) {
        this.isOptional = isOptional;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public String getWorkJobName() {
        return workJobName;
    }

    public void setWorkJobName(String workJobName) {
        this.workJobName = workJobName;
    }
}
