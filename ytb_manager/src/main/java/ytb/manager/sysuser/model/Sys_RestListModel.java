package ytb.manager.sysuser.model;

import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;

/**
 * Package: ytb.manager.sysuser.model
 * Author: ZCS
 * Date: Created in 2018/8/22 11:03
 */
public class Sys_RestListModel implements Comparable<Sys_RestListModel> {

    private int restId = 0;
    private String model = "";
    private String url = "";
    private String cmdType = "";
    private String cmd = "";
    private int parentId = 0;
    private int orderNum = 0;
    private int createBy = 1;
    private Date createTime = new Date();
    private boolean isSelect = false;//1 选上  0：未选上
    private String restName = "";
    private String nameRemark = "";

    public int getSystemType() {
        return systemType;
    }

    public void setSystemType(int systemType) {
        this.systemType = systemType;
    }

    private int systemType = 1;
    private String memo = "";
    private List<Sys_RestListModel> children = Lists.newArrayList();



    //接口请求的样例
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public boolean getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(boolean select) {
        isSelect = select;
    }

    public boolean equals(Object arg0){
        if(!(arg0 instanceof Sys_RestListModel)){
            return false;
        }
        Sys_RestListModel restListModel = (Sys_RestListModel)arg0;

        return restListModel.restId==this.restId;

    }
    //覆盖Object里的hashCode方法
    public int hashCode() {
        return restId;//返回名字的哈希码。
    }

    public List<Sys_RestListModel> getChildren() {
        return children;
    }

    public void setChildren(List<Sys_RestListModel> children) {
        this.children = children;
    }

    public int getRestId() {
        return restId;
    }

    public void setRestId(int restId) {
        this.restId = restId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCmdType() {
        return cmdType;
    }

    public void setCmdType(String cmdType) {
        this.cmdType = cmdType;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
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



    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getNameRemark() {
        return nameRemark;
    }

    public void setNameRemark(String nameRemark) {
        this.nameRemark = nameRemark;
    }

    @Override
    public int compareTo(Sys_RestListModel o) {
        return o.restId-restId;
    }
}
