package ytb.project.view.model.ProjectTaskViewModel;

import ytb.common.context.model.Ytb_Model;

import java.sql.Timestamp;
import java.util.List;

public class ViewMeetingNoticeModel extends Ytb_Model {

    private int documentId;

    private String documentName;

    private String sponsor;

    private String participant ;

    private List<String> participantNameList ;

    private String issue ;

    private Timestamp createTime;

    private String startTime ;

    private String stopTime ;

    public List<String> getParticipantNameList() {
        return participantNameList;
    }

    public void setParticipantNameList(List<String> participantNameList) {
        this.participantNameList = participantNameList;
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

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }


}
