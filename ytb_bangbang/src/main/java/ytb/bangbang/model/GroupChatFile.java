package ytb.bangbang.model;

import java.util.Date;

/**
 * tanc
 * 群聊-聊天记录-图片与视频
 */
public class GroupChatFile {

    private Integer id;
    private Integer groupId;
    private Integer fromUser;
    private Integer fileType;//1:文件;2:图片,视频,音频
    private String fileName;//文件名称
    private String fileSize;//文件大小
    private String url;//文件路径
    private Date createTime;//创建时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getFromUser() {
        return fromUser;
    }

    public void setFromUser(Integer fromUser) {
        this.fromUser = fromUser;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
