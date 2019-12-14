package ytb.project.model;

import ytb.common.context.model.Ytb_ModelSkipNull;

/**
 * Package: ytb.project.model
 * <p>
 * Description： TODO
 * <p>
 * Author: ZCS
 * <p>
 * Date: Created in 2019/1/10 18:11
 * <p>
 * Company: 公司
 * <p>
 * Copyright: Copyright (c) 2018
 * <p>
 * Version: 0.0.1
 * <p>
 * Modified By:
 */
//  sql.append(" select b.doc_type,a.folder_id,a.project_id,a.user_id,b.document_id,b.title as docName");

public class UserShareModel  extends Ytb_ModelSkipNull {
    private Integer docType = 0;
    private Integer documentId = 0;

    private Integer userId = 0;
    private Integer projectId = 0;
    private String docName = "";

    public Integer getDocType() {
        return docType;
    }

    public void setDocType(Integer docType) {
        this.docType = docType;
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }


}
