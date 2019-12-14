package ytb.log.notify.model;

/**
 * Package: ytb.log.notify.model
 * Author: ZCS
 * Date: Created in 2018/11/24 11:09
 * Company: 公司
 */
public class SystemNoticesReadModel {

    //表主键
    private Integer id = 0;

    //接收者ID
    private Integer recId = 0;

    //消息ID
    private Integer messageId = 0;

    //已读未读状态
    private Integer status = 1;

    //是否删除标志
    private Integer deleteFlag = 1;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecId() {
        return recId;
    }

    public void setRecId(Integer recId) {
        this.recId = recId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
