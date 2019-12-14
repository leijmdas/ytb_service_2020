package ytb.project.view.model.ProjectTaskViewModel;

import ytb.common.context.model.Ytb_Model;

import java.sql.Timestamp;
import java.util.List;

public class ViewCustomTaskModel extends Ytb_Model {

    private String userId;

    private int documentId;

    private String documentName;

    private String receiver;

    private List<String> receiverNameList;

    public List<String> getReceiverNameList() {
        return receiverNameList;
    }

    public void setReceiverNameList(List<String> receiverNameList) {
        this.receiverNameList = receiverNameList;
    }

    private String remark ;

    private Timestamp createTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }


}
