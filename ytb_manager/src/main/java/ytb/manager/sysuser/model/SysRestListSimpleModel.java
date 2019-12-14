package ytb.manager.sysuser.model;

import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;

/**
 * Package: ytb.manager.sysuser.model
 * Author: ZCS
 * Date: Created in 2018/9/10 14:12
 */
public class SysRestListSimpleModel {
    private int restId = 0;
    private String model= "";
    private String cmdType = "";
    private String cmd= "";
    private int parentId = 0;
    private String restName = "";

    private List<SysRestListSimpleModel> children = Lists.newArrayList();


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

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public List<SysRestListSimpleModel> getChildren() {
        return children;
    }

    public void setChildren(List<SysRestListSimpleModel> children) {
        this.children = children;
    }

}
