package ytb.manager.projectStat.model.ProjectStatProject;

import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;

public class StatProjectType {

    int id;
    String name;

    Float total;
    Float publish;

    Float req;
    Float talk;

    Float process;
    Float finish;

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPublish() {
        return publish;
    }

    public void setPublish(Float publish) {
        this.publish = publish;
    }

    public Float getReq() {
        return req;
    }

    public void setReq(Float req) {
        this.req = req;
    }

    public Float getTalk() {
        return talk;
    }

    public void setTalk(Float talk) {
        this.talk = talk;
    }

    public Float getProcess() {
        return process;
    }

    public void setProcess(Float process) {
        this.process = process;
    }

    public Float getFinish() {
        return finish;
    }

    public void setFinish(Float finish) {
        this.finish = finish;
    }

    public String toString() {
        return YtbUtils.toJSONStringPretty(this);
    }
}
