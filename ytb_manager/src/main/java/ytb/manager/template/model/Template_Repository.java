package ytb.manager.template.model;

import ytb.common.context.model.Ytb_Model;

import java.util.Date;

/**
 * 模板仓库
 */
public class Template_Repository extends Ytb_Model {

    private int repositoryId;

    private int workJobType;

    private String no;

    private String name;

    private int orderNo;

    private int state;

    private int createBy;

    private Date createTime;

    private String uuid;

    private int preTestOk;
    private Date preTestTime;
    private int projectTestOk;
    private Date projectTestTime;

    public Template_Repository() {
    }

    public int getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(int repositoryId) {
        this.repositoryId = repositoryId;
    }

    public int getWorkJobType() {
        return workJobType;
    }

    public void setWorkJobType(int workJobType) {
        this.workJobType = workJobType;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getPreTestOk() {
        return preTestOk;
    }

    public void setPreTestOk(int preTestOk) {
        this.preTestOk = preTestOk;
    }

    public Date getPreTestTime() {
        return preTestTime;
    }

    public void setPreTestTime(Date preTestTime) {
        this.preTestTime = preTestTime;
    }

    public int getProjectTestOk() {
        return projectTestOk;
    }

    public void setProjectTestOk(int projectTestOk) {
        this.projectTestOk = projectTestOk;
    }

    public Date getProjectTestTime() {
        return projectTestTime;
    }

    public void setProjectTestTime(Date projectTestTime) {
        this.projectTestTime = projectTestTime;
    }
}
